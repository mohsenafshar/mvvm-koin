package ir.mohsenafshar.testfullkotlin.data.remote.ds

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Action
import io.reactivex.schedulers.Schedulers
import ir.mohsenafshar.testfullkotlin.data.model.News
import ir.mohsenafshar.testfullkotlin.data.remote.NetworkService
import ir.mohsenafshar.testfullkotlin.data.remote.State

class NewsDataSource(
    private val networkService: NetworkService,
    private val compositeDisposable: CompositeDisposable
) : PageKeyedDataSource<Int, News>() {


    var state: MutableLiveData<State> = MutableLiveData()
    private var retryCompletable: Completable? = null

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, News>) {
        updateState(State.LOADING)

        val subscribe = networkService.getNews(1, params.requestedLoadSize).subscribe({ response ->
            updateState(State.DONE)
            callback.onResult(response.news, null, 2)

        }, {
            updateState(State.ERROR)
            setRetry(Action { loadInitial(params, callback) })

        })

        compositeDisposable.add(subscribe)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, News>) {
        updateState(State.LOADING)

        val subscribe = networkService.getNews(params.key, params.requestedLoadSize).subscribe({ response ->
            updateState(State.DONE)
            callback.onResult(response.news, params.key + 1)

        }, {
            updateState(State.ERROR)
            setRetry(Action { loadAfter(params, callback) })

        })

        compositeDisposable.add(subscribe)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, News>) {

    }

    private fun updateState(state: State) {
        this.state.postValue(state)
    }

    fun retry() {
        if (retryCompletable != null) {
            compositeDisposable.add(
                retryCompletable!!
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            )
        }
    }

    private fun setRetry(action: Action?) {
        retryCompletable = if (action == null) null else Completable.fromAction(action)
    }
}