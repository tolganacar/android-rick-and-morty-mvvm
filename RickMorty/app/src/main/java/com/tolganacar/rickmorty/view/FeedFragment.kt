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
import com.tolganacar.rickmorty.adapter.RickMortyAdapter
import com.tolganacar.rickmorty.viewmodel.FeedViewModel
import kotlinx.android.synthetic.main.fragment_feed.*

class FeedFragment : Fragment() {

    private lateinit var viewModel: FeedViewModel
    private val rickMortyAdapter = RickMortyAdapter(arrayListOf())

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

        viewModel = ViewModelProviders.of(this).get(FeedViewModel::class.java)
        viewModel.refreshData()

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = rickMortyAdapter

        observeLiveData()

        swipeRefreshLayout.setOnRefreshListener {
            recyclerView.visibility = View.GONE
            feedErrorText.visibility = View.GONE
            feedLoading.visibility = View.VISIBLE
            viewModel.refreshData()
            swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun observeLiveData(){
        viewModel.characterList.observe(viewLifecycleOwner, Observer { characters ->
            characters?.let {
                recyclerView.visibility = View.VISIBLE
                rickMortyAdapter.updateCharacterList(characters)
            }
        })

        viewModel.errorMessage.observe(viewLifecycleOwner, Observer { error ->
            error?.let {
                if(it){
                    feedErrorText.visibility = View.VISIBLE
                }else{
                    feedErrorText.visibility = View.GONE
                }
            }
        })

        viewModel.loadingBar.observe(viewLifecycleOwner, Observer { loading ->
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

}