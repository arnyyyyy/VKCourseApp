package com.arno.vk_course_app

import android.content.Intent
import android.content.Intent.ACTION_DIAL
import android.content.Intent.ACTION_SEND
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arno.vk_course_app.ui.theme.VK_Course_AppTheme
import androidx.core.net.toUri

class MainActivity : ComponentActivity() {
        @OptIn(ExperimentalMaterial3Api::class)
        override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                enableEdgeToEdge()
                setContent {
                        var inputText by remember { mutableStateOf("") }
                        var inputPhone by remember { mutableStateOf("") }

                        VK_Course_AppTheme {
                                Scaffold(
                                        modifier = Modifier.fillMaxSize(),
                                        topBar = {
                                                TopAppBar(title = { Text("Main Activity") })
                                        }
                                ) { innerPadding ->
                                        Column(
                                                modifier = Modifier
                                                        .fillMaxSize()
                                                        .padding(innerPadding)
                                        ) {
                                                TextField(
                                                        value = inputText,
                                                        onValueChange = { inputText = it },
                                                        label = { Text("Введите текст") },
                                                        modifier = Modifier.padding(16.dp)
                                                )
                                                Button(
                                                        onClick = {
                                                                if (!isInputValid(inputText)) {
                                                                        Toast.makeText(
                                                                                this@MainActivity,
                                                                                "Пожалуйста, введите текст",
                                                                                Toast.LENGTH_SHORT
                                                                        ).show()
                                                                        return@Button
                                                                }
                                                                val intent = Intent(this@MainActivity, SecondActivity::class.java).apply {
                                                                        putExtra("text", inputText)
                                                                }
                                                                startActivity(intent)
                                                        },
                                                        modifier = Modifier.padding(16.dp)
                                                ) {
                                                        Text("Открыть вторую Activity")
                                                }
                                                Button(
                                                        onClick = {
                                                                if (!isInputValid(inputText)) {
                                                                        Toast.makeText(
                                                                                this@MainActivity,
                                                                                "Пожалуйста, введите текст",
                                                                                Toast.LENGTH_SHORT
                                                                        ).show()
                                                                        return@Button
                                                                }
                                                                val intent = Intent(ACTION_SEND).apply {
                                                                        type = "text/plain"
                                                                        putExtra(Intent.EXTRA_TEXT, inputText)
                                                                }
                                                                startActivity(intent)
                                                        },
                                                        modifier = Modifier.padding(16.dp)
                                                ) {
                                                        Text("Поделиться текстом")
                                                }

                                                Spacer(modifier = Modifier.height(20.dp))

                                                TextField(
                                                        value = inputPhone,
                                                        onValueChange = { inputPhone = it },
                                                        label = { Text("Введите номер телефона") },
                                                        modifier = Modifier.padding(16.dp)
                                                )
                                                Button(
                                                        onClick = {
                                                                if (!isValidPhoneNumber(inputPhone)) {
                                                                        Toast.makeText(
                                                                                this@MainActivity,
                                                                                "Некорректный номер телефона",
                                                                                Toast.LENGTH_SHORT
                                                                        ).show()
                                                                        return@Button
                                                                }
                                                                val implicitIntent = Intent(ACTION_DIAL).apply {
                                                                        data = "tel:$inputPhone".toUri()
                                                                }
                                                                if (implicitIntent.resolveActivity(this@MainActivity.packageManager) != null) {
                                                                        startActivity(implicitIntent)
                                                                } else {
                                                                        Toast.makeText(
                                                                                this@MainActivity,
                                                                                "Нет приложения для звонков",
                                                                                Toast.LENGTH_SHORT
                                                                        ).show()
                                                                        return@Button
                                                                }
                                                        },
                                                        modifier = Modifier.padding(16.dp)
                                                ) {
                                                        Text("Позвонить другу")
                                                }
                                        }
                                }
                        }
                }
        }

        private fun isValidPhoneNumber(phone: String): Boolean {
                val regex = Regex("^\\+?\\d{10,15}$")
                return regex.matches(phone)
        }

        private fun isInputValid(input: String): Boolean {
                return input.isNotBlank()
        }
}
