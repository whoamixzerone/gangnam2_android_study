package com.survivalcoding.gangnam2kiandroidstudy

import android.app.ComponentCaller
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.survivalcoding.gangnam2kiandroidstudy.core.routing.NavigationRoot

class MainActivity : ComponentActivity() {
    private var deepLinkUri by mutableStateOf<String?>(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        deepLinkUri = intent.dataString

        setContent {
            NavigationRoot(deepLinkUri = deepLinkUri)
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        deepLinkUri = intent.dataString
    }
}
