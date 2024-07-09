package com.example.myfirstapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.myfirstapp.components.ScaffoldExample

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScaffoldExample()
            /*
            val openAlertDialog =remember { mutableStateOf(false) }

            AlertDialogExample(
                onDismissRequest={ openAlertDialog.value = false },
            onConfirmation={openAlertDialog.value = false
                println("Confirmation registered")},
            dialogTitle= "Flash SMS Message",
            dialogText="""DataUsage : 121.58MB DataBal:708.1MB
                |03August2023 Bal:Rs.0.00
                |65=4GB data,valid till existing pack
            """.trimMargin(),
            icon= Icons.Default.Email,
            )
            */
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

