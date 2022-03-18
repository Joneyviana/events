package com.example.events.presentation.checkin

import android.app.AlertDialog
import android.content.Context
import com.example.events.R

class CheckInDialogs {

    fun showSuccessDialog(context: Context) {
        AlertDialog.Builder(context).apply {
            setTitle(R.string.success_dialog)
            setPositiveButton(R.string.ok_label) { _, _ -> }
        }.create().show()
    }

    fun showFailedDialog(context: Context) {
        AlertDialog.Builder(context).apply {
            setTitle(R.string.failed_dialog)
            setPositiveButton(R.string.ok_label) { _, _ -> }
        }.create().show()
    }
}