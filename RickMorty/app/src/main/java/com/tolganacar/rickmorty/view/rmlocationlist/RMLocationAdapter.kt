package com.tolganacar.rickmorty.view.rmlocationlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tolganacar.rickmorty.databinding.RecyclerRowLocationListBinding
import com.tolganacar.rickmorty.model.RMLocationResponseModel

class RMLocationAdapter(val locationList: ArrayList<RMLocationResponseModel>) :
    RecyclerView.Adapter<RMLocationAdapter.RMLocationViewHolder>() {

    private var listener: RMLocationClickListener? = null

    class RMLocationViewHolder(val binding: RecyclerRowLocationListBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RMLocationViewHolder {
        val binding = RecyclerRowLocationListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RMLocationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RMLocationViewHolder, position: Int) {
        holder.binding.location = locationList.get(position)
        holder.binding.cardViewLocationList.setOnClickListener {
            listener?.onRMLocationClicked(locationList.get(position))
        }
    }

    override fun getItemCount(): Int {
        return locationList.size
    }

    fun updateLocationList(newLocationList: List<RMLocationResponseModel>) {
        locationList.clear()
        locationList.addAll(newLocationList)
        notifyDataSetChanged()
    }

    fun setOnClickListener(listener: RMLocationClickListener) {
        this.listener = listener
    }
}