package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.unmockkStatic
import io.mockk.verify
import org.junit.After
import org.junit.Before
import org.junit.Test

class ClipboardRepositoryImplTest {

    private lateinit var context: Context
    private lateinit var clipboardManager: ClipboardManager
    private lateinit var clipboardRepository: ClipboardRepositoryImpl

    @Before
    fun setUp() {
        context = mockk()
        clipboardManager = mockk(relaxed = true)
        clipboardRepository = ClipboardRepositoryImpl(context)

        every { context.getSystemService(Context.CLIPBOARD_SERVICE) } returns clipboardManager
        mockkStatic(ClipData::class)
    }

    @After
    fun tearDown() {
        unmockkStatic(ClipData::class)
    }

    @Test
    fun `copyToClipboard should call setPrimaryClip with correct data`() {
        // Given
        val text = "test text"
        val label = "test label"
        val clipData = mockk<ClipData>()

        every { ClipData.newPlainText(label, text) } returns clipData

        // When
        clipboardRepository.copyToClipboard(text, label)

        // Then
        verify { ClipData.newPlainText(label, text) }
        verify { clipboardManager.setPrimaryClip(clipData) }
    }

    @Test
    fun `copyToClipboard should handle empty strings`() {
        // Given
        val text = ""
        val label = ""
        val clipData = mockk<ClipData>()

        every { ClipData.newPlainText(label, text) } returns clipData

        // When
        clipboardRepository.copyToClipboard(text, label)

        // Then
        verify { ClipData.newPlainText(label, text) }
        verify { clipboardManager.setPrimaryClip(clipData) }
    }

    @Test
    fun `copyToClipboard should use default label when not provided`() {
        // Given
        val text = "test text"
        val defaultLabel = "link"
        val clipData = mockk<ClipData>()

        every { ClipData.newPlainText(defaultLabel, text) } returns clipData

        // When
        clipboardRepository.copyToClipboard(text)

        // Then
        verify { ClipData.newPlainText(defaultLabel, text) }
        verify { clipboardManager.setPrimaryClip(clipData) }
    }
}
