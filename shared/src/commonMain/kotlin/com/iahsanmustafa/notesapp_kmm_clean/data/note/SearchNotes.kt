package com.iahsanmustafa.notesapp_kmm_clean.data.note

import com.iahsanmustafa.notesapp_kmm_clean.domain.note.Note
import com.iahsanmustafa.notesapp_kmm_clean.domain.time.DateTimeUtil

class SearchNotes {
    fun execute(notes: List<Note>, query: String): List<Note>{
        if(query.isBlank())
            return notes
        return notes.filter {
            it.title.trim().lowercase().contains(query) || it.content.trim().lowercase().contains(query)
        }.sortedBy { DateTimeUtil.toEpochMillis(it.created) }
    }
}