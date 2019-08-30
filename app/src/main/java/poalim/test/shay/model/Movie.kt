package poalim.test.shay.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/*{"popularity":349.175,
"vote_count":420,
"video":false,
"poster_path":"\/a4BfxRK8dBgbQqbRxPs8kmLd8LG.jpg",
"id":429203,
"adult":false,
"backdrop_path":"\/8bRIfPGDnmWgdy65LO8xtdcFmFP.jpg",
"original_language":"en",
"original_title":"The Old Man & the Gun",
"genre_ids":[35,80,18],
"title":"The Old Man & the Gun",
"vote_average":6.4,
"overview":"The true story of Forrest Tucker, from his audacious escape from San Quentin at the age of 70 to an unprecedented string of heists that confounded authorities and enchanted the public. Wrapped up in the pursuit are a detective, who becomes captivated with Forrest’s commitment to his craft, and a woman, who loves him in spite of his chosen profession.",
"release_date":"2018-09-28"}*/
@Entity(tableName = "movie")
class Movie() : Parcelable {

    @SerializedName("poster_path")
    @ColumnInfo(name = "poster_path")
    var mPosterPath = ""

    @PrimaryKey
    @SerializedName("id")
    @ColumnInfo(name = "id")
    var mId: Int = 0

    @SerializedName("backdrop_path")
    @ColumnInfo(name = "backdrop_path")
    var mBackdropPath = ""


    @SerializedName("title")
    @ColumnInfo(name = "title")
    var mTitle = ""

    @SerializedName("overview")
    @ColumnInfo(name = "overview")
    var mOverview = ""

    @SerializedName("release_date")
    @ColumnInfo(name = "release_date")
    var mReleaseDate = ""

    @SerializedName("vote_average")
    @ColumnInfo(name = "vote_average")
    var mRating = ""

    @ColumnInfo(name = "is_favorite")
    var mFavorite = 0

    constructor(parcel: Parcel) : this() {
        mPosterPath = parcel.readString()
        mId = parcel.readInt()
        mBackdropPath = parcel.readString()
        mTitle = parcel.readString()
        mOverview = parcel.readString()
        mReleaseDate = parcel.readString()
        mRating = parcel.readString()
        mFavorite = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(mPosterPath + "")
        parcel.writeInt(mId)
        parcel.writeString(mBackdropPath + "")
        parcel.writeString(mTitle + "")
        parcel.writeString(mOverview + "")
        parcel.writeString(mReleaseDate + "")
        parcel.writeString(mRating + "")
        parcel.writeInt(mFavorite)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Movie> {
        override fun createFromParcel(parcel: Parcel): Movie {
            return Movie(parcel)
        }

        override fun newArray(size: Int): Array<Movie?> {
            return arrayOfNulls(size)
        }
    }
}