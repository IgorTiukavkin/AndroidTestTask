package com.test.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.test.favorites.databinding.FavoritesItemLayoutBinding
import com.test.presentation.FavoriteItem
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.ConflatedBroadcastChannel

@ExperimentalCoroutinesApi
class FavoritesAdapter : RecyclerView.Adapter<FavoritesAdapter.ViewHolder>() {

    class ViewHolder (val binding: FavoritesItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    private var items: List<FavoriteItem> = emptyList()
    val onItemClick = BroadcastChannel<Int>(1)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FavoritesItemLayoutBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.binding.imageView.load(item.imageURL)
        holder.binding.titleText.text = item.title
        holder.itemView.setOnClickListener {
            onItemClick.offer(position)
        }
    }

    override fun getItemCount(): Int = items.count()

    fun update(items: List<FavoriteItem>) {
        this.items = items
        notifyDataSetChanged()
    }
}