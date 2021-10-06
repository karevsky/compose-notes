package com.karevksy.notes.di

import android.app.Application
import androidx.room.PrimaryKey
import androidx.room.Room
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.karevksy.api.useCase.*
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

    @Provides
    @ViewModelScoped
    fun provideFirebaseAuth(): FirebaseAuth = Firebase.auth

    @Provides
    @ViewModelScoped
    fun provideFirebaseAuthUseCases(firebaseAuth: FirebaseAuth): FirebaseAuthUseCases =
        FirebaseAuthUseCases(
            authenticateUserUseCase = AuthenticateUserUseCaseImpl(firebaseAuth),
            registerUserUseCase = RegisterUserUseCaseImpl(firebaseAuth),
            checkCurrentUserUseCase = CheckCurrentUserUseCaseImpl(firebaseAuth)
        )
}