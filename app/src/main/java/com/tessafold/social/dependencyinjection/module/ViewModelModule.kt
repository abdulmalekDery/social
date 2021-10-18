package com.tessafold.social.dependencyinjection.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tessafold.social.base.ViewModelFactory
import com.tessafold.social.dependencyinjection.utils.ViewModelKey
import com.tessafold.social.features.postdetails.PostDetailsViewModel
import com.tessafold.social.features.posts.PostsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(PostsViewModel::class)
    fun bindPostsViewModel(viewModel: PostsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PostDetailsViewModel::class)
    fun bindPostDetailsViewModel(viewModel: PostDetailsViewModel): ViewModel


    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}