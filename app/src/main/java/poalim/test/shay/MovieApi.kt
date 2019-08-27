package poalim.test.shay

import okhttp3.ResponseBody
import poalim.test.shay.model.Movie
import poalim.test.shay.model.Movies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Streaming

interface MovieApi
{
    @GET("trending/movie/week?api_key=a8dce934a78ea981891a5ac56d170d5c")
    fun getLatestMoviesList() : Call<Movies>

//    @GET("https://image.tmdb.org/t/p/original/{imageName}")
//    fun getLargeImage(@Path("imageName") imageName: String) : Call<ResponseBody>
//
//    @GET("https://image.tmdb.org/t/p/w500/{imageName}")
//    fun getImage(@Path("imageName") imageName: String) : Call<ResponseBody>
}