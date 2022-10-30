package com.tolganacar.rickmorty.view.rmcharacterlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tolganacar.rickmorty.databinding.RecyclerRowBinding
import com.tolganacar.rickmorty.model.RMCharacter

class RMCharacterAdapter(
    private val characterList: ArrayList<RMCharacter>
) : RecyclerView.Adapter<RMCharacterAdapter.RMCharacterViewHolder>() {

    private var listener: RMCharacterClickListener? = null

    class RMCharacterViewHolder(val binding: RecyclerRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RMCharacterViewHolder {
        val binding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RMCharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RMCharacterViewHolder, position: Int) {

        holder.binding.rmCharacter = characterList.get(position)
        holder.binding.cardView.setOnClickListener {
            listener?.onRMCharacterClicked(characterList.get(position))
        }
    }

    override fun getItemCount(): Int {
        return characterList.size
    }

    fun updateCharacterList(newCharacterList: List<RMCharacter>) {
        characterList.clear()
        characterList.addAll(newCharacterList)
        notifyDataSetChanged()
    }

    fun setOnClickListener(listener: RMCharacterClickListener) {
        this.listener = listener
    }
}