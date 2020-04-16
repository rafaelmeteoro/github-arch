package com.meteoro.githubarch.views.bookmarked

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.meteoro.githubarch.R
import com.meteoro.githubarch.injection.factory.ViewModelFactory
import com.meteoro.githubarch.presentation.model.ProjectUI
import com.meteoro.githubarch.presentation.resource.Resource
import com.meteoro.githubarch.presentation.resource.ResourceState
import com.meteoro.githubarch.presentation.view.bookmarked.BookmarkedProjectsViewModel
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_bookmarked_projects.*
import javax.inject.Inject

class BookmarkedProjectsFragment : Fragment() {

    @Inject
    lateinit var adapter: BookmarkedProjectsAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: BookmarkedProjectsViewModel

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_bookmarked_projects, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupRecyclerView()
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(BookmarkedProjectsViewModel::class.java)
        viewModel.itemsResources.observe(viewLifecycleOwner, Observer { handleItemsRecource(it) })
    }

    private fun handleItemsRecource(itemsRes: Resource<List<ProjectUI>>?) {
        if (itemsRes == null) {
            return
        }

        progress.visibility = if (itemsRes.state == ResourceState.LOADING)
            View.VISIBLE else View.GONE

        adapter.projectsList = itemsRes.data ?: emptyList()
        adapter.notifyDataSetChanged()
    }

    private fun setupRecyclerView() {
        recycler_projects.layoutManager = LinearLayoutManager(context)
        recycler_projects.adapter = adapter
    }
}