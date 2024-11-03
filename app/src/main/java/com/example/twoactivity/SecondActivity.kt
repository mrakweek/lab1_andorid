package com.example.twoactivity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.twoactivity.ui.theme.TwoActivityTheme

class SecondActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val greetingMessage = intent.getStringExtra("GREETING_MESSAGE") ?: "Здравствуйте!"

        setContent {
            TwoActivityTheme {
                var name by remember { mutableStateOf("") }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = greetingMessage, style = MaterialTheme.typography.headlineMedium)
                        Spacer(modifier = Modifier.height(20.dp))
                        OutlinedTextField(
                            value = name,
                            onValueChange = { name = it },
                            label = { Text("Введите имя") },
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        Button(onClick = {
                            val resultIntent = Intent()
                            resultIntent.putExtra("USER_NAME", name)
                            setResult(Activity.RESULT_OK, resultIntent)
                            finish()
                        }) {
                            Text(text = "Отправить и закрыть")
                        }
                    }
                }
            }
        }
    }
}
