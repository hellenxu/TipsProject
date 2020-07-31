package six.ca.droiddailyproject.hilt

import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

/**
 * @author hellenxu
 * @date 2020-07-30
 * Copyright 2020 Six. All rights reserved.
 */
@Module
@InstallIn(ApplicationComponent::class)
object RepoModule {

    @Provides
    @Reusable
    fun provideUserRepo(): UserRepo{
        return UserRepo()
    }
}