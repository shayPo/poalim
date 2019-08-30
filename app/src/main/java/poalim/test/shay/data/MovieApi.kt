package poalim.test.shay.data

import poalim.test.shay.model.Movies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface MovieApi
{
    @GET("discover/movie?api_key=a8dce934a78ea981891a5ac56d170d5c&sort_by=release_date.desc")
    fun getLatestMoviesList(@Query("release_date.gte") date_start : String,
                             @Query("release_date.lte") date_end : String) : Call<Movies>
}