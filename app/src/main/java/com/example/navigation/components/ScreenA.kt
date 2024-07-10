package com.example.navigation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun ScreenA(navController: NavController){
    var data by remember { mutableStateOf("") }
    Box (modifier = Modifier.fillMaxSize().background(Color.LightGray),
        contentAlignment = Alignment.Center
    ){
        Column (){
            Text(text = "This is Screen A", fontSize = 20.sp, fontFamily = FontFamily.Serif,
                modifier = Modifier.padding(horizontal = 70.dp))
            OutlinedTextField(value = data , onValueChange = {
                data = it
            }, label = { Text(text = "Enter Data to send to Screen B") })
            Button(
                modifier = Modifier.padding(horizontal = 80.dp, vertical = 5.dp),
                onClick = {navController.navigate("screen_B"+"/$data")},
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
            ) {
                Text(text = "Send to Screen B", textAlign = TextAlign.Center)
            }
        }

    }

}