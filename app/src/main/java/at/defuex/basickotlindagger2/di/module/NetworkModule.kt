package at.defuex.basickotlindagger2.di.module

import android.app.Application
import android.content.Context
import at.defuex.basickotlindagger2.data.RestManager
import at.defuex.basickotlindagger2.data.network.RestInterface
import at.defuex.basickotlindagger2.data.network.RestManagerImpl
import at.defuex.basickotlindagger2.di.inject.ApplicationContext
import at.defuex.basickotlindagger2.di.inject.InterceptorInfo
import at.defuex.basickotlindagger2.utils.AppUtils
import com.google.gson.*
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import dagger.Module
import dagger.Provides
//import io.reactivex.disposables.CompositeDisposable
import okhttp3.*

import okhttp3.logging.HttpLoggingInterceptor
import org.joda.time.DateTime
import retrofit2.Retrofit
//import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.lang.reflect.Type
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by timoobereder on 03.12.17.
 */

//@Module
//class NetworkModule {
//
//    private var logging: HttpLoggingInterceptor? = null
//    private var chain: Interceptor.Chain? = null
//    private var exception: IOException? = null
//    private var response: Response? = null
//
//    companion object NullStringToEmptyAdapterFactory : TypeAdapterFactory {
//        override fun <T : Any?> create(gson: Gson?, type: TypeToken<T>?): TypeAdapter<T>? {
//            var rawType: Class<T> = type?.rawType as Class<T>
//
//            if (rawType != String.javaClass) {
//                return null
//            }
//            return StringAdapter as TypeAdapter<T>
//        }
//    }
//
//    object StringAdapter : TypeAdapter<String>() {
//        override fun read(reader: JsonReader?): String? {
//            if (reader?.peek() == JsonToken.NULL) {
//                reader.nextNull()
//                return ""
//            }
//            return reader?.nextString()
//        }
//
//        override fun write(writer: JsonWriter?, value: String?) {
//            if (value == null) {
//                writer?.nullValue()
//                return
//            }
//            writer?.value(value)
//        }
//    }
//
//    object DateTimeDeserializer : JsonDeserializer<DateTime> {
//        override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): DateTime {
//            return DateTime(json?.asJsonPrimitive?.asString)
//        }
//
//    }
//
//    @Provides
//    @ApplicationContext
//    fun provideApplicationContext(application: Application) : Context {
//        return application.applicationContext
//    }
//
//    @Provides
//    @Singleton
//    fun provideCache(application: Application) : Cache {
//        val cacheSize: Long = 10 * 1024 * 1024
//        return Cache(application.cacheDir, cacheSize)
//    }
//
//    @Provides
//    @Singleton
//    fun provideHttpLoggingInterceptor() : HttpLoggingInterceptor {
//        logging?.level = HttpLoggingInterceptor.Level.BODY
//        return logging!!
//    }
//
//    @Provides
//    @InterceptorInfo
//    fun provideRetryCount(): Int {
//        return AppUtils.RETRY_NETWORK_REQUEST_COUNT
//    }
//
//    @Singleton
//    @Provides
//    fun provideRetryInterceptor(@InterceptorInfo retryCount: Int): Interceptor {
//
//        var request: Request = chain!!.request()
//        var tryCount: Int = 0
//
//        while (tryCount < retryCount && (null == response || !(response?.isSuccessful)!!)) {
//            try {
//                response = chain!!.proceed(request)
//            } catch (e: IOException) {
//                exception = e
//            } finally {
//                tryCount++
//            }
//        }
//
//        if (null == response && null != exception) {
//            throw exception as IOException
//        }
//
//        return response as Interceptor
//    }
//
//    @Provides
//    fun provideCompositeDisposable() : CompositeDisposable {
//        return CompositeDisposable()
//    }
//
//    @Provides
//    @Singleton
//    fun providesRestManager(@ApplicationContext context: Context, restInterface: RestInterface) : RestManager {
//        return RestManagerImpl(context, restInterface)
//    }
//
//    @Provides
//    @Singleton
//    fun provideRestInterface(@Named("endpoint") endpoint: String, gson: Gson, okHttpClient: OkHttpClient) : RestInterface {
//        return Retrofit.Builder()
//                .baseUrl(endpoint)
//                .client(okHttpClient)
//                .addConverterFactory(GsonConverterFactory.create(gson))
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .build()
//                .create(RestInterface::class.java)
//    }
//
//    @Provides
//    @Named("endpoint")
//    fun provideEndpoint() : String {
//        return "https://api.gihtub.com/"
//    }
//
//    @Provides
//    fun provideGson() : Gson {
//        return GsonBuilder()
//                .registerTypeAdapterFactory(NullStringToEmptyAdapterFactory)
//                .registerTypeAdapter(DateTime::class.java, DateTimeDeserializer)
//                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
//                .create()
//    }
//
//    @Provides
//    @Singleton
//    fun provideOkHttpClient(cache: Cache, interceptor: HttpLoggingInterceptor, retryInterceptor: Interceptor) : OkHttpClient {
//        return OkHttpClient.Builder()
//                .addNetworkInterceptor(interceptor)
//                .addInterceptor(retryInterceptor)
//                .cache(cache)
//                .build()
//    }
//}