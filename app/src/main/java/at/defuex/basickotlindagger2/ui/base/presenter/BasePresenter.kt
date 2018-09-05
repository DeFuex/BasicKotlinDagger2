package at.defuex.basickotlindagger2.ui.base.presenter

import android.os.Bundle
import android.util.Log
import at.defuex.basickotlindagger2.ui.base.view.MVPView
//import kotlinx.coroutines.experimental.*
//import kotlinx.coroutines.experimental.android.UI
//import kotlin.coroutines.experimental.CoroutineContext

/**
 * Created by timoobereder on 03.12.17.
 */

abstract class BasePresenter<out T : MVPView> protected constructor(protected val view: T) : Presenter {

    override fun onStart(savedInstanceState: Bundle?) {}

    override fun onResume() {}

    override fun onPause() {}

    override fun onSaveInstanceState(outState: Bundle) {}

    override fun onEnd() {}

//    protected var parentJob: Job = Job()
//    //var backgroundContext: CoroutineContext = IO
//    var backgroundContext: CoroutineContext = CommonPool
//    var foregroundContext: CoroutineContext = UI


//    protected abstract suspend fun executeOnBackground(): T
//
//    fun execute(onComplete: (T) -> Unit, onError: (Throwable) -> Unit) {
//        parentJob.cancel()
//        parentJob = Job()
//        launch(foregroundContext, parent = parentJob) {
//            try {
//                val result = withContext(backgroundContext) {
//                    executeOnBackground()
//                }
//                onComplete.invoke(result)
//            } catch (e: CancellationException) {
//                Log.d("UseCase", "canceled by user")
//            } catch (e: Exception) {
//                onError(e)
//            }
//        }
//    }
//
//    protected suspend fun <X> background(context: CoroutineContext = backgroundContext, block: suspend () -> X): Deferred<X> {
//        return async(context, parent = parentJob) {
//            block.invoke()
//        }
//    }
//
//    fun unsubscribe() {
//        parentJob.cancel()
//    }
}

///**
// * Abstract class for a Use Case (Interactor in terms of Clean Architecture).
// * This interface represents a execution unit for different use cases (this means any use case
// * in the application should implement this contract).
// * <p>
// * By convention each UseCase implementation will return the result using a coroutine
// * that will execute its job in a background thread and will post the result in the UI thread.
// */
//abstract class UseCase<T> {
//
//
//
//}