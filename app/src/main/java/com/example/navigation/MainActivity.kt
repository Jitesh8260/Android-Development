package com.example.navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.navigation.components.ScreenA
import com.example.navigation.components.ScreenB

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "screen_A") {
                composable("screen_A",){
                    ScreenA(navController)
                }
                composable("screen_B"+"/{name}",){
                    val name =it.arguments?.getString("name")
                    ScreenB(name?:"No name")
                }

            }
            //ScreenA()
            //ScreenB()

        }
    }
}
