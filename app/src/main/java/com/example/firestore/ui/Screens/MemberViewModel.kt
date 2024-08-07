package com.example.firestore.ui.Screens

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.firestore.model.Member
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObjects
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MemberViewModel : ViewModel() {

    private var _memberList = MutableStateFlow<List<Member>>(emptyList())
    var memberList = _memberList.asStateFlow()

    private val db = Firebase.firestore

    init {
        getMemberList()
    }

    private fun getMemberList() {

        db.collection("Members")
            .addSnapshotListener { value, error ->
                if (error != null) {
                    return@addSnapshotListener
                }

                if (value != null) {
                    _memberList.value = value.toObjects()
                }
            }
    }

    fun addMember() {
        val member = hashMapOf(
            "Designation" to "UI Designer",
            "Name" to "Jitesh"
        )
        db.collection("Members")
            .add(member)
            .addOnSuccessListener {
                Log.d("document", "CREATED")
            } }

    fun updateMember() {
        val member = hashMapOf(
            "Designation" to "UI Designer",
            "Name" to "Kuldeep"
        )
        db.collection("Members")
            .document("7GK85DDbJzYPVhDfA0Bq")
            .set(member)
            .addOnSuccessListener {
                Log.d("document", "UPDATED")
            }
    }


    fun deleteMember() {
        db.collection("Members")
            .document("7GK85DDbJzYPVhDfA0Bq")
            .delete()
            .addOnSuccessListener {
                Log.d("document", "DELETED")
            }
    }
    }
