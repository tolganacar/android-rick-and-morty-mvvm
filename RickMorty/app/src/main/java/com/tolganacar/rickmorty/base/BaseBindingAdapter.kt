package com.tolganacar.rickmorty.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

abstract class BaseBindingAdapter<T : ListAdapterItem>(
    diffCallback: DiffUtil.ItemCallback<T> = ListAdapterItemDiffCallback()
) : ListAdapter<T, BaseBindingViewHolder<T>>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseBindingViewHolder<T> {
        return BaseBindingViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                viewType,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BaseBindingViewHolder<T>, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

}