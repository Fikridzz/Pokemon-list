package co.id.fikridzakwan.mypokemonlist.view

import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import co.id.fikridzakwan.common.BaseFragment
import co.id.fikridzakwan.common.Resource
import co.id.fikridzakwan.mypokemonlist.adapter.PokemonAdapter
import co.id.fikridzakwan.mypokemonlist.databinding.FragmentSavedPokemonBinding
import co.id.fikridzakwan.presentation.viewmodel.ListSavePokemonViewModel
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel

class SavedPokemonFragment : BaseFragment<FragmentSavedPokemonBinding>() {

    private val listPokemonViewModel: ListSavePokemonViewModel by viewModel()

    private val pokemonAdapter: PokemonAdapter by lazy {
        PokemonAdapter(
            onItemClick = {
                val action = SavedPokemonFragmentDirections.actionSavedPokemonFragmentToDetailFragment(it)
                findNavController().navigate(action)
            },
            isFromList = false
        )
    }

    override fun getViewBinding(): FragmentSavedPokemonBinding = FragmentSavedPokemonBinding.inflate(layoutInflater)

    override fun initUI() {
        with(binding.rvPokemon) {
            adapter = pokemonAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
    }

    override fun initProcess() {
        listPokemonViewModel.getSavedPokemon()
    }

    override fun initAction() {
    }

    override fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            listPokemonViewModel.savedPokemon.collectLatest {
                when (it) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        if (it.data != null) {
                            pokemonAdapter.submitList(it.data)
                        }
                    }
                    is Resource.Error -> {}
                    else -> Unit
                }
            }
        }
    }
}