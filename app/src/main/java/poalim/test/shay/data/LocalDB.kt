package poalim.test.shay.data

import android.content.Context
import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import poalim.test.shay.data.AppDatabase.Companion.MIGRATION_1_2
import poalim.test.shay.model.Movie
import poalim.test.shay.model.Movies

class LocalDB(context: Context) {
    private var mDataBase: AppDatabase? = null
    private var mHandler: Handler? = null

    init {
        val thread = HandlerThread("db")
        thread.start()
        mHandler = Handler(thread.looper)

        mDataBase = Room.databaseBuilder(
            context,
            AppDatabase::class.java, "the_movies_db"
        ).addMigrations(MIGRATION_1_2).build()
    }

    fun getMovieList(live: MutableLiveData<Movies>, startDate: String, endDate: String) {
        mHandler?.post {
            mDataBase!!.userDao().getUpdatedMovieList(startDate, endDate).apply {
                var data = live.value
                data?.mData?.addAll(this)
                live.postValue(data)
            }
        }
        deleteOldMovies(startDate)
    }

    fun getAllMovieList(live: MutableLiveData<Movies>){
        mHandler?.post {
            mDataBase!!.userDao().getAllDB().apply {
                var data = live.value
                data?.mData?.clear()
                data?.mData?.addAll(this)
                live.postValue(data)
            }
        }
    }

    fun getFavorites(live: MutableLiveData<Movies>){
        mHandler?.post {
            mDataBase!!.userDao().getByFavorites(1).apply {
                var data = live.value
                data?.mData?.clear()
                data?.mData?.addAll(this)
                live.postValue(data)
            }
        }
    }

    fun insertMovies(movies: List<Movie>) {
        mHandler?.post {

            for (movie in movies) {

                if (mDataBase!!.userDao().search(movie.mId) == null) {

                    if (movie.mBackdropPath == null) { movie.mBackdropPath = "" }
                    if (movie.mPosterPath == null) { movie.mPosterPath = "" }
                    if (movie.mOverview == null) { movie.mOverview = "" }
                    if (movie.mRating == null) { movie.mRating = "" }
                    if (movie.mReleaseDate == null) { movie.mReleaseDate = "" }
                    if (movie.mTitle == null) { movie.mTitle = "" }

                    mDataBase!!.userDao().insert(movie)
                }
            }
        }
    }

    private fun deleteOldMovies(endDate: String) {
        mHandler?.post {
            mDataBase!!.userDao().deleteOldMovies(endDate)
        }
    }

    fun updateMovie(movie : Movie) {
        mHandler?.post {
            mDataBase!!.userDao().updateMovie(movie.mId, movie.mFavorite)
        }
    }

    companion object {

        private val mSync = Any()
        private var mInstance: LocalDB? = null

        fun Instance(context: Context): LocalDB {
            synchronized(mSync) {
                if (mInstance == null) {
                    mInstance = LocalDB(context)
                }
                return mInstance!!
            }
        }
    }
}