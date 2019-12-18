package com.example.loginapi.util

import android.content.Context
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast

/**
 * Fonctions d'extensions
 */

// Toast
fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

// Progressbar
fun ProgressBar.show() {
    visibility = View.VISIBLE
}
fun ProgressBar.hide() {
    visibility = View.GONE
}