package com.bishwajeet.traveldiaries.view.profile

import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bishwajeet.traveldiaries.R
import com.bishwajeet.traveldiaries.data.model.ReviewsResponse
import com.bishwajeet.traveldiaries.interfaces.IAdapterClickListener

class PreviewAdapter(private var mContext: Context, private var mData: List<ReviewsResponse.Review>, private var mListener: IAdapterClickListener): RecyclerView.Adapter<PreviewAdapter.PreviewViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PreviewViewHolder {
        return PreviewViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_preview, parent, false))
    }


    override fun getItemCount(): Int {
        return mData.size
    }


    override fun onBindViewHolder(holder: PreviewViewHolder, position: Int) {
        holder.llPreview.setOnClickListener {
            mListener.onReviewClick(mData[position])
        }

        if(TextUtils.isEmpty(mData[position].title)) {
            var tempMessage = mData[position].message
            if(tempMessage.length >= 60) {
                tempMessage = tempMessage.subSequence(0, 60).toString()
            }
            holder.tvMessage.text = tempMessage
        } else {
            var tempTitle = mData[position].title
            if(tempTitle.length >= 60) {
                tempTitle = tempTitle.subSequence(0, 60).toString()
            }
            holder.tvMessage.text = tempTitle
        }

        if(mData[position].isAnonymous) {
            holder.tvName.text = mContext.getString(R.string.anonymous)
        } else {
            holder.tvName.text = mData[position].author.fullName
        }
    }


    class PreviewViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var llPreview: LinearLayout = v.findViewById(R.id.llPreview)
        var tvMessage: TextView = v.findViewById(R.id.tvMessage)
        var tvName: TextView = v.findViewById(R.id.tvName)
    }
}