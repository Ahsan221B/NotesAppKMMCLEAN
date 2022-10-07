package com.iahsanmustafa.notesapp_kmm_clean.android.note_detail

import androidx.compose.ui.graphics.Color

data class NoteDetailState(
    val noteTitle: String = "",
    val isNoteTitleHintVisible: Boolean = false,
    val noteContent: String = "",
    val isNoteContentHintVisible: Boolean = false,
    val noteColor: Long = 0xFFFFFF
)
