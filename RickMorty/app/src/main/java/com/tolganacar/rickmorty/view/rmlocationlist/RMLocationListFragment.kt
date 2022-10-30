package com.tolganacar.rickmorty.view.rmlocationlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.tolganacar.rickmorty.R
import com.tolganacar.rickmorty.model.RMLocationResponseModel
import com.tolganacar.rickmorty.viewmodel.rmlocationlist.RMLocationListViewModel
import kotlinx.android.synthetic.main.fragment_location_list.*

class RMLocationListFragment : Fragment(), RMLocationClickListener {

    private lateinit var viewModel: RMLocationListViewModel
    private val rmLocationAdapter = RMLocationAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_location_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeViewModel()

        initializeRecyclerView()

        observeLiveData()

        setSwipeRefreshLayout()
    }

    private fun initializeViewModel() {
        viewModel = ViewModelProviders.of(this).get(RMLocationListViewModel::class.java)
        viewModel.getRMLocationListFromAPI()
    }

    private fun initializeRecyclerView() {
        recyclerViewLocationList.layoutManager = GridLayoutManager(context, 2)
        recyclerViewLocationList.adapter = rmLocationAdapter.apply {
            setOnClickListener(this@RMLocationListFragment)
        }
    }

    private fun observeLiveData() {
        viewModel.locations.observe(viewLifecycleOwner, Observer { locations ->
            locations?.let {
                recyclerViewLocationList.visibility = View.VISIBLE
                rmLocationAdapter.updateLocationList(locations)
            }

            viewModel.shouldShowLocationErrorMessage.observe(viewLifecycleOwner, Observer { error ->
                error?.let {
                    if (it) {
                        errorTextLocationList.visibility = View.VISIBLE
                    } else {
                        errorTextLocationList.visibility = View.GONE
                    }
                }
            })

            viewModel.isLoadingLocation.observe(viewLifecycleOwner, Observer { loading ->
                loading?.let {
                    if (it) {
                        loadingLocationList.visibility = View.VISIBLE
                        recyclerViewLocationList.visibility = View.GONE
                        errorTextLocationList.visibility = View.GONE
                    } else {
                        loadingLocationList.visibility = View.GONE
                    }
                }
            })
        })
    }

    private fun setSwipeRefreshLayout() {
        recyclerViewLocationList.visibility = View.GONE
        errorTextLocationList.visibility = View.GONE
        loadingLocationList.visibility = View.VISIBLE
        viewModel.getRMLocationListFromAPI()
        swipeRefreshLayoutLocationList.isRefreshing = false
    }

    override fun onRMLocationClicked(location: RMLocationResponseModel) {
        val action =
            RMLocationListFragmentDirections.actionLocationListFragmentToRMLocationDetailFragment(
                location
            )
        findNavController().navigate(action)
    }


}