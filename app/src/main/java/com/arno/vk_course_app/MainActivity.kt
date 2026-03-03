package com.arno.vk_course_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.arno.vk_course_app.ui.theme.VK_Course_AppTheme

class MainActivity : ComponentActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                enableEdgeToEdge()
                setContent {
                        VK_Course_AppTheme {
                                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                                        Greeting(
                                                name = "Android",
                                                modifier = Modifier.padding(innerPadding)
                                        )
                                }
                        }
                }
        }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
        Text(
                text = "Hello $name!",
                modifier = modifier
        )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
        VK_Course_AppTheme {
                Greeting("Android")
        }
}