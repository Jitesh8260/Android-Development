package com.example.myfirstapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.myfirstapp.ui.theme.MyFirstAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyFirstAppTheme {
                val lightBlue = Color(0xFFADD8E6)
                val purple = Color(0xFF800080)

                val gradientColors = listOf(Color.Cyan, lightBlue, purple, Color.Yellow)

                Column (
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Cyan),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    for(i in 1 ..100)
                        Text(text = "COLUMN MODE",
                            color = Color.Red,
                            fontSize = 25.sp,
                            fontFamily = FontFamily.Serif,
                            fontStyle = FontStyle.Italic,
                            textAlign = TextAlign.Center,
                        )

                }
                Row (
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Cyan)
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                ){
                    for(i in 1 ..5)
                        Text(text = "ROW MODE",
                            color = Color.Red,
                            fontSize = 25.sp,
                            fontFamily = FontFamily.Serif,
                            fontStyle = FontStyle.Italic,
                            textAlign = TextAlign.Center,
                        )
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        println("LifeCycle:- On Create Started")
    }

    override fun onResume() {
        super.onResume()
        println("LifeCycle:- On Resume Started")
    }

    override fun onPause() {
        super.onPause()
        println("LifeCycle:- On Pause called")
    }

    override fun onStop() {
        super.onStop()
        println("LifeCycle:- On Stop called")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("LifeCycle:- On Destroy called")
    }
}
