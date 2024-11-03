package com.example.twoactivity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.twoactivity.ui.theme.TwoActivityTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TwoActivityTheme {
                var userName by remember { mutableStateOf("") }
                var greetingMessage by remember { mutableStateOf("Здравствуйте!") }
                val getResult = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.StartActivityForResult()
                ) { result ->
                    if (result.resultCode == Activity.RESULT_OK) {
                        val name = result.data?.getStringExtra("USER_NAME") ?: ""
                        userName = name
                        greetingMessage = "Здравствуйте, $userName!"
                    }
                }
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
                        Button(onClick = {
                            val intent = Intent(this@MainActivity, SecondActivity::class.java)
                            intent.putExtra("GREETING_MESSAGE", "Сообщение из MainActivity!")
                            getResult.launch(intent)
                        }) {
                            Text(text = "Открыть вторую Activity")
                        }
                    }
                }
            }
        }
    }
}
