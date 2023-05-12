package com.example.practicas03

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AlertDialog
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
    private val chipGroupOS by lazy { binding.webBrowserFragmentChipGroupFilter }

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
                orderBrowser(
                    requireContext(),
                    listOf("Nombre", "Compañia", "Año")
                ) { option ->
                    val sortedList = when (option) {
                        "Nombre" -> webBrowserAdapter.currentList.sortedBy { it.name }
                        "Compañia" -> webBrowserAdapter.currentList.sortedBy { it.company }
                        "Año" -> webBrowserAdapter.currentList.sortedBy { it.year }
                        else -> webBrowserAdapter.currentList
                    }
                    webBrowserAdapter.submitList(sortedList)
                }
                true
            }

            R.id.webBrowserFragmentMenuFilter -> {

                true
            }

            else -> false
        }
    }

    private fun orderBrowser(
        context: Context,
        listOption: List<String>,
        optionCallback: (String?) -> Unit
    ) {
        var option: String?

        val builder = AlertDialog.Builder(context)
        builder.setTitle("Seleccionar opción")

        val radioGroup = RadioGroup(context)
        radioGroup.orientation = RadioGroup.VERTICAL

        for (text in listOption) {
            val radioButton = RadioButton(context)
            radioButton.text = text
            radioGroup.addView(radioButton)
        }

        builder.setView(radioGroup)

        builder.setPositiveButton("Aceptar") { _, _ ->
            val selectedRadioButton =
                radioGroup.findViewById<RadioButton>(radioGroup.checkedRadioButtonId)
            option = selectedRadioButton?.text?.toString()
            optionCallback(option)
        }

        builder.setNegativeButton("Cancelar") { _, _ ->
            option = null
            optionCallback(option)
        }

        val alertDialog = builder.create()
        alertDialog.show()
    }

    /*    private fun chipGenerator(context: Context, chipGroup: ChipGroup) {
            val compatibleList = listOf<CompatibleOperatingSystems>(
                CompatibleOperatingSystems.WINDOWS,
                CompatibleOperatingSystems.MAC,
                CompatibleOperatingSystems.LINUX,
                CompatibleOperatingSystems.ANDROID,
                CompatibleOperatingSystems.WINDOWS_PHONE,
                CompatibleOperatingSystems.IOS,
            )
            for (os in compatibleList) {
                val chip = Chip(context)
                chip.text = os.operatingSystem
                chipGroup.addView(chip)
            }
        }*/
}