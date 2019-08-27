package poalim.test.shay

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import poalim.test.shay.model.Movie
import poalim.test.shay.model.Movies
import poalim.test.shay.view.adapters.MovieListAdapter
import poalim.test.shay.view_model.MainViewModel

class MainActivity : AppCompatActivity() {

//    private val mViewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val adapter = MovieListAdapter()
        ViewModelProviders.of(this).get(MainViewModel::class.java)
            .getMovies().observe(this, Observer<Movies>{ movies ->
                adapter.update(movies.getData())
            })
        movie_list.layoutManager = LinearLayoutManager(this)
        movie_list.adapter = adapter

    }
}
