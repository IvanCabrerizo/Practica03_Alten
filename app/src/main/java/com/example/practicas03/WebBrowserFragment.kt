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
import com.example.practicas03.data.model.CompatibleOperatingSystems
import com.example.practicas03.databinding.FragmentWebBrowserBinding
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class WebBrowserFragment : Fragment(), MenuProvider {

    private val binding by lazy { FragmentWebBrowserBinding.inflate(layoutInflater) }
    private val webBrowserAdapter = WebBrowserListAdapter()
    private val chipGroupOS by lazy { binding.webBrowserFragmentChipGroupFilter }
    private val listOS = CompatibleOperatingSystems.values().map { it.operatingSystem }
    private val oSSelected = listOS.map { false }.toBooleanArray()
    private val originalList = mockBrowser(10)
    private val chipsCreated = mutableListOf<Chip>()
    private var orderMemory: String? = null

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
        val webBrowserRecyclerView = binding.webBrowserFragmentListBrowsers

        webBrowserRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        webBrowserRecyclerView.adapter = webBrowserAdapter

        webBrowserAdapter.submitList(originalList)
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.web_browser_fragment_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.webBrowserFragmentMenuOrder -> {
                orderDialog(
                    requireContext(),
                    listOf("Nombre", "Compañia", "Año")
                ) { option ->
                    orderBrowser(option)
                }
                true
            }

            R.id.webBrowserFragmentMenuFilter -> {
                filterDialog(
                    requireContext(),
                    listOS
                ) { options ->
                    chipGenerator(requireContext(), chipGroupOS, options)
                }
                true
            }

            else -> false
        }
    }

    private fun orderBrowser(option: String?) {
        orderMemory = option
        val sortedList = when (option) {
            "Nombre" -> webBrowserAdapter.currentList.sortedBy { it.name }
            "Compañia" -> webBrowserAdapter.currentList.sortedBy { it.company }
            "Año" -> webBrowserAdapter.currentList.sortedBy { it.year }
            else -> webBrowserAdapter.currentList
        }
        webBrowserAdapter.submitList(sortedList)
    }

    private fun orderDialog(
        context: Context,
        listOption: List<String>,
        optionCallback: (String?) -> Unit
    ) {
        var option: String?

        val builder = AlertDialog.Builder(context)
        builder.setTitle(getString(R.string.WebBrowserOrderDialogTittle))

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

    private fun filterDialog(
        context: Context,
        options: List<String>,
        selectionCallback: (List<String>) -> Unit
    ) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(getString(R.string.WebBrowserFilterDialogTittle))

        builder.setMultiChoiceItems(
            options.toTypedArray(),
            oSSelected
        ) { _, which, isChecked ->
            oSSelected[which] = isChecked
        }

        builder.setPositiveButton("Aceptar") { _, _ ->
            val selectedOptions = options.filterIndexed { index, _ ->
                oSSelected[index]
            }
            selectionCallback(selectedOptions)
            applyFilters()
        }

        builder.setNegativeButton("Cancelar") { _, _ ->
            selectionCallback(emptyList())
        }
        val alertDialog = builder.create()
        alertDialog.show()
    }

    private fun chipGenerator(context: Context, chipGroup: ChipGroup, listOs: List<String>) {
        for (os in listOs) {
            val existingChip = chipsCreated.find { chip -> chip.text == os }
            if (existingChip == null) {
                val chip = Chip(context)
                chip.text = os
                chipGroup.addView(chip)
                chipsCreated.add(chip)
                chip.setOnClickListener {
                    chipGroup.removeView(chip)
                    chipsCreated.remove(chip)
                    val chipName = chip.text.toString()
                    val chipPosition = listOS.indexOf(chipName)
                    if (chipPosition != -1) {
                        oSSelected[chipPosition] = false
                    }
                    applyFilters()
                }
            }
        }
    }

    private fun applyFilters() {
        val selectedOperatingSystems = listOS.filterIndexed { index, _ -> oSSelected[index] }
        val filteredList = originalList.filter { browser ->
            browser.compatible.map { it.operatingSystem }.containsAll(selectedOperatingSystems)
        }

        if (orderMemory != null) {
            val sortedList = when (orderMemory) {
                "Nombre" -> filteredList.sortedBy { it.name }
                "Compañia" -> filteredList.sortedBy { it.company }
                "Año" -> filteredList.sortedBy { it.year }
                else -> filteredList
            }
            webBrowserAdapter.submitList(sortedList)
        } else {
            webBrowserAdapter.submitList(filteredList)
        }

        val selectedChips = chipsCreated.filter { chip ->
            chip.text in selectedOperatingSystems
        }
        chipGroupOS.removeAllViews()
        for (chip in selectedChips) {
            chipGroupOS.addView(chip)
        }
    }
}