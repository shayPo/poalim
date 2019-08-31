package poalim.test.shay.data

import androidx.lifecycle.MutableLiveData
import poalim.test.shay.model.Movies
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Network
{
    var mRetrofit : Retrofit? = null
    var mApi : MovieApi? = null

    private constructor()

    init {
        mRetrofit = Retrofit.Builder().baseUrl(BASE_URL).
            addConverterFactory(GsonConverterFactory.create()).
            build()
        mApi = mRetrofit!!.create(MovieApi::class.java)
    }

    fun getMovieList(live : MutableLiveData<Movies>, startDate : String, endDate : String)
    {
        var call = mApi?.getLatestMoviesList(startDate,endDate)
        call?.enqueue(object : Callback<Movies> {

            override fun onResponse(call: Call<Movies>?, response: Response<Movies>?) {
                val movies = response?.body()
                Repository.Instance()!!.updateDataBase(movies!!.mData)
                live.value?.mData?.addAll(movies!!.mData)
                live.postValue(live.value)
            }

            override fun onFailure(call: Call<Movies>?, t: Throwable?) {
            }
        })
    }

    companion object {
        private val mSync = Any()
        private var mInstance : Network? = null
        private const val BASE_URL = "https://api.themoviedb.org/3/"
        public const val IMAGE_URL = "https://image.tmdb.org/t/p/w500/"

        fun Instance() : Network
        {
         synchronized(mSync){
             if(mInstance == null)
             {
                 mInstance = Network()
             }
             return mInstance!!
         }
        }
    }
}