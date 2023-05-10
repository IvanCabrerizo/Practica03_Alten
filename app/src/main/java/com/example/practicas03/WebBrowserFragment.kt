package com.example.practicas03

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practicas03.adapter.WebBrowserListAdapter
import com.example.practicas03.data.mockBrowser
import com.example.practicas03.databinding.FragmentWebBrowserBinding

class WebBrowserFragment : Fragment() {

    private val binding by lazy { FragmentWebBrowserBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val webBrowserRecyclerView = binding.attendanceListCalendar
        val webBrowserAdapter = WebBrowserListAdapter()

        webBrowserRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        webBrowserRecyclerView.adapter = webBrowserAdapter

        webBrowserAdapter.submitList(mockBrowser(10))
    }
}