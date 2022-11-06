package com.tolganacar.rickmorty.view.rmcharacterlist

import com.tolganacar.rickmorty.base.BaseBindingAdapter
import com.tolganacar.rickmorty.base.BaseBindingViewHolder
import com.tolganacar.rickmorty.databinding.RecyclerRowBinding
import com.tolganacar.rickmorty.model.RMCharacter

class RMCharacterAdapter : BaseBindingAdapter<RMCharacter>() {

    private var listener: RMCharacterClickListener? = null

    override fun onBindViewHolder(
        holder: BaseBindingViewHolder<RMCharacter>,
        position: Int
    ) {
        super.onBindViewHolder(holder, position)

        holder.binding as RecyclerRowBinding
        val character = getItem(position)
        holder.binding.item = character
        holder.binding.cardView.setOnClickListener {
            character?.let { character ->
                listener?.onRMCharacterClicked(character)
            }
        }
    }

    fun setOnClickListener(listener: RMCharacterClickListener) {
        this.listener = listener
    }
}