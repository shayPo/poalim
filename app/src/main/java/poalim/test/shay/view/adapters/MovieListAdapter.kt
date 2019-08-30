package poalim.test.shay.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_movie.*
import kotlinx.android.synthetic.main.movie_list_item.view.*
import poalim.test.shay.R
import poalim.test.shay.data.Network
import poalim.test.shay.model.Movie

class MovieListAdapter(val listener : OnItemClickListener) : RecyclerView.Adapter<MovieListAdapter.MovieHolder>() {

    private var mData: List<Movie> = emptyList()

    fun update(movies: List<Movie>) {
        mData = movies
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        return MovieHolder(LayoutInflater.from(parent.context).inflate(R.layout.movie_list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return if(mData == null) 0 else mData.size
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.bind(mData[position], listener)
    }

    class MovieHolder(val v: View) : RecyclerView.ViewHolder(v) {


        private var mTitle : TextView
        private var mPoster : ImageView
        private var mFavorite : ImageView

        init {
            mTitle = v.title
            mPoster = v.poster
            mFavorite = v.favorite
        }

        fun bind(data: Movie, listener: OnItemClickListener) {
            mTitle.text = data.mTitle
            Glide.with(v.context.applicationContext).load(Network.IMAGE_URL + data.mPosterPath).into(mPoster)
            v.setOnClickListener{ listener.onItemClick(data) }
            mFavorite.setOnClickListener{ listener.onLikeClick(data) }
            mFavorite.setImageResource(if(data.mFavorite == 0)  R.drawable.not_like else R.drawable.like)
        }
    }
}