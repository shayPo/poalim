package poalim.test.shay.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import poalim.test.shay.data.Repository
import poalim.test.shay.model.Movies

class FavoritesViewModel : ViewModel()
{
    private var mRepository : Repository? = null
    private val mFavorites = MutableLiveData<Movies>()

    init { mFavorites.value = Movies() }

    fun updateMovieList(){
        mRepository = Repository.Instance()
        mRepository!!.getFavorites(mFavorites)
    }

    fun getMovies(): LiveData<Movies> {
        return mFavorites
    }

    override fun onCleared() {
        super.onCleared()
        mRepository = null
    }
}