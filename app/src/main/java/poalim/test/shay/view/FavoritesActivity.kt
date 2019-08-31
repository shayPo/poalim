package poalim.test.shay.view

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_favorite.*
import poalim.test.shay.R
import poalim.test.shay.model.Movie
import poalim.test.shay.model.Movies
import poalim.test.shay.view.adapters.MovieListAdapter
import poalim.test.shay.view.adapters.OnItemClickListener
import poalim.test.shay.view_model.FavoritesViewModel
import poalim.test.shay.view_model.factory.MovieViewModelFactory

class FavoritesActivity : AppCompatActivity() {

    var mFavoritesViewModel : FavoritesViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)
        val adapter = MovieListAdapter(object : OnItemClickListener {
            override fun onItemClick(movie: Movie) {
                //TODO add animation
                val intent = Intent(this@FavoritesActivity, MovieActivity::class.java).apply {
                    putExtra(MovieViewModelFactory.MOVIE_DATA, movie)
                }
                startActivity(intent)
            }

            override fun onLikeClick(movie: Movie) {}
        })

        mFavoritesViewModel = ViewModelProviders.of(this).get(FavoritesViewModel::class.java)
        mFavoritesViewModel!!.getMovies().observe(this, Observer<Movies>{ movies ->
            if(movies != null) { adapter.update(movies.getData()) }
        })
        movie_list.layoutManager = LinearLayoutManager(this)
        movie_list.adapter = adapter
        mFavoritesViewModel?.updateMovieList()
    }


}