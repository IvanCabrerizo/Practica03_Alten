package com.example.practicas03.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.practicas03.R
import com.example.practicas03.data.model.WebBrowserBo
import com.example.practicas03.databinding.ListBrowserItemBinding
import com.example.practicas03.imageUrl

class WebBrowserListAdapter :
    ListAdapter<WebBrowserBo, WebBrowserListAdapter.WebBrowserBoViewHolder>(
        WebBrowserDiffUtilCallBack
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

    inner class WebBrowserBoViewHolder(private val listBrowserBinding: ListBrowserItemBinding) :
        RecyclerView.ViewHolder(listBrowserBinding.root) {
        fun bind(item: WebBrowserBo) {
            with(listBrowserBinding) {
                listBrowserItemImgBrowserIcon.imageUrl(item.logo)
                listBrowserItemLabelName.text = item.name
                listBrowserItemLabelYear.text = item.year.toString()
                listBrowserItemLabelCompany.text = item.company

                if (item.mobile) {
                    listBrowserItemImgMobileIcon.visibility = View.VISIBLE
                } else {
                    listBrowserItemImgMobileIcon.visibility = View.GONE
                }
                listBrowserItemImgWebIcon.setOnClickListener {
                    val browserUrl = item.web
                    val browserName = item.name
                    val bundle = Bundle()

                    with(bundle) {
                        putString("browserName", browserName)
                        putString("browserUrl", browserUrl)
                    }

                    itemView.findNavController().navigate(
                        R.id.action_webBrowserFragment_to_browserViewerFragment,
                        bundle
                    )
                }
            }
        }
    }

    private object WebBrowserDiffUtilCallBack : DiffUtil.ItemCallback<WebBrowserBo>() {
        override fun areItemsTheSame(oldItem: WebBrowserBo, newItem: WebBrowserBo): Boolean =
            oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: WebBrowserBo, newItem: WebBrowserBo): Boolean =
            oldItem == newItem
    }
}