package com.example.mvvm_coroutin_flow_hilt.repository

import android.util.Log
import com.example.mvvm_coroutin_flow_hilt.model.ResponseDocument
import com.google.android.gms.tasks.Task
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import javax.inject.Inject

class CommonDataBase @Inject constructor() {
    private var database: DatabaseReference = Firebase.database.reference

    fun insert(responseDocument: ResponseDocument): Task<Void> {
        val user = responseDocument
        return database.child("bookList").child(user.isbn).setValue(user)
    }

    fun select(): DatabaseReference {
        return database.child("bookList")
    }

    fun remove(responseDocument: ResponseDocument): Task<Void> {
        val user = responseDocument
        return database.child("bookList").child(user.isbn).removeValue()
    }
}