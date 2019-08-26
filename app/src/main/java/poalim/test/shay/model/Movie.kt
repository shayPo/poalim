package poalim.test.shay.model


class Movie(imageURL : String, title: String)
{
    lateinit var mImageUrl : String
    lateinit var mTitle : String


    init{
        mImageUrl = imageURL
        mTitle = title
    }
}