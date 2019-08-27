package poalim.test.shay.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.movie_list_item.view.*
import poalim.test.shay.R
import poalim.test.shay.model.Movie
import poalim.test.shay.view.model.Network

class MovieListAdapter() : RecyclerView.Adapter<MovieListAdapter.MovieHolder>() {

    private var mData: List<Movie> = emptyList()

    fun update(movies: List<Movie>) {
        mData = movies
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        return MovieHolder(LayoutInflater.from(parent.context).inflate(R.layout.movie_list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.bind(mData[position])
    }

    class MovieHolder(v: View) : RecyclerView.ViewHolder(v) {

        private var mTitle: TextView
        private var mPoster: ImageView

        init {
            mTitle = v.title
            mPoster = v.poster
        }

        fun bind(data: Movie) {
            mTitle.text = data.mTitle
            Glide.with(mPoster).load(Network.IMAGE_URL + data.mPosterPath).into(mPoster)
        }
    }
}