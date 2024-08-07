package com.example.firestore.ui.Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun MemberScreen(viewModel: MemberViewModel = viewModel(), modifier: Modifier) {

    val members by viewModel.memberList.collectAsStateWithLifecycle()

    Scaffold { paddingValues ->
        Column {
            Text(
                text = " Team OrbitX ",
                fontSize = 60.sp,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.padding(vertical = 20.dp)
            )
            LazyColumn(
                contentPadding = PaddingValues(20.dp),
                modifier = Modifier.padding(paddingValues),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(members) { member ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = member.Designation,
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = member.Name,
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                item {
                    Row {
                        Button(onClick = { viewModel.addMember() }) {
                            Text(text = "Add Member")
                        }
                        Button(onClick = { viewModel.updateMember() } ,modifier=Modifier.padding(horizontal = 10.dp)) {
                            Text(text = "Update Member")
                        }

                    }
                    Button(onClick = { viewModel.deleteMember() } ) {
                        Text(text = "Delete Member")
                    }

                }
            }
        }
    }
}
