package com.bishwajeet.traveldiaries.view.profile

import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bishwajeet.traveldiaries.R
import com.bishwajeet.traveldiaries.data.model.ReviewsResponse
import com.bishwajeet.traveldiaries.interfaces.IAdapterClickListener

class ReviewRecyclerAdapter(private var mContext: Context, private var reviewResponse: ReviewsResponse) : RecyclerView.Adapter<ReviewRecyclerAdapter.ViewHolder>() {

    private var mData: List<ReviewsResponse.Review>? = reviewResponse.reviews
    private var mListener: IAdapterClickListener = mContext as IAdapterClickListener


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            TYPE_HEADER -> HeaderViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_header, parent, false))
            TYPE_ITEM -> ReviewViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false))
            else -> FooterViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_footer, parent, false))
        }
    }


    override fun getItemCount(): Int {
        return mData?.size!!
    }


    override fun getItemViewType(position: Int): Int {
        return when {
            isPositionHeader(position) -> TYPE_HEADER
            isPositionItem(position) -> TYPE_ITEM
            else -> TYPE_FOOTER
        }
    }


    //Checks if given position is header or not
    private fun isPositionHeader(position: Int): Boolean {
        return position == 0
    }


    //Checks if given position is item or not
    private fun isPositionItem(position: Int): Boolean {
        return position < itemCount - 1
    }


    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        when {
            getItemViewType(position) == TYPE_HEADER -> run {
                val holder = viewHolder as HeaderViewHolder

                holder.ivProfile.setImageResource(R.drawable.tour_image)
                holder.ivShare.setOnClickListener {
                    mListener.onShareClick()
                }
                
                holder.ivBack.setOnClickListener {
                    mListener.onBackClick()
                }

                holder.ivLike.setOnClickListener {
                    holder.ivLike.setImageResource(R.drawable.ic_heart_filled)
                    mListener.onLikeClick(false)
                }

                holder.tvTourName.text = mContext.getString(R.string.tour_name)
                holder.tvTourLocation.text = mContext.getString(R.string.tour_location)
                holder.tvRating.text = mContext.getString(R.string.rated).plus(" ").plus(reviewResponse.averageRating)
                holder.tvReviewCount.text =reviewResponse.totalCount.toString().plus(" reviews")

                holder.rlPreview.visibility = View.VISIBLE

                val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                holder.rvPreview.layoutManager = staggeredGridLayoutManager

                val previewAdapter = PreviewAdapter(mContext, mData!!.subList(0, 6), mListener)
                holder.rvPreview.adapter = previewAdapter
            }
            getItemViewType(position) == TYPE_ITEM -> run {
                val holder = viewHolder as ReviewViewHolder

                val review :ReviewsResponse.Review = mData?.get(position)!!

                if(review.isAnonymous) {
                    holder.tvProfile.text = "A"
                    holder.tvName.text = mContext.getString(R.string.anonymous)
                } else {
                    holder.tvProfile.text = review.author.fullName[0].toString()
                    holder.tvName.text = review.author.fullName
                }
                
                holder.tvRating.text = review.rating.toString().plus("/5")

                if(TextUtils.isEmpty(review.travelerType)) {
                    holder.tvTravellerType.visibility = View.INVISIBLE
                } else {
                    holder.tvTravellerType.visibility = View.VISIBLE
                    holder.tvTravellerType.text = review.travelerType
                }

                if(TextUtils.isEmpty(review.title)) {
                    holder.tvMessage.text = review.message
                } else {
                    holder.tvMessage.text = review.title
                }

                holder.rlItemLayout.setOnClickListener {
                    mListener.onReviewClick(mData!![position])
                }

                holder.tvTimeAndPlace.text = review.created.subSequence(0, 10).toString().plus(", ").plus(review.author.country)
            }
            else -> {
                val holder = viewHolder as FooterViewHolder
                holder.pbLoader.visibility = View.VISIBLE
            }
        }
    }


    fun notifyOfDataSetChange(reviewList: List<ReviewsResponse.Review>) {
        val currentCount = itemCount
        val newReviewCount = reviewList.size
        mData = mData?.plus(reviewList)
        notifyItemRangeInserted(currentCount + 1, newReviewCount)
    }


    open class ViewHolder(v: View) : RecyclerView.ViewHolder(v)


    class HeaderViewHolder(@NonNull itemView: View) : ViewHolder(itemView) {

        var ivProfile: ImageView = itemView.findViewById(R.id.ivProfile)
        var ivShare: ImageView = itemView.findViewById(R.id.ivShare)
        var ivLike: ImageView = itemView.findViewById(R.id.ivLike)
        var ivBack: ImageView = itemView.findViewById(R.id.ivBack)
        var tvTourName: TextView = itemView.findViewById(R.id.tvTourName)
        var tvTourLocation: TextView = itemView.findViewById(R.id.tvTourLocation)
        var tvRating: TextView = itemView.findViewById(R.id.tvRating)
        var tvReviewCount: TextView = itemView.findViewById(R.id.tvReviewCount)
        var rlPreview: RelativeLayout = itemView.findViewById(R.id.rlPreview)
        var rvPreview: RecyclerView = itemView.findViewById(R.id.rvPreview)
    }


    class ReviewViewHolder(@NonNull itemView: View) : ViewHolder(itemView) {

        var rlItemLayout: RelativeLayout = itemView.findViewById(R.id.rlItemLayout)
        var tvProfile: TextView = itemView.findViewById(R.id.tvProfile)
        var tvName: TextView = itemView.findViewById(R.id.tvName)
        var tvRating: TextView = itemView.findViewById(R.id.tvRating)
        var tvTravellerType: TextView = itemView.findViewById(R.id.tvTravellerType)
        var tvMessage: TextView = itemView.findViewById(R.id.tvMessage)
        var tvTimeAndPlace: TextView = itemView.findViewById(R.id.tvTimeAndPlace)

    }


    class FooterViewHolder(@NonNull itemView: View) : ViewHolder(itemView) {

        var pbLoader: ProgressBar = itemView.findViewById(R.id.pbLoader)

    }


    companion object {
        const val TYPE_HEADER = 0
        const val TYPE_ITEM = 1
        const val TYPE_FOOTER = 2
    }
}