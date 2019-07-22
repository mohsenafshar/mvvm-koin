package ir.mohsenafshar.testfullkotlin.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import io.reactivex.disposables.CompositeDisposable
import ir.mohsenafshar.testfullkotlin.data.model.News
import ir.mohsenafshar.testfullkotlin.data.remote.NetworkService
import ir.mohsenafshar.testfullkotlin.data.remote.State
import ir.mohsenafshar.testfullkotlin.data.remote.ds.NewsDataSource
import ir.mohsenafshar.testfullkotlin.data.remote.ds.NewsDataSourceFactory

class NewsListViewModel : ViewModel() {

    private val pageSize = 5

    var newsList: LiveData<PagedList<News>>

    private val networkService = NetworkService.getService()
    private val compositeDisposable = CompositeDisposable()
    private val newsDataSourceFactory: NewsDataSourceFactory

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setInitialLoadSizeHint(pageSize * 2)
            .setEnablePlaceholders(false)
            .build()

        newsDataSourceFactory = NewsDataSourceFactory(compositeDisposable, networkService)
        newsList = LivePagedListBuilder(newsDataSourceFactory, config).build()
    }

    fun getState(): LiveData<State> =
        Transformations.switchMap<NewsDataSource, State>(
            newsDataSourceFactory.newsDataSourceLiveData,
            NewsDataSource::state
        )

    fun retry() {
        newsDataSourceFactory.newsDataSourceLiveData.value?.retry()
    }

    fun listIsEmpty(): Boolean {
        return newsList.value?.isEmpty() ?: true
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}