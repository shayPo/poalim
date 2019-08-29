package poalim.test.shay.data

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import poalim.test.shay.R
import poalim.test.shay.data.Network
import poalim.test.shay.model.Movies
import java.text.SimpleDateFormat
import java.util.*

class Repository(context : Context)
{
    private var mSharedPre : SharedPreferences? = null
    private val mNetwork = Network.Instance()
    private var mDataBase : LocalDB? = null
    private var mStartTime : Long = 0
    private var mEndTime : Long = 0

    init {
        mDataBase = LocalDB.Instance(context)
        mSharedPre = context.getSharedPreferences("time",0)
        mStartTime = mSharedPre!!.getLong(context.getString(R.string.start_key), 0)
        mEndTime = mSharedPre!!.getLong(context.getString(R.string.end_key), 0)
    }

    fun getMovieList(live : MutableLiveData<Movies>)
    {
        val calendar = Calendar.getInstance()
        var date = calendar.time
        val format = SimpleDateFormat("yyyy-MM-dd")

        val endDate = format.format(date)
        calendar.add(Calendar.MONTH, -1)
        date = calendar.time
        var startDate = format.format(date)

        mNetwork!!.getMovieList(live, startDate, endDate)
        mDataBase!!.getMovieList(live, startDate, endDate)
    }


    companion object{
        private val mSync = Any()
        private var mInstance : Repository? = null

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
    }


}