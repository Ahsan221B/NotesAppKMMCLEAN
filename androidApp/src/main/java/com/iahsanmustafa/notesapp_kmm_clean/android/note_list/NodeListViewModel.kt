package com.iahsanmustafa.notesapp_kmm_clean.android.note_list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iahsanmustafa.notesapp_kmm_clean.data.note.SearchNotes
import com.iahsanmustafa.notesapp_kmm_clean.domain.note.Note
import com.iahsanmustafa.notesapp_kmm_clean.domain.note.NoteDataSource
import com.iahsanmustafa.notesapp_kmm_clean.domain.time.DateTimeUtil
import com.iahsanmustafa.notesapp_kmm_clean.presentation.RedOrangeHex
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NodeListViewModel @Inject constructor(
    private val noteDataSource: NoteDataSource,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    private val searchNotes = SearchNotes()

    private val notes = savedStateHandle.getStateFlow("notes", emptyList<Note>())
    private val searchText = savedStateHandle.getStateFlow("searchText", "")
    private val isSearchActive = savedStateHandle.getStateFlow("isSearchActive", false)

    val state = combine(notes, searchText, isSearchActive) { notes, searchText, isSearchActive ->
        NoteListState(
            notes = searchNotes.execute(notes, searchText),
            searchText = searchText,
            isSearchActive = isSearchActive
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), NoteListState())

    // Adding Notes
    init{
        viewModelScope.launch {
            (1..10).forEach {
                noteDataSource.insertNote(
                    Note(
                        id = null,
                        title = "Note$it",
                        content = "Content$it",
                        colorHex = RedOrangeHex,
                        created = DateTimeUtil.now()
                    )
                )
            }
        }
    }

    fun loadNotes() {
        viewModelScope.launch {
            savedStateHandle["notes"] = noteDataSource.getAllNotes()
        }
    }

    fun onSearchTextChange(text: String){
        savedStateHandle["searchText"] = text
    }

    fun onToggleSearch(){
        savedStateHandle["isSearchActive"] = !isSearchActive.value
        if(!isSearchActive.value)
            savedStateHandle["searchText"] = ""
    }

    fun deleteNoteById(id: Long){
        viewModelScope.launch {
            noteDataSource.deleteNoteById(id)
            loadNotes()
        }
    }

}