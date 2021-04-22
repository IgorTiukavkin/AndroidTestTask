package com.test.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.test.search.databinding.SearchListItemBinding

class SearchListAdapter: RecyclerView.Adapter<SearchListAdapter.ViewHolder>() {

    class ViewHolder (val binding: SearchListItemBinding) : RecyclerView.ViewHolder(binding.root)

    private var items: List<AlbumListItem> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = SearchListItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        val context = holder.itemView.context
        holder.binding.imageView.load(item.imageUrl)
        holder.binding.textView.text = item.name
        if (item.isFavorite) {
            holder.binding.isFavoriteImageView.setImageResource(R.drawable.ic_star_selected)
            holder.binding.isFavoriteImageView.contentDescription = context.getString(R.string.favorite_selected)
        } else {
            holder.binding.isFavoriteImageView.setImageResource(R.drawable.ic_star_unselected)
            holder.binding.isFavoriteImageView.contentDescription = context.getString(R.string.favorite_unselected)
        }
    }

    override fun getItemCount(): Int = items.count()

    fun update(items: List<AlbumListItem>) {
        this.items = items
        notifyDataSetChanged()
    }
}