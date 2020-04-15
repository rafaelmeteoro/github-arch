package com.meteoro.githubarch.presentation.view.browse

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.meteoro.githubarch.domain.model.Project
import com.meteoro.githubarch.domain.usecase.BookmarkProject
import com.meteoro.githubarch.domain.usecase.GetProjects
import com.meteoro.githubarch.domain.usecase.UnbookmarkProject
import com.meteoro.githubarch.presentation.extensions.mutableLivedataOf
import com.meteoro.githubarch.presentation.mapper.ProjectUIMapper
import com.meteoro.githubarch.presentation.model.ProjectUI
import com.meteoro.githubarch.presentation.resource.Resource
import com.meteoro.githubarch.presentation.resource.toFailed
import com.meteoro.githubarch.presentation.resource.toLoading
import com.meteoro.githubarch.presentation.resource.toSucceeded
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

class BrowseProjectsViewModel @Inject constructor(
    private val getProjects: GetProjects,
    private val bookmarkProjects: BookmarkProject,
    private val unbookmarkProject: UnbookmarkProject,
    private val projectUIMapper: ProjectUIMapper
) : ViewModel() {

    private val _itemsResource by lazy { mutableLivedataOf(Resource<List<ProjectUI>>()) }
    val itemsResource: LiveData<Resource<List<ProjectUI>>> by lazy { _itemsResource.also { loadProjects() } }

    override fun onCleared() {
        getProjects.dispose()
        super.onCleared()
    }

    fun loadProjects() {
        _itemsResource.toLoading()
        getProjects.dispose()
        getProjects.execute(LoadProjectsObserver())
    }

    fun bookmarkProject(projectId: String) {
        _itemsResource.toLoading()
        val params = BookmarkProject.Params.forProject(projectId)
        bookmarkProjects.execute(BookmarkProjectObserver(), params)
    }

    fun unbookmarkProject(projectId: String) {
        _itemsResource.toLoading()
        val param = UnbookmarkProject.Params.forProject(projectId)
        unbookmarkProject.execute(BookmarkProjectObserver(), param)
    }

    inner class LoadProjectsObserver : DisposableObserver<List<Project>>() {

        override fun onComplete() {}

        override fun onNext(it: List<Project>) {
            val listOfProjectUi = it.map(projectUIMapper::mapToView)
            _itemsResource.toSucceeded(listOfProjectUi)
        }

        override fun onError(e: Throwable) {
            _itemsResource.toFailed(e.localizedMessage)
        }
    }

    inner class BookmarkProjectObserver : DisposableCompletableObserver() {

        override fun onComplete() {
            _itemsResource.toSucceeded(_itemsResource.value?.data)
        }

        override fun onError(e: Throwable) {
            _itemsResource.toFailed(e.localizedMessage)
        }
    }
}