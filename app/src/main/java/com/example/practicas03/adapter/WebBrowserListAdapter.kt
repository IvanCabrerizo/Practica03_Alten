package com.example.practicas03.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.practicas03.data.model.WebBrowserBo
import com.example.practicas03.databinding.ListBrowserItemBinding

class WebBrowserListAdapter :
    ListAdapter<WebBrowserBo, WebBrowserListAdapter.WebBrowserBoViewHolder>(
        WebBrowserDiffUtilCallBack()
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WebBrowserBoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listBrowserItemBinding = ListBrowserItemBinding.inflate(layoutInflater, parent, false)
        return WebBrowserBoViewHolder(listBrowserItemBinding)
    }

    override fun onBindViewHolder(
        holder: WebBrowserListAdapter.WebBrowserBoViewHolder,
        position: Int
    ) {
        holder.bind(getItem(position))
    }

    inner class WebBrowserBoViewHolder(listBrowserBinding: ListBrowserItemBinding) :
        RecyclerView.ViewHolder(listBrowserBinding.root) {
        fun bind(item: WebBrowserBo) {

        }
    }

    private class WebBrowserDiffUtilCallBack : DiffUtil.ItemCallback<WebBrowserBo>() {
        override fun areItemsTheSame(oldItem: WebBrowserBo, newItem: WebBrowserBo): Boolean {
            TODO("Not yet implemented")
        }

        override fun areContentsTheSame(oldItem: WebBrowserBo, newItem: WebBrowserBo): Boolean {
            TODO("Not yet implemented")
        }
    }
}