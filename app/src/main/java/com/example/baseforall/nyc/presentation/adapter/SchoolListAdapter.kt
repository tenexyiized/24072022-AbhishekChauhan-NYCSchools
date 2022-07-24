package com.example.baseforall.nyc.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.baseforall.nyc.data.models.SchoolListPlainResponse
import com.example.baseforall.nyc.presentation.viewholders.SchoolViewHolder

class SchoolListAdapter (val noteInteractor: SchoolInteractor) : ListAdapter<SchoolListPlainResponse, RecyclerView.ViewHolder>(BlockDiff){

    interface SchoolInteractor{
        fun schoolClicked(notes:SchoolListPlainResponse)
        fun onPhoneButtonClicked(phone:String?)
        fun onNavigate(lat:String?, long:String?)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SchoolViewHolder(parent,noteInteractor)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as SchoolViewHolder).bind(getItem(position) )
    }

    override fun submitList(list: List<SchoolListPlainResponse>?) {
        super.submitList(list?.let { ArrayList(it) })
    }

    object BlockDiff : DiffUtil.ItemCallback<SchoolListPlainResponse>() {
        override fun areItemsTheSame(oldItem: SchoolListPlainResponse, newItem: SchoolListPlainResponse): Boolean {
            return oldItem.dbn == newItem.dbn
        }

        override fun areContentsTheSame(oldItem: SchoolListPlainResponse, newItem: SchoolListPlainResponse) = oldItem == newItem

    }


}