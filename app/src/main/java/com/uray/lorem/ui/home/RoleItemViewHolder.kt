package com.uray.lorem.ui.home

import androidx.recyclerview.widget.RecyclerView
import com.uray.lorem.databinding.ItemAdminBinding
import febri.uray.bedboy.core.domain.model.User

class RoleItemViewHolder(private val binding: ItemAdminBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(mData: User, onClick: (User) -> Unit) {
        binding.apply {
            tvIdItem.text = mData.userID.toString()
            tvRole.text = mData.userRole
            tvEmail.text = mData.userEmail
            tvUsername.text = mData.userUsername
        }.root.setOnClickListener { onClick(mData) }
    }
}