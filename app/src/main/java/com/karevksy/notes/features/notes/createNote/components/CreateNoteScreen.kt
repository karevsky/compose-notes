package com.karevksy.notes.features.notes.createNote.components

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddPhotoAlternate
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.karevksy.core.ui.components.DefaultButton
import com.karevksy.core.ui.components.DefaultLoader
import com.karevksy.core.ui.components.Toast
import com.karevksy.core.utils.observeEvent
import com.karevksy.notes.R
import com.karevksy.notes.features.notes.createNote.CreateNoteViewModel
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun CreateNoteScreen(
    viewModel: CreateNoteViewModel,
    navController: NavController
) {
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            viewModel.addImage(uri)
        }
    )

    viewModel.navigateTo.observeEvent { screen ->
        navController.navigate(screen.route)
    }

    viewModel.showToast.observeEvent { message ->
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    viewModel.openGallery.observeEvent {
        launcher.launch("image/*")
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(
                modifier = Modifier.padding(start = 20.dp, top = 10.dp),
                text = stringResource(R.string.create_note),
                fontFamily = FontFamily.Monospace,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )

            InputForm(
                modifier = Modifier.fillMaxWidth(),
                noteTitle = viewModel.noteTitle,
                onTitleChanged = { viewModel.onTitleChanged(it) },
                noteContent = viewModel.noteContent,
                onContentChanged = { viewModel.onContentChanged(it) }
            )

            ImagesRow(
                modifier = Modifier
                    .padding(
                        start = 20.dp,
                        top = 10.dp,
                        end = 20.dp
                    )
                    .wrapContentSize(),
                images = viewModel.images,
                onAddImageClick = { viewModel.onAddImageClick() }
            )

            Text(
                modifier = Modifier.padding(start = 20.dp, top = 5.dp),
                text = "${viewModel.imageCount} фото",
                fontSize = 10.sp,
                color = MaterialTheme.colors.secondaryVariant
            )
        }

        DefaultButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .align(Alignment.BottomCenter),
            onClick = { viewModel.onSaveButtonClick() },
            title = stringResource(R.string.save_note),
            enabled = viewModel.buttonVisible
        )
    }
}

@Composable
fun InputForm(
    modifier: Modifier = Modifier,
    noteTitle: String,
    onTitleChanged: (String) -> Unit,
    noteContent: String,
    onContentChanged: (String) -> Unit
) {
    OutlinedTextField(
        modifier = modifier.padding(start = 20.dp, end = 20.dp, top = 10.dp),
        value = noteTitle,
        onValueChange = { value -> onTitleChanged(value) },
        label = { Text(text = stringResource(R.string.note_title)) },
        singleLine = true
    )

    OutlinedTextField(
        modifier = modifier
            .padding(start = 20.dp, end = 20.dp, top = 20.dp)
            .height(300.dp),
        value = noteContent,
        onValueChange = { value -> onContentChanged(value) },
        label = { Text(text = stringResource(R.string.description)) },
        maxLines = 50
    )
}

@Composable
fun ImagesRow(
    modifier: Modifier = Modifier,
    images: MutableList<String>,
    onAddImageClick: () -> Unit
) {
    LazyRow(
        modifier = modifier,
        content = {
            item {
                IconButton(
                    modifier = Modifier
                        .size(50.dp)
                        .padding(2.dp),
                    onClick = { onAddImageClick() }
                ) {
                    Icon(
                        modifier = Modifier.size(40.dp),
                        imageVector = Icons.Default.AddPhotoAlternate,
                        contentDescription = stringResource(R.string.ic_add_image)
                    )
                }
            }
            images.forEach { imagePath ->
                item {
                    ImageItem(
                        modifier = Modifier
                            .padding(2.dp)
                            .size(50.dp),
                        onItemClick = { },
                        onImageLoadFailure = {
                            Toast(message = stringResource(R.string.load_image_error))
                        },
                        imagePath = imagePath
                    )
                }
            }
        }
    )
}

@Composable
private fun ImageItem(
    modifier: Modifier = Modifier,
    onItemClick: (String) -> Unit,
    onImageLoadFailure: @Composable () -> Unit,
    imagePath: String
) {
    GlideImage(
        modifier = modifier.clickable { onItemClick(imagePath) },
        imageModel = imagePath,
        loading = {
            Box(Modifier.fillMaxSize()) {
                DefaultLoader(
                    modifier = Modifier
                        .size(20.dp)
                        .align(Alignment.Center),
                )
            }
        },
        failure = {
            onImageLoadFailure()
        }
    )
}