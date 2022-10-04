package com.iahsanmustafa.notesapp_kmm_clean

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform