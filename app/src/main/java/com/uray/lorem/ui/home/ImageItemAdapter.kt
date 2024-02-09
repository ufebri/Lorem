package com.uray.lorem.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.uray.lorem.databinding.ItemNormalBinding
import febri.uray.bedboy.core.domain.model.Album

class ImageItemAdapter(private val onClick: (Album) -> Unit) :
    PagingDataAdapter<Album, ImageItemViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageItemViewHolder {
        return ImageItemViewHolder(
            ItemNormalBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ImageItemViewHolder, position: Int) {
        val mData = getItem(position)
        if (mData != null) itemCount.let { holder.bind(mData, onClick) }
    }


    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Album>() {
            override fun areItemsTheSame(
                oldItem: Album,
                newItem: Album
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: Album,
                newItem: Album
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}