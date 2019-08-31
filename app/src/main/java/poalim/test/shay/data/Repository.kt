package poalim.test.shay.data

import android.content.Context
import android.content.SharedPreferences
import android.provider.Settings.Global.getString
import android.util.Log
import androidx.core.content.edit
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import poalim.test.shay.R
import poalim.test.shay.data.Network
import poalim.test.shay.model.Movie
import poalim.test.shay.model.Movies
import java.text.SimpleDateFormat
import java.util.*

class Repository(context : Context)
{
    private var mSharedPre : SharedPreferences? = null
    private var mContext : Context? = null
    private val mNetwork = Network.Instance()
    private var mDataBase : LocalDB? = null
    private var mStartTime : Long = 0
    private var mEndTime : Long = 0

    init {
        mContext = context
        mDataBase = LocalDB.Instance(context)
        mSharedPre = context.getSharedPreferences("time",0)
        mStartTime = mSharedPre!!.getLong(context.getString(R.string.start_key), 0)
        mEndTime = mSharedPre!!.getLong(context.getString(R.string.end_key), 0)
    }

    fun getMovieList(live : MutableLiveData<Movies>) {
        val format = SimpleDateFormat("yyyy-MM-dd")
        val calendar = Calendar.getInstance()
        val endDate = calendar.timeInMillis
        calendar.add(Calendar.MONTH, -1)
        val startDate = calendar.timeInMillis
        var middle : Long = 0

        mStartTime = startDate
        if(mEndTime > startDate)
        {
            middle = mEndTime
        }
        mEndTime = endDate

        if(middle > 0 )
        {
            mDataBase!!.getMovieList(live, format.format(mStartTime), format.format(middle))
            mNetwork!!.getMovieList(live, format.format(middle), format.format(mEndTime))
        }
        else
        {
            mNetwork!!.getMovieList(live, format.format(mStartTime), format.format(mEndTime))
        }
        updateDates()
    }

    fun getFavorites(live : MutableLiveData<Movies>){ mDataBase!!.getFavorites(live) }

    fun getDataBaseList(live : MutableLiveData<Movies>){ mDataBase!!.getAllMovieList(live) }

    fun updateDataBase(movies: List<Movie>) { mDataBase?.insertMovies(movies) }

    fun setLikeValue(movie: Movie) { mDataBase?.updateMovie(movie) }

    private fun updateDates() {
         mSharedPre!!.edit {
         this.putLong(mContext!!.getString(R.string.start_key),mStartTime)
         this.putLong(mContext!!.getString(R.string.end_key),mEndTime)
         commit()
     }
    }

    companion object{
        private val mSync = Any()
        private var mInstance : Repository? = null

        fun Instance() : Repository?
        {
            synchronized(mSync){
                return mInstance
            }
        }

        fun Instance(context: Context) : Repository
        {
            synchronized(mSync){
                if(mInstance == null)
                {
                    mInstance = Repository(context)
                }
                return mInstance!!
            }
        }

        fun Terminate()
        {
            synchronized(mSync){
                mInstance = null
            }
        }
    }


}