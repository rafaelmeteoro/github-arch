package com.meteoro.githubarch.injection.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.meteoro.githubarch.domain.model.Project
import com.meteoro.githubarch.injection.annotations.ViewModelKey
import com.meteoro.githubarch.injection.factory.ViewModelFactory
import com.meteoro.githubarch.presentation.mapper.ProjectUIMapper
import com.meteoro.githubarch.presentation.mapper.UIMapper
import com.meteoro.githubarch.presentation.model.ProjectUI
import com.meteoro.githubarch.presentation.view.bookmarked.BookmarkedProjectsViewModel
import com.meteoro.githubarch.presentation.view.browse.BrowseProjectsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class PresentationModule {

    // ViewModels
    @Binds
    @IntoMap
    @ViewModelKey(BrowseProjectsViewModel::class)
    abstract fun bindBrowseProjectsViewModel(viewModel: BrowseProjectsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BookmarkedProjectsViewModel::class)
    abstract fun bindBrowseBookmarkedProjectsViewModel(viewModel: BookmarkedProjectsViewModel): ViewModel

    // Factories
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    // Mappers
    @Binds
    abstract fun bindProjectUIMapper(mapper: ProjectUIMapper): UIMapper<ProjectUI, Project>
}