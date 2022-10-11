package com.tolganacar.rickmorty.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.tolganacar.rickmorty.R
import com.tolganacar.rickmorty.adapter.RMCharacterAdapter
import com.tolganacar.rickmorty.viewmodel.RMCharacterListVM
import kotlinx.android.synthetic.main.fragment_feed.*

class RMCharacterListFragment : Fragment() {

    private lateinit var viewModel: RMCharacterListVM
    private val rickMortyAdapter = RMCharacterAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeViewModel()

        initializeRecyclerview()

        observeLiveData()

        setSwipeRefreshLayout()
    }

    private fun observeLiveData(){
        viewModel.characterList.observe(viewLifecycleOwner, Observer { characters ->
            characters?.let {
                recyclerView.visibility = View.VISIBLE
                rickMortyAdapter.updateCharacterList(characters)
            }
        })

        viewModel.shouldShowErrorMessage.observe(viewLifecycleOwner, Observer { error ->
            error?.let {
                if(it){
                    feedErrorText.visibility = View.VISIBLE
                }else{
                    feedErrorText.visibility = View.GONE
                }
            }
        })

        viewModel.isLoading.observe(viewLifecycleOwner, Observer { loading ->
            loading?.let {
                if(it){
                    feedLoading.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                    feedErrorText.visibility = View.GONE
                }else{
                    feedLoading.visibility = View.GONE
                }
            }
        })
    }

    private fun initializeViewModel(){
        viewModel = ViewModelProviders.of(this).get(RMCharacterListVM::class.java)
        viewModel.getRMCharacterListFromAPI()
    }

    private fun initializeRecyclerview(){
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = rickMortyAdapter
    }

    private fun setSwipeRefreshLayout(){
        swipeRefreshLayout.setOnRefreshListener {
            recyclerView.visibility = View.GONE
            feedErrorText.visibility = View.GONE
            feedLoading.visibility = View.VISIBLE
            viewModel.getRMCharacterListFromAPI()
            swipeRefreshLayout.isRefreshing = false
        }
    }

}