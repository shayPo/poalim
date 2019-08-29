package poalim.test.shay.data

import androidx.room.Database
import androidx.room.RoomDatabase
import poalim.test.shay.model.Movie

@Database(entities = arrayOf(Movie::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): MovieiDao
}