package poalim.test.shay.model

import com.google.gson.annotations.SerializedName

class Movies
{
    @SerializedName("results")
    var mData : List<Movie> = emptyList()

    fun getData(): List<Movie> {
      return mData
    }
}