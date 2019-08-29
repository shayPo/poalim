package poalim.test.shay.view_model.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import poalim.test.shay.model.Movie
import poalim.test.shay.view_model.MovieViewModel

class MovieViewModelFactory(val movie : Movie) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieViewModel(movie) as T
    }

    companion object{
        const val MOVIE_DATA = "Data"
    }
}