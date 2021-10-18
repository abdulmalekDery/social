package com.tessafold.social.dependencyinjection.module

import android.app.Application
import androidx.room.Room
import com.tessafold.social.BuildConfig
import com.tessafold.social.config.Urls
import com.tessafold.social.models.local.LocalRepository
import com.tessafold.social.models.local.TestRoomDatabase
import com.tessafold.social.models.local.dao.CommentDBDao
import com.tessafold.social.models.local.dao.PostDBDao
import com.tessafold.social.models.local.dao.UserDBDao
import com.tessafold.social.models.network.control.RemoteRepository
import com.tessafold.social.models.network.services.IApiService
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideCoroutineContext(): CoroutineContext {
        return Dispatchers.Main
    }

    @Singleton
    @Provides
    fun provideDatabase(app: Application) = Room.databaseBuilder(
        app,
        TestRoomDatabase::class.java,
        "test_database"
    ).build()

    @Singleton
    @Provides
    fun providePostDao(db: TestRoomDatabase) = db.postDBDao()

    @Singleton
    @Provides
    fun provideUserDao(db: TestRoomDatabase) = db.userDBDao()

    @Singleton
    @Provides
    fun provideCommentDao(db: TestRoomDatabase) = db.commentDBDao()

    @Provides
    @Singleton
    fun provideLocalRepository(
        postDBDao: PostDBDao,
        userDBDao: UserDBDao,
        commentDBDao: CommentDBDao
    ): LocalRepository {
        return LocalRepository(postDBDao,commentDBDao,userDBDao)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(logger: HttpLoggingInterceptor): OkHttpClient {
        val TIMEOUT_CONNECT = 60
        val TIMEOUT_READ = 60
        val TIMEOUT_WRITE = 60
        val okHttpBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
        okHttpBuilder.addInterceptor(logger)
        okHttpBuilder.connectTimeout(TIMEOUT_CONNECT.toLong(), TimeUnit.SECONDS)
        okHttpBuilder.readTimeout(TIMEOUT_READ.toLong(), TimeUnit.SECONDS)
        okHttpBuilder.writeTimeout(TIMEOUT_WRITE.toLong(), TimeUnit.SECONDS)
        return okHttpBuilder.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Urls.BASE_URL).client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            loggingInterceptor.apply {
                loggingInterceptor.level = HttpLoggingInterceptor.Level.HEADERS
            }.level = HttpLoggingInterceptor.Level.BODY
        }
        return loggingInterceptor
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): IApiService {
        return retrofit.create(IApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRemoteRepository(
        apiService: IApiService
    ): RemoteRepository {
        return RemoteRepository(apiService)
    }
}