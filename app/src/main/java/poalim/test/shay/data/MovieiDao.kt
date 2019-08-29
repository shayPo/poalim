package poalim.test.shay.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import poalim.test.shay.model.Movie
import poalim.test.shay.model.Movies

@Dao
interface MovieiDao {
    /* @Query("SELECT * FROM user")
        fun getAll(): List<User>

        @Query("SELECT * FROM user WHERE uid IN (:userIds)")
        fun loadAllByIds(userIds: IntArray): List<User>

        @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
               "last_name LIKE :last LIMIT 1")
        fun findByName(first: String, last: String): User

        @Insert
        fun insertAll(vararg users: User)

        @Delete
        fun delete(user: User)*/

    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    fun getUpdatedMovieList(first: String, last: String) : Movies

    @Insert
    fun insertAll(movies: List<Movie>)

    @Delete
    fun delete(movie: Movie)
}