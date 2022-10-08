package com.tolganacar.rickmorty.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.tolganacar.rickmorty.databinding.RecyclerRowBinding
import com.tolganacar.rickmorty.model.Character
import com.tolganacar.rickmorty.util.downloadFromUrl
import com.tolganacar.rickmorty.util.placeholderProgressBar
import com.tolganacar.rickmorty.view.FeedFragmentDirections

class RickMortyAdapter(val characterList: ArrayList<Character>): RecyclerView.Adapter<RickMortyAdapter.RickMortyHolder>() {

    class RickMortyHolder(val binding : RecyclerRowBinding): RecyclerView.ViewHolder(binding.root){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RickMortyHolder {
        val binding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return RickMortyHolder(binding)
    }

    override fun onBindViewHolder(holder: RickMortyHolder, position: Int) {
        holder.binding.recyclerName.text = characterList.get(position).name
        holder.binding.recyclerStatus.text = characterList.get(position).status
        holder.binding.cardView.setOnClickListener {
            val action = FeedFragmentDirections.actionFeedFragmentToDetailsFragment()
            Navigation.findNavController(it).navigate(action)
        }
        holder.binding.recyclerImage.downloadFromUrl(
            characterList.get(position).image,
            placeholderProgressBar(holder.itemView.context))
    }

    override fun getItemCount(): Int {
        return characterList.size
    }

    fun updateCharacterList(newCharacterList: List<Character>){
        characterList.clear()
        characterList.addAll(newCharacterList)
        notifyDataSetChanged()
    }
}