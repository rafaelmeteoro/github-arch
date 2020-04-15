package com.meteoro.githubarch.presentation.view.bookmarked

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.meteoro.githubarch.domain.model.Project
import com.meteoro.githubarch.domain.usecase.GetBookmarkedProjects
import com.meteoro.githubarch.presentation.extensions.mutableLivedataOf
import com.meteoro.githubarch.presentation.mapper.ProjectUIMapper
import com.meteoro.githubarch.presentation.model.ProjectUI
import com.meteoro.githubarch.presentation.resource.Resource
import com.meteoro.githubarch.presentation.resource.toFailed
import com.meteoro.githubarch.presentation.resource.toLoading
import com.meteoro.githubarch.presentation.resource.toSucceeded
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

class BookmarkedProjectsViewModel @Inject constructor(
    private val getBookmarkedProjects: GetBookmarkedProjects,
    private val projectUIMapper: ProjectUIMapper
) : ViewModel() {

    private val _itemsResource by lazy { mutableLivedataOf(Resource<List<ProjectUI>>()) }
    val itemsResources: LiveData<Resource<List<ProjectUI>>> by lazy { _itemsResource.also { loadBookmarkedProjects() } }

    override fun onCleared() {
        getBookmarkedProjects.dispose()
        super.onCleared()
    }

    fun loadBookmarkedProjects() {
        _itemsResource.toLoading()
        getBookmarkedProjects.execute(LoadProjectsObserver())
    }

    inner class LoadProjectsObserver : DisposableObserver<List<Project>>() {

        override fun onComplete() {}

        override fun onNext(it: List<Project>) {
            val listOfProjectUI = it.map(projectUIMapper::mapToView)
            _itemsResource.toSucceeded(listOfProjectUI)
        }

        override fun onError(e: Throwable) {
            _itemsResource.toFailed(e.localizedMessage)
        }
    }
}