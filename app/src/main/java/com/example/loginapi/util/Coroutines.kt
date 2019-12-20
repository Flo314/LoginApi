package com.example.loginapi.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// object = static en java access de partout et appel sur la class
object Coroutines {

    fun main(work: suspend (() -> Unit)) =
        // définir le thread qu'il devra utiliser (Main)
        CoroutineScope(Dispatchers.Main).launch {
            // launch renvoi un job
            work()
        }
}