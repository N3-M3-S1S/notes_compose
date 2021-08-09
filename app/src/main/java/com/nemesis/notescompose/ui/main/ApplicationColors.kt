package com.nemesis.notescompose.ui.main

import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import com.nemesis.notescompose.R

@Composable
fun applicationColors() = darkColors(
    primary = colorResource(id = R.color.primary),
    secondary = colorResource(id = R.color.primary)
)