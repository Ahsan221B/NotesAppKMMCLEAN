package com.iahsanmustafa.notesapp_kmm_clean.domain.note

import com.iahsanmustafa.notesapp_kmm_clean.presentation.*
import kotlinx.datetime.LocalDateTime

data class Note(
    val id: Long?,
    val title: String,
    val content: String,
    val colorHex: Long,
    val created: LocalDateTime
){
    companion object{
        private val colors = listOf(RedOrangeHex, RedPinkHex, LightGreenHex, BabyBlueHex, VioletHex)
        fun generateRandomColors() = colors.random()
    }
}
