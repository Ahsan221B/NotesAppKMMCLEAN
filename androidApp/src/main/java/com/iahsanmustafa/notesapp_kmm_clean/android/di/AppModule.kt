package com.iahsanmustafa.notesapp_kmm_clean.android.di

import android.app.Application
import com.iahsanmustafa.notesapp_kmm_clean.data.local.DatabaseDriverFactory
import com.iahsanmustafa.notesapp_kmm_clean.data.note.SqlDelightNoteDataSource
import com.iahsanmustafa.notesapp_kmm_clean.database.NoteDatabase
import com.iahsanmustafa.notesapp_kmm_clean.domain.note.NoteDataSource
import com.squareup.sqldelight.db.SqlDriver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSqlDriver(app: Application): SqlDriver{
        return DatabaseDriverFactory(app).createDriver()
    }

    @Provides
    @Singleton
    fun provideNoteDataSource(driver: SqlDriver): NoteDataSource{
        return SqlDelightNoteDataSource(NoteDatabase(driver))
    }

}