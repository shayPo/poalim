package poalim.test.shay

import android.app.Application
import poalim.test.shay.view.model.Network


class App : Application() {

    override fun onCreate() {
        super.onCreate()
    }


    init {
        val network = Network.Instance()
    }
}