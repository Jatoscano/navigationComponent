package com.torres.myapplication.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.torres.myapplication.R
import com.torres.myapplication.databinding.ItemMarvelCharsBinding
import com.torres.myapplication.ui.entities.MarvelsCharsUI

class ListMarvelCharsAdapterDiffUtil: ListAdapter<MarvelsCharsUI,ListMarvelCharsAdapterDiffUtil.ListMarvelViewHolder>(DiffUtilMarvelCallBack) {

    class ListMarvelViewHolder(view: View) : RecyclerView.ViewHolder(view){

        private val binding = ItemMarvelCharsBinding.bind(view)
        fun render(item: MarvelsCharsUI){

            binding.imgMarvel.load(item.image)
            binding.txtMarvel.text = item.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListMarvelViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return ListMarvelViewHolder(inflater.inflate(R.layout.item_marvel_chars, parent, false))
    }

    override fun onBindViewHolder(holder: ListMarvelViewHolder, position: Int) {
        holder.render(getItem(position))
    }

    object DiffUtilMarvelCallBack : DiffUtil.ItemCallback<MarvelsCharsUI>(){
        override fun areItemsTheSame(oldItem: MarvelsCharsUI, newItem: MarvelsCharsUI): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MarvelsCharsUI, newItem: MarvelsCharsUI): Boolean {
            return oldItem == newItem
        }
    }
}