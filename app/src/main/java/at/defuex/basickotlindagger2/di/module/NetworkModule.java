package at.defuex.basickotlindagger2.di.module;

import android.app.Application;
import android.content.Context;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory;

import org.joda.time.DateTime;

import java.io.IOException;
import java.lang.reflect.Type;

import javax.inject.Named;
import javax.inject.Singleton;

import at.defuex.basickotlindagger2.data.RestManager;
import at.defuex.basickotlindagger2.data.network.RestInterface;
import at.defuex.basickotlindagger2.data.network.RestManagerImpl;
import at.defuex.basickotlindagger2.di.inject.ApplicationContext;
import at.defuex.basickotlindagger2.di.inject.InterceptorInfo;
import at.defuex.basickotlindagger2.utils.AppUtils;
import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by timoobereder on 03.12.17.
 */

@Module
public class NetworkModule {

    @Provides
    @ApplicationContext
    Context provideApplicationContext(Application application) {
        return application.getApplicationContext();
    }

    @Provides
    @Singleton
    Cache provideCache(Application application) {
        int cacheSize = 10 * 1024 * 1024;
        return new Cache(application.getCacheDir(), cacheSize);
    }

    @Singleton
    @Provides
    public HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return logging;

    }

    @Provides
    @InterceptorInfo
    public int provideRetryCount() {
        return AppUtils.RETRY_NETWORK_REQUEST_COUNT;
    }

    @Singleton
    @Provides
    public Interceptor provideRetryInterceptor(@InterceptorInfo int retryCount) {
        return chain -> {
            Request request = chain.request();
            Response response = null;
            IOException exception = null;

            int tryCount = 0;
            while (tryCount < retryCount && (null == response || !response.isSuccessful())) {
                try {
                    response = chain.proceed(request);
                } catch (IOException e) {
                    exception = e;
                } finally {
                    tryCount++;
                }
            }

            if (null == response && null != exception) {
                throw exception;
            }

            return response;
        };
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    @Singleton
    RestManager provideRestManager(@ApplicationContext Context context, RestInterface restInterface) {
        return new RestManagerImpl(context, restInterface);
    }

    @Provides
    @Singleton
    RestInterface provideRestInterface(@Named("endpoint") String endpoint, Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(endpoint)
                .client(okHttpClient) //(new OkHttpClient.Builder().addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(CoroutineCallAdapterFactory.create())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(RestInterface.class);
    }

    @Provides @Named("endpoint") String provideEndpoint() {
        return "https://api.github.com/";
    }

    @Provides Gson provideGson() {
        return new GsonBuilder()
                .registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory())
                .registerTypeAdapter(DateTime.class, new DateTimeDeserializer())
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .create();
    }

    public static class NullStringToEmptyAdapterFactory<T> implements TypeAdapterFactory {
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {

            Class<T> rawType = (Class<T>) type.getRawType();
            if (rawType != String.class) {
                return null;
            }
            return (TypeAdapter<T>) new StringAdapter();
        }
    }

    public static class StringAdapter extends TypeAdapter<String> {
        public String read(JsonReader reader) throws IOException {
            if (reader.peek() == JsonToken.NULL) {
                reader.nextNull();
                return "";
            }
            return reader.nextString();
        }

        public void write(JsonWriter writer, String value) throws IOException {
            if (value == null) {
                writer.nullValue();
                return;
            }
            writer.value(value);
        }
    }

    private class DateTimeDeserializer implements JsonDeserializer<DateTime> {
        public DateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            return new DateTime(json.getAsJsonPrimitive().getAsString());
        }
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Cache cache,
                                     HttpLoggingInterceptor interceptor,
                                     Interceptor retryInterceptor) {
        return new OkHttpClient.Builder()
                .addNetworkInterceptor(interceptor)
                .addInterceptor(retryInterceptor)
                .cache(cache)
                .build();
    }
}
