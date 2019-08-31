package poalim.test.shay.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import poalim.test.shay.data.Repository
import poalim.test.shay.model.Movie
import poalim.test.shay.model.Movies

class MainListViewModel : ViewModel() {
    private var mRepository : Repository? = null
    private val mMovieList = MutableLiveData<Movies>()
    private var mAfterUpdate = false

    init {
        mMovieList.value = Movies()
    }

    fun updateMovieList(){
        if(!mAfterUpdate) {
            mAfterUpdate = true
            mRepository = Repository.Instance()
            mRepository!!.getMovieList(mMovieList)
        }
        else{
            mRepository!!.getDataBaseList(mMovieList)
        }
    }

    fun getMovies(): LiveData<Movies> { return mMovieList }

    fun onLikeClick(data: Movie) {
        if(data.mFavorite == 0) data.mFavorite = 1 else data.mFavorite = 0
        Repository.Instance()?.setLikeValue(data)
        mMovieList.postValue(mMovieList.value)
    }

    override fun onCleared() {
        super.onCleared()
        mRepository = null
    }

}