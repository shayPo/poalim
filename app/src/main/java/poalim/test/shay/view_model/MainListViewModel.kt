package poalim.test.shay.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import poalim.test.shay.data.Repository
import poalim.test.shay.model.Movies
import java.text.SimpleDateFormat
import java.util.*

class MainListViewModel : ViewModel() {
    private var mRepository : Repository? = null
    private var mMovieList = MutableLiveData<Movies>()

    init {
        mRepository = Repository.Instance()
        mRepository!!.getMovieList(mMovieList)
    }

    fun getMovies(): LiveData<Movies> {
        return mMovieList
    }

    override fun onCleared() {
        super.onCleared()
        mRepository = null
    }
}