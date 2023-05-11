package com.example.practicas03

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.widget.RadioButton
import android.widget.RadioGroup

class SingleOptionDialog(private val context: Context, listOption: List<String>) {
    private var selectedOption: String? = null
    private val radioButtonList = listOption
    private var optionListener: OptionListener? = null

    interface OptionListener {
        fun optionSelected(option: String?)
    }

    fun setOptionListener(listener: OptionListener) {
        optionListener = listener
    }

    fun show() {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Seleccionar opción")

        val radioGroup = RadioGroup(context)
        radioGroup.orientation = RadioGroup.VERTICAL

        for (text in radioButtonList) {
            val radioButton = RadioButton(context)
            radioButton.text = text
            radioGroup.addView(radioButton)
        }

        builder.setView(radioGroup)

        builder.setPositiveButton("Aceptar") { dialogInterface: DialogInterface, i: Int ->
            val selectedRadioButton =
                radioGroup.findViewById<RadioButton>(radioGroup.checkedRadioButtonId)
            selectedOption = selectedRadioButton?.text?.toString()
            optionListener?.optionSelected(selectedOption)
        }

        builder.setNegativeButton("Cancelar") { dialogInterface: DialogInterface, i: Int ->
            selectedOption = null
            optionListener?.optionSelected(selectedOption)
        }

        val alertDialog = builder.create()
        alertDialog.show()
    }
}