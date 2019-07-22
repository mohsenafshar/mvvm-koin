package ir.mohsenafshar.testfullkotlin.data.remote.routes

import ir.mohsenafshar.testfullkotlin.data.model.Feed
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface NewsApi {

    /*
     * We would be using the below url:
     * https://newsapi.org/v2/everything?q=movies&apiKey=079dac74a5f94ebdb990ecf61c8854b7&pageSize=20&page=2
     * The url has four query parameters.
     * We would be changing the pageSize and the page
     */
    @GET("/v2/everything")
    fun fetchFeed(
        @Query("q") q: String,
        @Query("apiKey") apiKey: String,
        @Query("page") page: Long,
        @Query("pageSize") pageSize: Int
    ): Call<Feed>

}