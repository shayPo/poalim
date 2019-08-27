package poalim.test.shay.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import poalim.test.shay.MovieApi
import poalim.test.shay.model.Movie
import poalim.test.shay.model.Movies
import poalim.test.shay.view.model.Network
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    private val mTime = 0
    private val mNetwork = Network.Instance()
    private var mMovieList = MutableLiveData<Movies>()

    init {
        //TODO: add timer - every 4 days
        mNetwork.getMovieList(mMovieList)

    }


    fun getMovies(): LiveData<Movies> {
        return mMovieList
    }


}