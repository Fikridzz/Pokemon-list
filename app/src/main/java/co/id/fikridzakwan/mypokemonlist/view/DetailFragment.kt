package co.id.fikridzakwan.mypokemonlist.view

import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import co.id.fikridzakwan.common.BaseFragment
import co.id.fikridzakwan.common.Resource
import co.id.fikridzakwan.domain.model.Pokemon
import co.id.fikridzakwan.mypokemonlist.R
import co.id.fikridzakwan.mypokemonlist.databinding.FragmentDetailBinding
import co.id.fikridzakwan.mypokemonlist.util.DialogSavePokemon
import co.id.fikridzakwan.presentation.viewmodel.DetailPokemonViewModel
import com.bumptech.glide.Glide
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : BaseFragment<FragmentDetailBinding>() {

    private val detailViewModel: DetailPokemonViewModel by viewModel()
    private val detailArgs: DetailFragmentArgs by navArgs()
    private var detailPokemon: Pokemon? = null
    private var isFavorite = false
    private val pokemonId get() = detailArgs.pokemonId

    override fun getViewBinding(): FragmentDetailBinding =
        FragmentDetailBinding.inflate(layoutInflater)

    override fun initUI() {
    }

    override fun initProcess() {
        detailViewModel.getDetailPokemonFromDb(pokemonId)
    }

    override fun initAction() {
        binding.fbSavePokemon.setOnClickListener {
            val randomNumber = (0..10).random()

            if (!isFavorite) {
                if (randomNumber % 2 == 0) {
                    setSaveDialog()
                } else {
                    Toast.makeText(requireContext(), "Gagal mendapatkan pokemon", Toast.LENGTH_SHORT).show()
                }
            } else {
                detailViewModel.deletePokemon(pokemonId)
            }
        }
    }

    override fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            detailViewModel.dataFromDb.collectLatest {
                when (it) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        if (it.data?.id != null) {
                            isFavorite = true
                            detailPokemon = it.data
                            populatePokemon(it.data)
                        } else {
                            detailViewModel.getDetailPokemon(pokemonId)
                        }
                    }
                    is Resource.Error -> {
                    }
                    else -> Unit
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            detailViewModel.pokemon.collectLatest {
                when (it) {
                    is Resource.Loading -> {
                        binding.groupDetail.visibility = View.GONE
                        binding.progressCircular.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {
                        binding.groupDetail.visibility = View.VISIBLE
                        binding.progressCircular.visibility = View.GONE
                        if (it.data != null) {
                            isFavorite = false
                            detailPokemon = it.data
                            populatePokemon(it.data)
                        }
                    }
                    is Resource.Error -> {
                        binding.groupDetail.visibility = View.VISIBLE
                        binding.progressCircular.visibility = View.GONE
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    }
                    else -> Unit
                }
            }
        }
    }

    private fun populatePokemon(data: Pokemon?) {
        binding.apply {
            tvPokemon.text = data?.name
            tvHeight.text = data?.height.toString()
            tvWeight.text = data?.weight.toString()
            tvTypes.text = data?.types
            tvSpecies.text = data?.species
            Glide.with(requireContext()).load(data?.imageUrl).into(imgPokemon)
            
            if (isFavorite) fbSavePokemon.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.egg_save_com))
            else fbSavePokemon.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.egg_com))
        }
    }

    private fun setSaveDialog() {
        DialogSavePokemon.newInstance {
            val newData = Pokemon(
                detailPokemon?.id,
                detailPokemon?.name,
                it,
                detailPokemon?.height,
                detailPokemon?.weight,
                detailPokemon?.species,
                detailPokemon?.types,
                detailPokemon?.imageUrl,
            )
            detailViewModel.savePokemon(newData)
        }.show(childFragmentManager, "")
    }
}