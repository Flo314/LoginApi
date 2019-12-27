package com.example.loginapi.ui.home.comments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.example.loginapi.R
import com.example.loginapi.util.Coroutines
import com.example.loginapi.util.toast
import kotlinx.coroutines.CoroutineScope
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class CommentsFragment : Fragment(), KodeinAware {

    override val kodein by kodein()
    private val factory: CommentsViewModelFactory by instance()

    private lateinit var viewModel: CommentsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.comments_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, factory).get(CommentsViewModel::class.java)

        Coroutines.main {
            val comments = viewModel.comments.await()
            comments.observe(this, Observer {
                context?.toast(it.size.toString())
            })
        }


    }

}
