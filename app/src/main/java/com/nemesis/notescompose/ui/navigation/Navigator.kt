package com.nemesis.notescompose.ui.navigation

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class Navigator @Inject constructor() {
    private val _navigationEvent = MutableSharedFlow<Destination>()

    val navigationEvent: Flow<Destination> = _navigationEvent

    @OptIn(DelicateCoroutinesApi::class)
    fun navigate(destination: Destination) {
        GlobalScope.launch {
            _navigationEvent.emit(destination)
        }
    }

}