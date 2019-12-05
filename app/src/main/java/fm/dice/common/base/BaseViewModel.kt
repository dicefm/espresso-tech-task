package fm.dice.common.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import timber.log.Timber
import kotlin.properties.Delegates

open class BaseViewModel : ViewModel() {

    protected val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        Timber.e(exception)
        error.value = exception
    }

    private val error = MutableLiveData<Throwable>()
    private val isLoading = MutableLiveData<Boolean>()

    fun isLoading(): LiveData<Boolean> = isLoading

    fun error(): LiveData<Throwable> = error

    protected fun Job.addToLoadingState(): Job {
        isLoading.value = true
        invokeOnCompletion { isLoading.value = false }
        return this
    }
}