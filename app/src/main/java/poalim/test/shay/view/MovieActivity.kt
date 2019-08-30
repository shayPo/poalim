package poalim.test.shay.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_movie.*
import poalim.test.shay.R
import poalim.test.shay.data.Network
import poalim.test.shay.model.Movie
import poalim.test.shay.view_model.MovieViewModel
import poalim.test.shay.view_model.factory.MovieViewModelFactory
import poalim.test.shay.view_model.factory.MovieViewModelFactory.Companion.MOVIE_DATA

class MovieActivity : AppCompatActivity()
{
    var mMovieViewModel : MovieViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)
        mMovieViewModel = ViewModelProviders.of(this, MovieViewModelFactory(intent.getParcelableExtra(MOVIE_DATA))).get(MovieViewModel::class.java)
        mMovieViewModel!!.getMovie().observe(this, Observer<Movie>{ movie ->
                if(movie.mBackdropPath != null) {
                    Glide.with(this@MovieActivity.applicationContext).load(Network.IMAGE_URL + movie.mBackdropPath)
                        .into(movie_image)
                }
                is_liked.setImageResource(if(movie.mFavorite == 0)  R.drawable.not_like else R.drawable.like)
                movie_title.text = resources.getString(R.string.title, movie.mTitle)
                movie_date.text = resources.getString(R.string.release_date, movie.mReleaseDate)
                movie_rating.text = resources.getString(R.string.rating, movie.mRating)
                movie_overview.text = resources.getString(R.string.overview, movie.mOverview)
            })
    }

    public fun isLikes(v : View)
    {
        mMovieViewModel!!.onLikeClick()
    }

    override fun onDestroy() {
        super.onDestroy()
        mMovieViewModel = null
    }
}