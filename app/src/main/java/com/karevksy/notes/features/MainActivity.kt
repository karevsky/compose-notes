package com.karevksy.notes.features

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.karevksy.core.ui.theme.NotesTheme
import com.karevksy.notes.features.notes.components.NotesScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotesTheme {
                Surface() {
                    NotesScreen()

                }
            }
        }
    }
}

@Preview
@Composable
fun Preview() {
    NotesScreen()
}