package poalim.test.shay.view.adapters

import poalim.test.shay.model.Movie

interface OnItemClickListener
{
    fun onItemClick(movie : Movie)

    fun onLikeClick(movie: Movie)
}