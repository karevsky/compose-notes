package com.karevksy.notes.di

import android.app.Application
import androidx.room.Room
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.karevksy.domain.api.useCase.*
import com.karevksy.domain.data.NotesDatabase
import com.karevksy.domain.database.useCase.notes.*
import com.karevksy.domain.database.repository.NoteRepository
import com.karevksy.domain.database.repository.NoteRepositoryImpl
import com.karevksy.domain.database.repository.UserRepository
import com.karevksy.domain.database.repository.UserRepositoryImpl
import com.karevksy.domain.database.useCase.user.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object AppModule {

    @Provides
    @ViewModelScoped
    fun provideNoteDatabase(app: Application): NotesDatabase {
        return Room.databaseBuilder(
            app,
            NotesDatabase::class.java,
            NotesDatabase.DATABASE_NAME
        ).build()
    }

    //Notes UseCases
    @Provides
    @ViewModelScoped
    fun provideNoteRepository(db: NotesDatabase): NoteRepository = NoteRepositoryImpl(db.noteDao)

    @Provides
    @ViewModelScoped
    fun provideAddNoteUseCase(noteRepository: NoteRepository): AddNoteUseCase =
        AddNoteUseCaseImpl(noteRepository)

    @Provides
    @ViewModelScoped
    fun provideDeleteNoteUseCase(noteRepository: NoteRepository): DeleteNoteUseCase =
        DeleteNoteUseCaseImpl(noteRepository)

    @Provides
    @ViewModelScoped
    fun provideGetNoteByIdUseCase(noteRepository: NoteRepository): GetNoteByIdUseCase =
        GetNoteByIdUseCaseImpl(noteRepository)

    @Provides
    @ViewModelScoped
    fun provideGetNotesUseCase(noteRepository: NoteRepository): GetNotesUseCase =
        GetNotesUseCaseImpl(noteRepository)


    //User UseCases
    @Provides
    @ViewModelScoped
    fun provideUserRepository(db: NotesDatabase): UserRepository = UserRepositoryImpl(db.userDao)

    @Provides
    @ViewModelScoped
    fun provideAddUserUseCase(userRepository: UserRepository): AddUserUseCase =
        AddUserUseCaseImpl(userRepository)

    @Provides
    @ViewModelScoped
    fun provideDeleteUserUseCase(userRepository: UserRepository): DeleteUserUseCase =
        DeleteUserUseCaseImpl(userRepository)

    @Provides
    @ViewModelScoped
    fun provideGetUserUseCase(userRepository: UserRepository): GetUserUseCase =
        GetUserUseCaseImpl(userRepository)


    //FirebaseAuthUseCases
    @Provides
    @ViewModelScoped
    fun provideFirebaseAuth(): FirebaseAuth = Firebase.auth

    @Provides
    @ViewModelScoped
    fun provideAuthUserUseCase(
        firebaseAuth: FirebaseAuth,
        addUserUseCase: AddUserUseCase
    ): SignInUseCase = SignInUseCaseImpl(firebaseAuth, addUserUseCase)

    @Provides
    @ViewModelScoped
    fun provideSignUpUserUseCase(firebaseAuth: FirebaseAuth): RegisterUserUseCase =
        RegisterUserUseCaseImpl(firebaseAuth)

    @Provides
    @ViewModelScoped
    fun provideCheckCurrentUserUseCase(
        firebaseAuth: FirebaseAuth,
        deleteUserUseCase: DeleteUserUseCase
    ): CheckCurrentUserUseCase =
        CheckCurrentUserUseCaseImpl(firebaseAuth, deleteUserUseCase)
}