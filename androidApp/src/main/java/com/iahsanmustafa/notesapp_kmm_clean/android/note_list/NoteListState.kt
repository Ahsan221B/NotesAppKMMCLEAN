package com.iahsanmustafa.notesapp_kmm_clean.android.note_list

import com.iahsanmustafa.notesapp_kmm_clean.domain.note.Note

data class NoteListState(
    val notes : List<Note> = emptyList(),
    val searchText: String = "",
     val isSearchActive: Boolean = false
)
