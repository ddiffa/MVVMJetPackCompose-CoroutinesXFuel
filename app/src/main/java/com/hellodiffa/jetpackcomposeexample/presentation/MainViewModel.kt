package com.hellodiffa.jetpackcomposeexample.presentation

import androidx.lifecycle.*
import com.hellodiffa.jetpackcomposeexample.data.model.Article
import com.hellodiffa.jetpackcomposeexample.data.repository.NewsRepositoryImpl
import com.hellodiffa.jetpackcomposeexample.domain.executor.Dispatchers
import com.hellodiffa.jetpackcomposeexample.remote.Resource
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import okio.IOException
import java.util.concurrent.TimeoutException

class MainViewModel(
    private val repository: NewsRepositoryImpl,
    private val dispatchers: Dispatchers
) : ViewModel(), LifecycleObserver {

    private val _news = MutableLiveData<Resource<List<Article>?>>()
    val news: LiveData<Resource<List<Article>?>> get() = _news


    private fun setResource(resource: Resource<List<Article>?>) {
        _news.postValue(resource)
    }

    internal fun getAllArticle() {
        viewModelScope.launch(dispatchers.mainDispatchers()) {
            setResource(Resource.loading())
            try {
                val result = async(dispatchers.ioDispatchers()) {
                    repository.getAllArticle()
                }
                getData(result.await())
            } catch (e: Throwable) {
                when (e) {
                    is IOException -> setResource(Resource.error("No internet Connection"))
                    is TimeoutException -> setResource(Resource.error("Connection Timeout"))
                    else -> setResource(Resource.error("unknown"))
                }
            }
        }
    }

    private fun getData(result: List<Article>?) {
        setResource(Resource.success(result)).takeUnless {
            result.isNullOrEmpty()
        }
    }
}