package com.survivalcoding.gangnam2kiandroidstudy.domain.usecase

import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.ClipboardRepository
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class CopyLinkUseCaseTest {

    private lateinit var clipboardRepository: ClipboardRepository
    private lateinit var copyLinkUseCase: CopyLinkUseCase

    @Before
    fun setUp() {
        clipboardRepository = mockk(relaxed = true)
        copyLinkUseCase = CopyLinkUseCase(clipboardRepository)
    }

    @Test
    fun `invoke should call copyToClipboard on repository`() {
        // Given
        val link = "https://example.com"

        // When
        copyLinkUseCase(link)

        // Then
        verify { clipboardRepository.copyToClipboard(link) }
    }
}
