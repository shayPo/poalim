package poalim.test.shay.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import poalim.test.shay.model.Movie
import poalim.test.shay.model.Movies


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
}