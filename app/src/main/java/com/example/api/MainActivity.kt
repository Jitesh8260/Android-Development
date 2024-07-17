package com.example.api

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AlignVerticalBottom
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Dehaze
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.imageLoader
import coil.request.ImageRequest
import coil.size.Size
import com.example.api.data.RepositoryIm
import com.example.api.data.model.Product
import com.example.api.presentation.ProductsViewModel
import kotlinx.coroutines.flow.collectLatest

class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<ProductsViewModel>(factoryProducer = {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return ProductsViewModel(RepositoryIm(RetrofitInstance.api))
                        as T
            }

        }
    })

    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        setContent {

            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                val productsList = viewModel.products.collectAsState().value
                val context = LocalContext.current
                LaunchedEffect(key1 = viewModel.showErrorToastChannel) {
                    viewModel.showErrorToastChannel.collectLatest { show ->
                        if (show) {
                            Toast.makeText(context, "Error", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
                if (productsList.isEmpty()) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                } else {
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(70.dp)
                                    .padding(vertical = 10.dp),
                                title = {
                                        Row {
                                            Icon(
                                                Icons.Default.ArrowBack,
                                                contentDescription = "Add"
                                            )
                                            Spacer(modifier = Modifier.width(100.dp))
                                            Text(
                                                "MEN'S CORNER",
                                                fontFamily = FontFamily.Monospace,
                                                fontSize = 20.sp,
                                                fontWeight = FontWeight.Bold
                                            )
                                            Spacer(modifier = Modifier.width(80.dp))
                                            Icon(Icons.Default.Search, contentDescription = "Add")
                                        }
                                }
                            )
                        },
                        bottomBar = {
                            BottomAppBar(
                                containerColor = Color(255, 255, 255)
                            ) {
                                Image(modifier = Modifier.fillMaxSize(), painter = painterResource(id = R.drawable.cart), contentDescription = "")
                            }
                        },
                        floatingActionButton = {
                            FloatingActionButton(onClick = { }) {
                                Icon(Icons.Default.Add, contentDescription = "Add")
                            }
                        }
                    ) { innerPadding ->
                        Column(
                            modifier = Modifier
                                .padding(innerPadding),
                            verticalArrangement = Arrangement.spacedBy(16.dp),
                        ) {
                            Row (modifier = Modifier.horizontalScroll(rememberScrollState())){
                                FilledTonalButton(
                                    onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(
                                        containerColor = Color.Black,
                                        contentColor = Color.White
                                    ),
                                    modifier = Modifier
                                        .padding(horizontal = 5.dp)
                                        .size(height = 30.dp, width = 100.dp)
                                ) {
                                    Text(
                                        text = "T-shirts",
                                        fontSize = 12.sp
                                    )
                                }
                                FilledTonalButton(
                                    onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(
                                        containerColor = Color.Black,
                                        contentColor = Color.White
                                    ), modifier = Modifier
                                        .padding(horizontal = 5.dp)
                                        .size(height = 30.dp, width = 100.dp)
                                ) {
                                    Text(text = "Shirts", fontSize = 12.sp)
                                }
                                FilledTonalButton(
                                    onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(
                                        containerColor = Color.Black,
                                        contentColor = Color.White
                                    ),
                                    modifier = Modifier
                                        .padding(horizontal = 5.dp)
                                        .size(height = 30.dp, width = 100.dp)
                                ) {
                                    Text(text = "Jeans", fontSize = 12.sp)
                                }
                                FilledTonalButton(
                                    onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(
                                        containerColor = Color.Black,
                                        contentColor = Color.White
                                    ),
                                    modifier = Modifier
                                        .padding(horizontal = 5.dp)
                                        .size(height = 30.dp, width = 100.dp)
                                ) {
                                    Text(text = "Jackets", fontSize = 12.sp)
                                }

                            }
                            Box(modifier = Modifier
                                .fillMaxWidth()
                                .height(20.dp)) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(horizontal = 15.dp)
                                        .background(Color(235, 235, 231))
                                ) {
                                    Icon(
                                        Icons.Default.ArrowDropDown,
                                        contentDescription = "Add"
                                    )
                                    Text(text = "Filters",
                                        color = Color.DarkGray, fontSize = 13.sp,
                                        modifier = Modifier.align(Alignment.CenterVertically))
                                    Spacer(modifier = Modifier.width(100.dp))
                                    Icon(Icons.Default.AlignVerticalBottom, contentDescription = "Add")
                                    Text(text = "Price: lowest to high",
                                        color = Color.DarkGray,fontSize = 13.sp,
                                        modifier = Modifier.align(Alignment.CenterVertically))
                                    Spacer(modifier = Modifier.width(80.dp))
                                    Icon(Icons.Default.Dehaze, contentDescription = "Add")
                                }

                            }

                            LazyVerticalGrid(
                                columns = GridCells.Fixed(2), // 2 columns
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(16.dp)
                            ) {
                                items(productsList.size) { index ->
                                    Prod(productsList[index])
                                    Spacer(modifier = Modifier.height(50.dp))
                                }
                            }


                        }
                    }

                }
            }

        }
    }
}


