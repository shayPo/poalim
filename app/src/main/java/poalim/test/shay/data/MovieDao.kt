package poalim.test.shay.data

import androidx.room.*
import poalim.test.shay.model.Movie

@Dao
interface MovieDao {


    @Query("SELECT * FROM movie ORDER BY release_date DESC")
    fun getAllDB() : List<Movie>

    @Query("SELECT * FROM movie WHERE is_favorite == :num")
    fun getByFavorites(num : Int) : List<Movie>

    @Query("SELECT * FROM movie WHERE release_date BETWEEN :dayst AND :dayet")
    fun getUpdatedMovieList(dayst : String, dayet : String) : List<Movie>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insert(movie : Movie)

    @Query("DELETE FROM movie WHERE release_date < :date_end AND is_favorite == 0")
    fun deleteOldMovies(date_end: String)

    @Query("UPDATE movie SET is_favorite = :favorite WHERE id = :id")
    fun updateMovie(id: Int, favorite: Int)

    @Query("SELECT * FROM movie WHERE id == :id")
    fun search(id : Int) : Movie?
}