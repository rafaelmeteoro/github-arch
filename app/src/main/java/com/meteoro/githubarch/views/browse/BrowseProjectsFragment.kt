package com.meteoro.githubarch.views.browse

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
import com.meteoro.githubarch.presentation.view.browse.BrowseProjectsViewModel
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_browse_projects.*
import javax.inject.Inject

class BrowseProjectsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var browseAdapter: BrowseAdapter

    private lateinit var viewModel: BrowseProjectsViewModel

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_browse_projects, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(BrowseProjectsViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBrowseRecycler()
        swipe.setOnRefreshListener { viewModel.loadProjects() }
        viewModel.itemsResource.observe(viewLifecycleOwner, Observer { handleItemResource(it) })
    }

    private fun handleItemResource(itemRes: Resource<List<ProjectUI>>?) {
        if (itemRes?.state == null) {
            return
        }

        if (itemRes.state == ResourceState.LOADING && itemRes.data.isNullOrEmpty()) {
            progress.visibility = View.VISIBLE
            return
        } else if (itemRes.state == ResourceState.SUCCEEDED) {
            progress.visibility = View.GONE
            swipe.post { swipe.isRefreshing = false }

            browseAdapter.setItems(itemRes.data ?: emptyList())
            browseAdapter.notifyDataSetChanged()
        }
    }

    private fun setupBrowseRecycler() {
        projects_recycler_view.layoutManager = LinearLayoutManager(context)

        browseAdapter = BrowseAdapter().apply {
            setListener(object : BrowseAdapter.ItemClickListener {
                override fun onClick(item: ProjectUI, pos: Int) {
                    if (item.isBookmarked) {
                        viewModel.unbookmarkProject(item.id)
                    } else {
                        viewModel.bookmarkProject(item.id)
                    }
                }
            })
        }
        projects_recycler_view.adapter = browseAdapter
    }
}