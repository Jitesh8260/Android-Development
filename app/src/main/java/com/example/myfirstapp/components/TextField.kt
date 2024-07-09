package com.example.myfirstapp.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@Composable
fun TextField(){
    var valueOfField by remember { mutableStateOf(TextFieldValue("")) }
    Box(contentAlignment = Alignment.Center,
        modifier = Modifier.height(80.dp).width(411.dp)) {
        TextField(value = valueOfField, onValueChange ={valueOfField=it}, 
            label = {Text(text = "Name")},
            placeholder = {Text(text = "Enter Your Name")},
            leadingIcon = { Icon(Icons.Default.AccountCircle, contentDescription ="" )},
            trailingIcon = { Icon(Icons.Default.Close, contentDescription ="" )},
            )
    

    }
}