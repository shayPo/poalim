package poalim.test.shay

import android.app.Application
import poalim.test.shay.data.Repository

class MovieApp : Application() {


    override fun onCreate() {
        super.onCreate()
        Repository.Instance(this.applicationContext)
    }

    override fun onTerminate() {
        super.onTerminate()
        Repository.Terminate()
    }
}