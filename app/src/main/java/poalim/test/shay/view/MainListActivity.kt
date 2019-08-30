package poalim.test.shay.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import poalim.test.shay.R
import poalim.test.shay.model.Movie
import poalim.test.shay.model.Movies
import poalim.test.shay.view.MovieActivity
import poalim.test.shay.view.adapters.MovieListAdapter
import poalim.test.shay.view.adapters.OnItemClickListener
import poalim.test.shay.view_model.MainListViewModel
import poalim.test.shay.view_model.factory.MovieViewModelFactory.Companion.MOVIE_DATA

class MainListActivity : AppCompatActivity() {

    var mMainListViewModel : MainListViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val adapter = MovieListAdapter(object : OnItemClickListener {
            override fun onItemClick(movie: Movie) {
                val intent = Intent(this@MainListActivity, MovieActivity::class.java).apply {
                    putExtra(MOVIE_DATA, movie)
                }
                startActivity(intent)
            }

            override fun onLikeClick(movie: Movie)
            {
                mMainListViewModel?.onLikeClick(movie)
            }
        })

        mMainListViewModel = ViewModelProviders.of(this).get(MainListViewModel::class.java)
        mMainListViewModel!!.getMovies().observe(this, Observer<Movies>{ movies ->
                if(movies != null) { adapter.update(movies.getData()) }
            })

        mMainListViewModel?.updateMovieList()
        movie_list.layoutManager = LinearLayoutManager(this)
        movie_list.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        mMainListViewModel = null
    }

}
