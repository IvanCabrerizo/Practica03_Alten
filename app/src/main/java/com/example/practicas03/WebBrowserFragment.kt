package com.example.practicas03

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practicas03.adapter.WebBrowserListAdapter
import com.example.practicas03.data.mockBrowser
import com.example.practicas03.databinding.FragmentWebBrowserBinding

class WebBrowserFragment : Fragment(), MenuProvider {

    private val binding by lazy { FragmentWebBrowserBinding.inflate(layoutInflater) }
    private val webBrowserAdapter = WebBrowserListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val activityInUse = requireActivity() as? AppCompatActivity
        activityInUse?.setSupportActionBar(binding.webBrowserFragmentToolBar)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
        val webBrowserRecyclerView = binding.attendanceListCalendar

        webBrowserRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        webBrowserRecyclerView.adapter = webBrowserAdapter

        webBrowserAdapter.submitList(mockBrowser(10))
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.web_browser_fragment_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.webBrowserFragmentMenuOrder -> {
                orderBrowser(webBrowserAdapter)
                true
            }

            R.id.webBrowserFragmentMenuFilter -> {

                true
            }

            else -> false
        }
    }

    private fun orderBrowser(adapter: WebBrowserListAdapter) {
        val listOption = listOf("Nombre", "Compañia", "Año")
        val orderDialog = SingleOptionDialog(requireContext(), listOption)

        orderDialog.setOptionListener(object : SingleOptionDialog.OptionListener {
            override fun optionSelected(option: String?) {
                val sortedList = when (option) {
                    "Nombre" -> adapter.currentList.sortedBy { it.name }
                    "Compañia" -> adapter.currentList.sortedBy { it.company }
                    "Año" -> adapter.currentList.sortedBy { it.year }
                    else -> adapter.currentList
                }
                adapter.submitList(sortedList)
            }
        })

        orderDialog.show()
    }
}