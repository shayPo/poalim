package poalim.test.shay.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_movie.*
import poalim.test.shay.R
import poalim.test.shay.model.Movie
import poalim.test.shay.view.model.Network
import poalim.test.shay.view_model.MovieViewModel
import poalim.test.shay.view_model.factory.MovieViewModelFactory
import poalim.test.shay.view_model.factory.MovieViewModelFactory.Companion.MOVIE_DATA

class MovieActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)
        ViewModelProviders.of(this, MovieViewModelFactory(intent.getParcelableExtra(MOVIE_DATA))).get(MovieViewModel::class.java)
            .getMovie().observe(this, Observer<Movie>{ movie ->
                if(movie.mBackdropPath.isNotBlank()) {
                    Glide.with(this@MovieActivity.applicationContext).load(Network.IMAGE_URL + movie.mBackdropPath)
                        .into(movie_image)
                }
                movie_date.text = resources.getString(R.string.release_date, movie.mReleaseDate)
                movie_rating.text = resources.getString(R.string.rating, movie.mRating)
                movie_overview.text = movie.mOverview
                movie_overview.text = movie.mOverview
                is_liked.setImageResource(if(!movie.mFavorite)  R.drawable.not_like else R.drawable.like)

            })
    }
}