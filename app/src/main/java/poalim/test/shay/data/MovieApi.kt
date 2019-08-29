package poalim.test.shay.data

import okhttp3.ResponseBody
import poalim.test.shay.model.Movie
import poalim.test.shay.model.Movies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Streaming

interface MovieApi
{
//    @GET("trending/movie/week?api_key=a8dce934a78ea981891a5ac56d170d5c")
//    fun getLatestMoviesList() : Call<Movies>

    @GET("discover/movie?api_key=a8dce934a78ea981891a5ac56d170d5c")
    fun getLatestMoviesList(@Query("primary_release_date.gte") date_start : String,
                             @Query("primary_release_date.lte") date_end : String) : Call<Movies>
}