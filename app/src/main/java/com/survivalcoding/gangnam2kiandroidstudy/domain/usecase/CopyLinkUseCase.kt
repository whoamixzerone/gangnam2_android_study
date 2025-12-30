package com.survivalcoding.gangnam2kiandroidstudy.domain.usecase

import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.ClipboardRepository

class CopyLinkUseCase(private val repository: ClipboardRepository) {
    operator fun invoke(link: String) {
        repository.copyToClipboard(link)
    }
}
