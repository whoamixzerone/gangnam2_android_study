package com.survivalcoding.gangnam2kiandroidstudy.domain.repository

interface ClipboardRepository {
    fun copyToClipboard(text: String, label: String = "link")
}
