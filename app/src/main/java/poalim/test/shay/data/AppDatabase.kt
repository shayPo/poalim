package poalim.test.shay.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import poalim.test.shay.model.Movie

@Database(entities = arrayOf(Movie::class), version = 2)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): MovieDao

    override fun clearAllTables() {

    }

    companion object{
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {

            }
        }
    }
}