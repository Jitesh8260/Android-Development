package com.example.otpauth

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.otpauth.ui.theme.OTPAuthTheme
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import java.util.concurrent.TimeUnit

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OTPAuthTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    LoginDialog()
                }
            }
        }
    }
}

@Composable
fun LoginDialog() {
    val dialogState: MutableState<Boolean> = remember {
        mutableStateOf(true)
    }
    Dialog(
        onDismissRequest = { dialogState.value = false },
        content = {
            CompleteDialogContent()
        },
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false
        )
    )
}

val auth = FirebaseAuth.getInstance()
var storedVerificationId: String = ""

@Composable
fun CompleteDialogContent() {
    val context = LocalContext.current
    var phoneNumber by remember {
        mutableStateOf(TextFieldValue(""))
    }
    var otp by remember {
        mutableStateOf(TextFieldValue(""))
    }
    var isOtpVisible by remember { mutableStateOf(false) }
    var isSuccessDialogVisible by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .height(300.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = RoundedCornerShape(4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Login with phone number", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            TextField(
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White
                ),
                placeholder = { Text("Enter phone number") },
                value = phoneNumber,
                onValueChange = {
                    if (it.text.length <= 10) phoneNumber = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            if (isOtpVisible) {
                TextField(
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.White
                    ),
                    value = otp,
                    placeholder = { Text("Enter otp") },
                    onValueChange = { otp = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }

            if (!isOtpVisible) {
                Button(
                    onClick = {
                        onLoginClicked(context, phoneNumber.text) {
                            Log.d("phoneBook", "setting otp visible")
                            isOtpVisible = true
                        }
                    },
                    colors = ButtonDefaults.textButtonColors(
                        backgroundColor = MaterialTheme.colors.primary
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                ) {
                    Text(text = "Send otp", color = Color.White)
                }
            }

            if (isOtpVisible) {
                Button(
                    onClick = {
                        verifyPhoneNumberWithCode(context, storedVerificationId, otp.text) {
                            isSuccessDialogVisible = true
                        }
                    },
                    colors = ButtonDefaults.textButtonColors(
                        backgroundColor = MaterialTheme.colors.primary
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                ) {
                    Text(text = "Verify", color = Color.White)
                }
            }
        }
    }

    if (isSuccessDialogVisible) {
        Dialog(
            onDismissRequest = { isSuccessDialogVisible = false },
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true
            )
        ) {
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = Color.White,
                modifier = Modifier.padding(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "OTP Verified Successfully!", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = { isSuccessDialogVisible = false },
                        colors = ButtonDefaults.textButtonColors(
                            backgroundColor = MaterialTheme.colors.primary
                        )
                    ) {
                        Text(text = "OK", color = Color.White)
                    }
                }
            }
        }
    }
}

private fun verifyPhoneNumberWithCode(
    context: Context,
    verificationId: String,
    code: String,
    onSuccess: () -> Unit
) {
    val credential = PhoneAuthProvider.getCredential(verificationId, code)
    signInWithPhoneAuthCredential(context, credential, onSuccess)
}

private fun signInWithPhoneAuthCredential(
    context: Context,
    credential: PhoneAuthCredential,
    onSuccess: () -> Unit
) {
    context.getActivity()?.let {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(it) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = task.result?.user
                    Log.d("phoneBook", "logged in")
                    onSuccess()
                } else {
                    // Sign in failed, display a message and update the UI
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                        Log.d("phoneBook", "wrong otp")
                    }
                    // Update UI
                }
            }
    }
}

private fun onLoginClicked(context: Context, phoneNumber: String, onCodeSent: () -> Unit) {
    auth.setLanguageCode("en")
    val callback = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            Log.d("phoneBook", "verification completed")
            signInWithPhoneAuthCredential(context, credential, onCodeSent)
        }

        override fun onVerificationFailed(p0: FirebaseException) {
            Log.d("phoneBook", "verification failed" + p0)
        }

        override fun onCodeSent(verificationId: String, token: PhoneAuthProvider.ForceResendingToken) {
            Log.d("phoneBook", "code sent" + verificationId)
            storedVerificationId = verificationId
            onCodeSent()
        }
    }
    val options = context.getActivity()?.let {
        PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber("+91" + phoneNumber)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(it)
            .setCallbacks(callback)
            .build()
    }
    if (options != null) {
        Log.d("phoneBook", options.toString())
        PhoneAuthProvider.verifyPhoneNumber(options)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    OTPAuthTheme {
        CompleteDialogContent()
    }
}
