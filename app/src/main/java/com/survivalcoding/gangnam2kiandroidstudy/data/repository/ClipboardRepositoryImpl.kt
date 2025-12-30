package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.ClipboardRepository

class ClipboardRepositoryImpl(
    private val context: Context
) : ClipboardRepository {
    override fun copyToClipboard(text: String, label: String) {
        val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText(label, text)
        clipboardManager.setPrimaryClip(clip)
    }
}
