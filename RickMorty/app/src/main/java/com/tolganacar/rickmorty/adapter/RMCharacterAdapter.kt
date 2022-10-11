package com.tolganacar.rickmorty.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.tolganacar.rickmorty.databinding.RecyclerRowBinding
import com.tolganacar.rickmorty.model.RMCharacter
import com.tolganacar.rickmorty.util.downloadFromUrl
import com.tolganacar.rickmorty.util.placeholderProgressBar
import com.tolganacar.rickmorty.view.RMCharacterListFragmentDirections

class RMCharacterAdapter(val characterList: ArrayList<RMCharacter>): RecyclerView.Adapter<RMCharacterAdapter.RMCharacterViewHolder>() {

    class RMCharacterViewHolder(val binding : RecyclerRowBinding): RecyclerView.ViewHolder(binding.root){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RMCharacterViewHolder {
        val binding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return RMCharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RMCharacterViewHolder, position: Int) {
        holder.binding.apply {
            recyclerName.text = characterList.get(position).name
            recyclerStatus.text = characterList.get(position).status

            cardView.setOnClickListener {
                val action = RMCharacterListFragmentDirections.actionFeedFragmentToDetailsFragment()
                Navigation.findNavController(it).navigate(action)
            }

            recyclerImage.downloadFromUrl(
                characterList.get(position).image,
                placeholderProgressBar(holder.itemView.context))
        }
    }

    override fun getItemCount(): Int {
        return characterList.size
    }

    fun updateCharacterList(newCharacterList: List<RMCharacter>){
        characterList.clear()
        characterList.addAll(newCharacterList)
        notifyDataSetChanged()
    }
}