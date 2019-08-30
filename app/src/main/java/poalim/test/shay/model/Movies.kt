package poalim.test.shay.model

import com.google.gson.annotations.SerializedName

class Movies
{
    @SerializedName("results")
    var mData : MutableList<Movie> = mutableListOf()

    fun getData(): List<Movie> {
        return mData
    }
}