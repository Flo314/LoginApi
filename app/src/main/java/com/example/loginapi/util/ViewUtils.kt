package com.example.loginapi.util

import android.content.Context
import android.widget.Toast

/**
 * Fonctions d'extensions
 */

// Toast
fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}