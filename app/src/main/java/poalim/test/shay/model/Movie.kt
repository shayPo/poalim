package poalim.test.shay.model

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
class Movie
{
    @SerializedName("poster_path")
    var mPosterPath = ""
    @SerializedName("id")
    var mId : Int = 0
    @SerializedName("backdrop_path")
    var mBackdropPath = ""
    @SerializedName("title")
    var mTitle = ""
    @SerializedName("overview")
    var mOverview = ""
    @SerializedName("release_date")
    var mReleaseDate = ""
}