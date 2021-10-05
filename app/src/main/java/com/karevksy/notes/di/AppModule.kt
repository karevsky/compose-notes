package com.karevksy.notes.di

import android.app.Application
import androidx.room.Room
import com.karevksy.domain.data.NoteDatabase
import com.karevksy.domain.repository.NoteRepository
import com.karevksy.domain.repository.NoteRepositoryImpl
import com.karevksy.domain.useCase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object AppModule {

    @Provides
    @ViewModelScoped
    fun provideNoteDatabase(app: Application): NoteDatabase {
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @ViewModelScoped
    fun provideNoteRepository(db: NoteDatabase): NoteRepository = NoteRepositoryImpl(db.noteDao)

    @Provides
    @ViewModelScoped
    fun provideNoteUseCases(noteRepository: NoteRepository): NoteUseCases =
        NoteUseCases(
            addNoteUseCase = AddNoteUseCaseImpl(noteRepository),
            deleteNoteUseCase = DeleteNoteUseCaseImpl(noteRepository),
            getNoteByIdUseCase = GetNoteByIdUseCaseImpl(noteRepository),
            getNotesUseCase =  GetNotesUseCaseImpl(noteRepository)
        )
}