package com.example.baseforall.nyc.presentation.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.baseforall.R
import com.example.baseforall.nyc.data.models.SchoolListPlainResponse
import com.example.baseforall.nyc.presentation.adapter.SchoolListAdapter

class SchoolViewHolder (
    viewGroup: ViewGroup,
    val schoolInteractor: SchoolListAdapter.SchoolInteractor,
): RecyclerView.ViewHolder(
    LayoutInflater
        .from(viewGroup.context)
        .inflate(R.layout.item_school, viewGroup, false)
)
{

    fun bind(item: SchoolListPlainResponse){
        itemView.findViewById<TextView>(R.id.name).text = item.school_name
        itemView.findViewById<TextView>(R.id.address).text = item.location!!.substring(0, item.location!!.indexOf("("))
        itemView.findViewById<TextView>(R.id.website).text = item.website
        itemView.setOnClickListener {
            schoolInteractor.schoolClicked(item)
        }
        itemView.findViewById<Button>(R.id.phone).setOnClickListener{
            schoolInteractor.onPhoneButtonClicked(item.phone_number)
        }
        itemView.findViewById<Button>(R.id.navigate).setOnClickListener{
            schoolInteractor.onNavigate(item.latitude, item.longitude)
        }
    }

}