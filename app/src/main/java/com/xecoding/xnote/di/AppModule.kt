package com.xecoding.xnote.di

import android.content.Context
import androidx.room.Room
import com.xecoding.xnote.data.datasource.NoteDao
import com.xecoding.xnote.data.datasource.XNoteDatabase
import com.xecoding.xnote.data.repository.NoteRepositoryImpl
import com.xecoding.xnote.domain.repository.NoteRepository
import com.xecoding.xnote.domain.use_case.GetNotesUseCase
import com.xecoding.xnote.domain.use_case.GetSingleNoteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun xNoteDatabaseProvider(@ApplicationContext context: Context): XNoteDatabase =
        Room.databaseBuilder(context, XNoteDatabase::class.java, "xnote_db").build()

    @Provides
    @Singleton
    fun noteDaoProvider(xNoteDatabase: XNoteDatabase): NoteDao = xNoteDatabase.noteDao()

    @Provides
    @Singleton
    fun noteRepositoryProvider(xNoteDatabase: XNoteDatabase): NoteRepository =
        NoteRepositoryImpl(xNoteDatabase)

    @Provides
    @Singleton
    fun provideGetNotesUseCases(xNoteRepository: NoteRepository): GetNotesUseCase {
        return GetNotesUseCase(xNoteRepository)
    }

    @Provides
    @Singleton
    fun provideGetSingleNoteUseCases(xNoteRepository: NoteRepository): GetSingleNoteUseCase {
        return GetSingleNoteUseCase(xNoteRepository)
    }

}