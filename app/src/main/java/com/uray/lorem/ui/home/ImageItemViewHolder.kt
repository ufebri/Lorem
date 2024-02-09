package com.uray.lorem.ui.home

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.uray.lorem.databinding.ItemNormalBinding
import febri.uray.bedboy.core.domain.model.Album

class ImageItemViewHolder(private val binding: ItemNormalBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(mData: Album, onClick: (Album) -> Unit) {
        binding.apply {
            tvTitle.text = mData.title
            Glide.with(root).load(mData.thumbnailUrl).into(ivImg)
            tvAlbumID.text = mData.id.toString()
        }.root.setOnClickListener { onClick(mData) }
    }
}