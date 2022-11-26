package com.tolganacar.rickmorty.view.rmlocationdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.tolganacar.rickmorty.R
import com.tolganacar.rickmorty.databinding.FragmentLocationDetailBinding
import com.tolganacar.rickmorty.viewmodel.rmlocationdetail.RMLocationDetailViewModel

class RMLocationDetailFragment : Fragment() {

    private lateinit var viewModel: RMLocationDetailViewModel
    private lateinit var dataBinding: FragmentLocationDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_location_detail, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeViewModel()

        setArguments()
    }

    private fun initializeViewModel() {
        viewModel = ViewModelProviders.of(this).get(RMLocationDetailViewModel::class.java)
        dataBinding.viewModel = viewModel
    }

    private fun setArguments() {
        arguments?.let {
            viewModel.setLocation(RMLocationDetailFragmentArgs.fromBundle(it).location)
        }
    }


}