package com.arno.vk_course_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.arno.vk_course_app.navigation.AppNavHost
import com.arno.vk_course_app.ui.theme.VK_Course_AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                enableEdgeToEdge()
                setContent {
                        VK_Course_AppTheme {
                                AppNavHost(modifier = Modifier.fillMaxSize())
                        }
                }
        }
}
