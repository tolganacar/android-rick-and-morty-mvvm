package com.tolganacar.rickmorty.view.rmcharacterlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tolganacar.rickmorty.R
import com.tolganacar.rickmorty.model.RMCharacter
import com.tolganacar.rickmorty.viewmodel.rmcharacterlist.RMCharacterListVM
import kotlinx.android.synthetic.main.fragment_feed.*

class RMCharacterListFragment : Fragment(), RMCharacterClickListener {

    private lateinit var viewModel: RMCharacterListVM
    private val rickMortyAdapter = RMCharacterAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeViewModel()

        initializeRecyclerview()

        observeLiveData()

        setSwipeRefreshLayout()

        setSearchListener()
    }

    private fun initializeViewModel() {
        viewModel = ViewModelProviders.of(this).get(RMCharacterListVM::class.java)
        viewModel.getRMCharacterListFromAPI()
    }

    private fun initializeRecyclerview() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = rickMortyAdapter.apply {
            setOnClickListener(this@RMCharacterListFragment)
        }
    }

    private fun observeLiveData() {
        viewModel.characterList.observe(viewLifecycleOwner, Observer { characters ->
            characters?.let {
                recyclerView.visibility = View.VISIBLE
                rickMortyAdapter.updateCharacterList(characters)
            }
        })

        viewModel.shouldShowErrorMessage.observe(viewLifecycleOwner, Observer { error ->
            error?.let {
                if (it) {
                    feedErrorText.visibility = View.VISIBLE
                } else {
                    feedErrorText.visibility = View.GONE
                }
            }
        })

        viewModel.isLoading.observe(viewLifecycleOwner, Observer { loading ->
            loading?.let {
                if (it) {
                    feedLoading.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                    feedErrorText.visibility = View.GONE
                } else {
                    feedLoading.visibility = View.GONE
                }
            }
        })

        viewModel.filteredCharacterList.observe(viewLifecycleOwner, Observer {
            rickMortyAdapter.updateCharacterList(it)
        })
    }

    private fun setSwipeRefreshLayout() {
        swipeRefreshLayout.setOnRefreshListener {
            recyclerView.visibility = View.GONE
            feedErrorText.visibility = View.GONE
            feedLoading.visibility = View.VISIBLE
            viewModel.getRMCharacterListFromAPI()
            swipeRefreshLayout.isRefreshing = false
        }
    }

    override fun onRMCharacterClicked(character: RMCharacter) {
        val action =
            RMCharacterListFragmentDirections.actionFeedFragmentToDetailsFragment(character)
        findNavController().navigate(action)
    }

    private fun setSearchListener() {
        searchView.clearFocus()
        searchView.setOnQueryTextListener(object : android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { viewModel.filterCharacterList(it) }
                return true
            }

        })
    }
}