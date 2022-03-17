package com.example.events.presentation.checkin

import android.app.AlertDialog
import android.content.Context

class CheckInDialogs {

    fun showSuccessDialog(context: Context) {
        AlertDialog.Builder(context).apply {
            setTitle("Success!!")
            setPositiveButton("OK") { _, _ -> }
        }.create().show()
    }

    fun showFailedDialog(context: Context) {
        AlertDialog.Builder(context).apply {
            setTitle("Failed!!")
            setPositiveButton("OK") { _, _ -> }
        }.create().show()
    }
}