package com.nemesis.notescompose.ui.main

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.nemesis.notescompose.ui.createnote.CreateNoteScreen
import com.nemesis.notescompose.ui.editnote.EditNoteScreen
import com.nemesis.notescompose.ui.navigation.*
import com.nemesis.notescompose.ui.notelist.NoteListScreen
import kotlinx.coroutines.flow.collect

@Composable
fun MainScreen(navigator: Navigator) {
    val navController = rememberNavController()
    MaterialTheme(colors = applicationColors()) {
        Navigation(navController = navController)
        ObserveNavigationEvent(navController = navController, navigator = navigator)
    }
}

@Composable
private fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = NoteListDestination.route) {
        composable(NoteListDestination.route) {
            NoteListScreen()
        }

        composable(CreateNoteDestination.route) {
            CreateNoteScreen()
        }

        composable(
            EditNoteDestination.route,
            arguments = listOf(navArgument(EditNoteDestination.noteIdArgumentName) {
                type = NavType.IntType
            })
        ) {
            val noteId = it.arguments!!.getInt(EditNoteDestination.noteIdArgumentName)
            EditNoteScreen(noteId)
        }

    }
}

@Composable
private fun ObserveNavigationEvent(navController: NavController, navigator: Navigator) {
    LaunchedEffect(navController, navigator) {
        navigator.navigationEvent.collect { destination: Destination ->
            destination.navigate(navController)
        }
    }
}