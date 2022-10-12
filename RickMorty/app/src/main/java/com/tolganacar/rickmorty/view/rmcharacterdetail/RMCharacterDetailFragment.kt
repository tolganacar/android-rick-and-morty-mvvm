package com.tolganacar.rickmorty.view.rmcharacterdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.tolganacar.rickmorty.R
import com.tolganacar.rickmorty.databinding.FragmentDetailsBinding
import com.tolganacar.rickmorty.model.RMCharacter
import com.tolganacar.rickmorty.viewmodel.rmcharacterdetail.RMCharacterDetailViewModel
import kotlinx.android.synthetic.main.fragment_details.*

class RMCharacterDetailFragment : Fragment() {

    private lateinit var viewModel: RMCharacterDetailViewModel
    private var character : RMCharacter ?= null
    private lateinit var dataBinding: FragmentDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_details,container,false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setArguments()

        initializeViewModel()

        observeLiveData()
    }

    private fun observeLiveData(){
        viewModel.characterLiveData.observe(viewLifecycleOwner, Observer { character ->
            character?.let {
                dataBinding.selectedCharacter = character
            }
        })
    }

    private fun initializeViewModel(){
        viewModel = ViewModelProviders.of(this).get(RMCharacterDetailViewModel::class.java)
        character?.let { viewModel.setCharacter(it) }
    }

    private fun setArguments(){
        arguments?.let {
            character = RMCharacterDetailFragmentArgs.fromBundle(it).character
        }
    }
}