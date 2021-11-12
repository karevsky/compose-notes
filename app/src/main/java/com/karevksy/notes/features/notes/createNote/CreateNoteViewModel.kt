package com.karevksy.notes.features.notes.createNote

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.karevksy.core.base.BaseViewModel
import com.karevksy.core.model.dto.Note
import com.karevksy.core.utils.*
import com.karevksy.domain.database.useCase.notes.AddNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreateNoteViewModel @Inject constructor(
    private val addNoteUseCase: AddNoteUseCase
) : BaseViewModel() {

    var imageCount by mutableStateOf(0)
    var images by mutableStateOf(mutableListOf<String>())
    var noteContent by mutableStateOf(Constants.EMPTY_STRING)
    var noteTitle by mutableStateOf(Constants.EMPTY_STRING)
    var buttonVisible by mutableStateOf(false)
    var noteFixed by mutableStateOf(false)

    var openGallery = LiveEvent()
    var openFullImage = LiveDataEvent<String>()

    fun onTitleChanged(value: String) {
        noteTitle = value
        checkButton()
    }

    private fun checkButton() {
        buttonVisible = noteTitle.isNotEmpty()
    }

    fun onContentChanged(value: String) {
        noteContent = value
    }

    fun changeNoteFix(value: Boolean) {
        noteFixed = value
    }

    fun onAddImageClick() {
        openGallery()
    }

    fun onImageClick(imagePath: String) {
        openFullImage(imagePath)
    }

    fun addImage(uri: Uri) {
        images.add(uri.toString())

        //compose не рекомпозируется если я не сделаю так
        imageCount = images.size
    }

    fun onSaveButtonClick() {
        val note = Note(
            title = noteTitle,
            content = noteContent,
            isFixed = noteFixed,
            timestamp = System.currentTimeMillis(),
            images = images as ArrayList<String>
        )

        addNoteUseCase(note)
            .androidAsync()
            .subscribe {
                showToast.value = "Метка создана"
                navigateTo.value = Screens.NotesScreen
            }
            .addToDisposable(compositeDisposable)
    }
}