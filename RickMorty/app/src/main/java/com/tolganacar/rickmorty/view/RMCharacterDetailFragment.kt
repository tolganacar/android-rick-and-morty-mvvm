package com.tolganacar.rickmorty.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.tolganacar.rickmorty.R
import com.tolganacar.rickmorty.viewmodel.RMCharacterDetailViewModel

class RMCharacterDetailFragment : Fragment() {

    private lateinit var viewModel: RMCharacterDetailViewModel
    private var countryUuid = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            countryUuid = RMCharacterDetailFragmentArgs.fromBundle(it).countryUuid
        }

        viewModel = ViewModelProviders.of(this).get(RMCharacterDetailViewModel::class.java)
        viewModel.getCharacter()

        observeLiveData()
    }

    private fun observeLiveData(){
        viewModel.characterLiveData.observe(viewLifecycleOwner, Observer { character ->
            character?.let {

            }
        })
    }
}