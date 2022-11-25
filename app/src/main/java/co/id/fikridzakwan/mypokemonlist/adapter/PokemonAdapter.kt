package co.id.fikridzakwan.mypokemonlist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import co.id.fikridzakwan.domain.model.ListPokemon
import co.id.fikridzakwan.mypokemonlist.databinding.ItemPokemonBinding
import com.bumptech.glide.Glide

class PokemonAdapter(private val onItemClick: (id: Int) -> Unit, private val isFromList: Boolean) :
    ListAdapter<ListPokemon, PokemonAdapter.ViewHolder>(pokemonCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemPokemonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemPokemonBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ListPokemon) {
            binding.tvPokemon.text = data.name
            Glide.with(itemView.context).load(data.imageUrl).into(binding.imgPokemon)
            itemView.setOnClickListener {
                onItemClick(data.id ?: 0)
            }

            if (!isFromList) {
                binding.tvNickname.visibility = View.VISIBLE
                binding.tvNickname.text = data.nickname
            } else binding.tvNickname.visibility = View.GONE
        }
    }

    companion object {
        val pokemonCallback = object : DiffUtil.ItemCallback<ListPokemon>() {
            override fun areItemsTheSame(oldItem: ListPokemon, newItem: ListPokemon): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: ListPokemon, newItem: ListPokemon): Boolean {
                return oldItem == newItem
            }
        }
    }
}