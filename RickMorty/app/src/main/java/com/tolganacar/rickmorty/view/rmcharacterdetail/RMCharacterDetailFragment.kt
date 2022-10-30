package com.tolganacar.rickmorty.view.rmcharacterdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.tolganacar.rickmorty.R
import com.tolganacar.rickmorty.databinding.FragmentDetailsBinding
import com.tolganacar.rickmorty.viewmodel.rmcharacterdetail.RMCharacterDetailViewModel

class RMCharacterDetailFragment : Fragment() {

    private lateinit var viewModel: RMCharacterDetailViewModel
    private lateinit var dataBinding: FragmentDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeViewModel()

        setArguments()
    }

    private fun initializeViewModel() {
        viewModel = ViewModelProviders.of(this).get(RMCharacterDetailViewModel::class.java)
        dataBinding.viewModel = viewModel
    }

    private fun setArguments() {
        arguments?.let {
            viewModel.setCharacter(RMCharacterDetailFragmentArgs.fromBundle(it).character)
        }
    }

}