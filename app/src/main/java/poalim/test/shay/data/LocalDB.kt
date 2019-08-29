package poalim.test.shay.data

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import poalim.test.shay.model.Movies

class LocalDB(context : Context)
{
    private var mDataBase : AppDatabase? = null

    init {
        mDataBase = Room.databaseBuilder(
            context,
            AppDatabase::class.java, "the_movies_db"
        ).build()
    }

    fun getMovieList(live : MutableLiveData<Movies>, startDate : String, endDate : String)
    {

    }

    companion object{

        private val mSync = Any()
        private var mInstance : LocalDB? = null

        fun Instance(context: Context) : LocalDB
        {
            synchronized(mSync){
                if(mInstance == null)
                {
                    mInstance = LocalDB(context)
                }
                return mInstance!!
            }
        }
    }
}