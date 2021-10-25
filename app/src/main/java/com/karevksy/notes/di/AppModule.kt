package com.karevksy.notes.di

import android.app.Application
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.room.PrimaryKey
import androidx.room.Room
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.karevksy.api.GetErrorCodeMessage
import com.karevksy.api.GetErrorCodeMessageImpl
import com.karevksy.api.useCase.*
import com.karevksy.domain.data.NoteDatabase
import com.karevksy.domain.repository.NoteRepository
import com.karevksy.domain.repository.NoteRepositoryImpl
import com.karevksy.domain.useCase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Singleton

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
    fun provideFirebaseAuthUseCases(
        firebaseAuth: FirebaseAuth,
    ): FirebaseAuthUseCases =
        FirebaseAuthUseCases(
            authenticateUserUseCase = AuthenticateUserUseCaseImpl(firebaseAuth),
            registerUserUseCase = RegisterUserUseCaseImpl(firebaseAuth),
            checkCurrentUserUseCase = CheckCurrentUserUseCaseImpl(firebaseAuth)
        )
}