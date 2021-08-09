package com.nemesis.notescompose.ui.navigation

import androidx.navigation.NavController

interface Destination {
    fun navigate(navHostController: NavController)
}

data class NoteListDestination(private val popBackStack: Boolean = true) : Destination {

    companion object {
        const val route: String = "noteList"
    }

    override fun navigate(navHostController: NavController) {
        if (popBackStack) {
            navHostController.popBackStack(route, false)
        } else {
            navHostController.navigate(route)
        }
    }

}

object CreateNoteDestination : Destination {
    const val route = "createNote"

    override fun navigate(navHostController: NavController) {
        navHostController.navigate(route) {
            popUpTo(NoteListDestination.route)
        }
    }

}

data class EditNoteDestination(private val noteId: Int) : Destination {
    private val route = "editNote/$noteId"

    companion object {
        const val noteIdArgumentName = "noteId"
        const val route = "editNote/{$noteIdArgumentName}"
    }

    override fun navigate(navHostController: NavController) {
        navHostController.navigate(route) {
            popUpTo(NoteListDestination.route)
        }
    }

}

