package co.id.fikridzakwan.mypokemonlist.view

import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import co.id.fikridzakwan.common.BaseFragment
import co.id.fikridzakwan.common.Resource
import co.id.fikridzakwan.mypokemonlist.adapter.PokemonAdapter
import co.id.fikridzakwan.mypokemonlist.R
import co.id.fikridzakwan.mypokemonlist.databinding.FragmentListBinding
import co.id.fikridzakwan.presentation.viewmodel.ListPokemonViewModel
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListFragment : BaseFragment<FragmentListBinding>() {

    private val listPokemonViewModel: ListPokemonViewModel by viewModel()

    private val pokemonAdapter: PokemonAdapter by lazy {
        PokemonAdapter(
            onItemClick = {
                val action = ListFragmentDirections.actionListFragmentToDetailFragment(it)
                findNavController().navigate(action)
            },
            isFromList = true
        )
    }

    override fun getViewBinding(): FragmentListBinding = FragmentListBinding.inflate(layoutInflater)

    override fun initUI() {
        with(binding.rvPokemon) {
            adapter = pokemonAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }

    override fun initProcess() {
        listPokemonViewModel.getListPokemon(0)
    }

    override fun initAction() {
        binding.fbSavedPokemon.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_savedPokemonFragment)
        }
    }

    override fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            listPokemonViewModel.listPokemon.collectLatest {
                when (it) {
                    is Resource.Loading -> {
                        loadingState(true)
                    }
                    is Resource.Success -> {
                        loadingState(false)
                        pokemonAdapter.submitList(it.data)
                    }
                    is Resource.Error -> {
                        loadingState(false)
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    }
                    else -> Unit
                }
            }
        }
    }

    private fun loadingState(state: Boolean) {
        binding.apply {
            fbSavedPokemon.isVisible = !state
            rvPokemon.isVisible = !state
            progressCircular.isVisible = state
        }
    }
}