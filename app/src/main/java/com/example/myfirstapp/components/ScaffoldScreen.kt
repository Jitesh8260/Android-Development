package com.example.myfirstapp.components


import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldExample() {
    var presses by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = Color.Red,
                    titleContentColor = Color.White,
                ),
                title = {
                    Text(
                        "TOI : THE TIMES OF INDIA",
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.ExtraBold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 45.dp)
                    )
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = Color.Red,
                contentColor = Color.White,
            ) {
                Row(
                    modifier = Modifier.horizontalScroll(rememberScrollState())
                ) {
                    PartialBottomSheet1()
                    PartialBottomSheet2()
                    PartialBottomSheet3()
                    /*
                    FilledTonalButton(onClick = { /*TODO*/ }, modifier = Modifier.padding(5.dp)) {
                        Text(text = "NEET UG RESULTS")
                    }
                    FilledTonalButton(onClick = { /*TODO*/ }, modifier = Modifier.padding(5.dp)) {
                        Text(text = "ODISHA ELECTIONS")
                    }
                    FilledTonalButton(onClick = { /*TODO*/ }, modifier = Modifier.padding(5.dp)) {
                        Text(text = "CRICKET")
                    }
                */
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { presses++ }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                text =
                """
                   Hy  I'm creatively combining Scaffold and BottomSheet UI elements to create a unique and intuitive user interface. By integrating these two elements, I aim to provide a seamless and visually appealing experience for users.
                    
                   Click on below buttons on bottom bar to display BottomSheet
                   
                   
                   You have pressed the floating action button $presses times.
                """.trimIndent(),
                fontSize = 20.sp, fontFamily = FontFamily.Serif,
            )
            Text(
                text = "Click on below buttons on bottom bar to display BottomSheet\n",
                fontSize = 20.sp,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.ExtraBold
            )
            Text(
                text = "Log In For Better Feeds",
                color = Color.Red,
                fontSize = 20.sp,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.ExtraBold, modifier = Modifier.padding(horizontal = 85.dp)
            )
            TextField()
        }
    }
}
