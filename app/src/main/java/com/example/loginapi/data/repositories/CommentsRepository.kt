package com.example.loginapi.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.loginapi.data.db.AppDatabase
import com.example.loginapi.data.db.entities.Comment
import com.example.loginapi.data.network.MyApi
import com.example.loginapi.data.network.responses.SafeApiRequest
import com.example.loginapi.util.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CommentsRepository(
    private val api: MyApi,
    private val db: AppDatabase
) : SafeApiRequest() {

    private val comments = MutableLiveData<List<Comment>>()

    init {
        // observer les comments
        // observeForever -> car on n'est pas à l'intérieur d'une classe activity ou fragment
        // on a pas à se soucier du changement du cycle de vie
        comments.observeForever {
            // chaque fois qu'il y aura des changements on poussera en db
            saveComments(it)
        }
    }

    // fournir au viewModel les datas de la db
    suspend fun getComments(): LiveData<List<Comment>> {
        // besoin d'un scope coroutines "withContext"
        return withContext(Dispatchers.IO) {
            fetchComments()
            // retourne tous les comments de la db
            db.getCommentDao().getComments()
        }
    }

    // récupérer les données de l'api appel dans un autre thread via coroutines "suspend"
    private suspend fun fetchComments() {
        // si on a une copie des données en db on veut les récupérer sur le serveur
        if (isFetchNeeded()) {
            val response = apiRequest { api.getComments() }
            // ajout des data de l'api dans la liste observable (MutableLiveData)
            comments.postValue(response.comments)
        }
    }

    private fun isFetchNeeded(): Boolean {
        return true
    }

    private fun saveComments(listComments: List<Comment>) {
        Coroutines.io {
            db.getCommentDao().saveAllComments(listComments)
        }
    }
}