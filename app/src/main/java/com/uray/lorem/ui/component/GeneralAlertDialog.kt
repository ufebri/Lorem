package com.uray.lorem.ui.component

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import androidx.core.widget.doAfterTextChanged
import com.uray.lorem.R
import com.uray.lorem.databinding.AlertDialogTextfieldBinding

object GeneralAlertDialog {

    interface onClickButtonListener {
        fun onClick(isPass: Boolean? = true, value: String)
    }

    fun showSimpleAlertDialog(
        context: Context,
        title: String,
        onClickListener: onClickButtonListener
    ) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(title)
        builder.setCancelable(true)
        builder.setMessage(title)

        builder.setPositiveButton(context.getString(R.string.text_yes)) { _, _ ->
            onClickListener.onClick(
                true,
                ""
            )
        }

        builder.setNegativeButton(context.getString(R.string.text_no)) { dialogInterface, _ ->
            onClickListener.onClick(false, "")
            dialogInterface.cancel()
        }

        val dialog = builder.create()
        dialog.show()
    }

    fun showCustomAlertDialog(
        context: Context,
        title: String,
        yesButton: String,
        noButton: String,
        onClickListener: onClickButtonListener
    ) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(title)
        builder.setCancelable(true)

        builder.setPositiveButton(yesButton) { dialogInterface, _ ->
            dialogInterface.dismiss()
            onClickListener.onClick(true, "")
        }

        builder.setNegativeButton(noButton) { dialogInterface, _ ->
            onClickListener.onClick(false, "")
            dialogInterface.cancel()
        }

        val dialog = builder.create()
        dialog.show()
    }

    fun showInputDialog(
        context: Context,
        title: String,
        pwd: (String) -> Unit
    ) {
        val builder = AlertDialog.Builder(context)
        val dialog = builder.create()

        val binding = AlertDialogTextfieldBinding.inflate(LayoutInflater.from(context))
        builder.setView(binding.root)
        builder.setTitle(title)

        binding.apply {
            etPwd.doAfterTextChanged { btnDel.isEnabled = etPwd.text.toString().isNotEmpty() }

            btnDel.setOnClickListener { pwd(etPwd.text.toString()) }
        }

        builder.show()
    }
}
