package com.tessafold.social.dependencyinjection.module

import com.tessafold.social.features.postdetails.PostDetailsActivity
import com.tessafold.social.features.posts.PostsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class ActivityModuleBuilder {

    @ContributesAndroidInjector
    abstract fun contributePostsActivity(): PostsActivity

    @ContributesAndroidInjector
    abstract fun contributePostsDetailsActivity(): PostDetailsActivity

}