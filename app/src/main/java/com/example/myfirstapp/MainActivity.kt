package com.example.myfirstapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myfirstapp.components.SliderAdvancedExample
import com.example.myfirstapp.components.SwitchWithCustomColors

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            /*
            var selected by remember { mutableStateOf(false) }

            Box (
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.LightGray),
                contentAlignment = Alignment.Center
            ){
                Text(text = "Types of Chips",modifier = Modifier
                        .padding(20.dp)
                        .align(Alignment.TopCenter),
                        fontSize = 40.sp,
                        fontWeight = FontWeight.ExtraBold,
                        fontFamily = FontFamily.Serif,
                        color = Color.Black )
                Column(modifier = Modifier.align(Alignment.Center)) {
                    SuggestionChip(
                        onClick = { Log.d("Suggestion chip", "hello world") },
                        label = { Text("Suggestion chip") }
                    )
                    FilterChip(
                        onClick = { selected = !selected },
                        label = {
                            Text("Filter chip")
                        },
                        selected = selected,
                        leadingIcon = if (selected) {
                            {
                                Icon(
                                    imageVector = Icons.Filled.Done,
                                    contentDescription = "Done icon",
                                    modifier = Modifier.size(FilterChipDefaults.IconSize)
                                )
                            }
                        } else {
                            null
                        },
                    )
                    AssistChip(
                        onClick = { Log.d("Assist chip", "hello world") },
                        label = { Text("Assist chip") },
                        leadingIcon = {
                            Icon(
                                Icons.Filled.Settings,
                                contentDescription = "Localized description"
                            )
                        }
                    )
                }*/

            val context = LocalContext.current
            Text(
                text = "How Much Are You Excited !!!",
                modifier = Modifier
                    .padding(horizontal = 100.dp, vertical = 15.dp),
                fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily.Serif,
                color = Color.Black
            )
            SliderAdvancedExample()
            Card(
                modifier = Modifier
                    .padding(20.dp, top = 100.dp)
                    .width(350.dp)
                    .height(450.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Gray
                ),
                elevation = CardDefaults.cardElevation(20.dp)
            ) {
                Column(modifier = Modifier.fillMaxSize()) {
                    Image(
                        painter = painterResource(id = R.drawable.image), contentDescription = null,
                        modifier = Modifier.padding(8.dp)
                    )
                    Text(
                        text = "BirthdaY InvitatioN",
                        modifier = Modifier
                            .padding(8.dp)
                            .align(Alignment.CenterHorizontally),
                        fontWeight = FontWeight.ExtraBold,
                        fontFamily = FontFamily.Serif,
                        color = Color.Black
                    )
                    ExtendedFloatingActionButton(
                        onClick = {
                            Toast.makeText(context, "Button Clicked", Toast.LENGTH_SHORT).show()
                        }, containerColor = Color.LightGray,
                        icon = { Icon(Icons.Filled.Edit, "Extended floating action button.") },
                        text = {
                            Text(
                                text = "Register", fontFamily = FontFamily.Serif,
                                fontWeight = FontWeight.Bold, fontSize = 15.sp
                            )
                        }, modifier = Modifier
                            .size(width = 140.dp, height = 30.dp)
                            .align(Alignment.CenterHorizontally)

                    )

                        Row(
                            modifier = Modifier
                                .width(200.dp)
                                .height(40.dp)
                                .align(Alignment.CenterHorizontally),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ){
                            Text(
                                text = "Done",
                                color = Color.DarkGray,
                                modifier = Modifier.padding(10.dp),
                                fontWeight = FontWeight.ExtraBold,
                                fontFamily = FontFamily.Serif,
                                fontSize = 15.sp
                            )
                            VerticalDivider(color = MaterialTheme.colorScheme.secondary)
                            SwitchWithCustomColors()
                        }
                    }



            }

            /*
                        val context = LocalContext.current
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(color = Color.LightGray),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Types of Buttons",
                                fontFamily = FontFamily.Serif,
                                fontWeight = FontWeight.Bold,
                                color = Color.Blue,
                                fontSize = 30.sp,
                                modifier = Modifier.align(Alignment.TopCenter)
                            )
                            Column(modifier = Modifier.align(Alignment.Center)) {
                                ExtendedFloatingActionButton(onClick = {
                                    Toast.makeText(context, "Button Clicked", Toast.LENGTH_SHORT).show() },
                                    icon = { Icon(Icons.Filled.Edit,"Extended floating action button.") },
                                    text = { Text(text = "Extended FAB",fontFamily = FontFamily.Serif,
                                        fontWeight = FontWeight.Bold, fontSize = 20.sp) },modifier = Modifier
                                            .size(width = 250.dp , height = 50.dp))
                                ElevatedButton(onClick = {
                                    Toast.makeText(context, "Button Clicked", Toast.LENGTH_SHORT).show() },
                                    modifier = Modifier.size(width = 200.dp , height = 50.dp)) {
                                    Text(text = "ELEVATED", fontFamily = FontFamily.Serif,
                                        fontWeight = FontWeight.Bold, fontSize = 20.sp)
                                }

                                OutlinedButton(onClick = {
                                    Toast.makeText(context, "Button Clicked", Toast.LENGTH_SHORT).show() },
                                    modifier = Modifier.size(width = 200.dp , height = 50.dp)) {
                                    Text(text = "OUT LINED", fontFamily = FontFamily.Serif,
                                        fontWeight = FontWeight.Bold, fontSize = 20.sp)
                                }

                                FilledTonalButton(onClick = {
                                    Toast.makeText(context, "Button Clicked", Toast.LENGTH_SHORT).show()},
                                    modifier = Modifier.size(width = 200.dp , height = 50.dp)) {
                                    Text(text = "TONAL", fontFamily = FontFamily.Serif,
                                        fontWeight = FontWeight.Bold, fontSize = 20.sp)
                                }
                                Button(onClick = {
                                    Toast.makeText(context, "Button Clicked", Toast.LENGTH_SHORT).show()
                                }, modifier = Modifier.size(width = 200.dp , height = 50.dp)) {
                                    Text(text = "FILLED", fontFamily = FontFamily.Serif,
                                        fontWeight = FontWeight.Bold, fontSize = 20.sp)
                                }
                            }

                        }
                        }*/
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

