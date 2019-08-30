package poalim.test.shay.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import poalim.test.shay.data.Repository
import poalim.test.shay.model.Movie


class MovieViewModel(data : Movie) : ViewModel()
{
    private var mMovieData = MutableLiveData<Movie>()

    init {
       mMovieData.value = data
    }

    fun getMovie() : LiveData<Movie>
    {
        return mMovieData
    }

    fun onLikeClick()
    {
        var data = mMovieData.value
        if(data!!.mFavorite == 0) data!!.mFavorite = 1 else data!!.mFavorite = 0
        Repository.Instance()?.setLikeValue(data)
        mMovieData.postValue(data)
    }

    override fun onCleared() {
        super.onCleared()
        mMovieData.value = null
    }
}