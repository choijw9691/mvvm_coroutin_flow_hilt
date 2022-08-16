package com.example.mvvm_coroutin_flow_hilt.ui.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm_coroutin_flow_hilt.model.ResponseDocument
import com.example.mvvm_coroutin_flow_hilt.repository.CommonDataBase
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommonViewModel @Inject constructor(private val repository: CommonDataBase) : ViewModel() {
    private var _toast = MutableSharedFlow<String>()
    val toast = _toast.asSharedFlow()

    private val favorites = mutableSetOf<ResponseDocument>()
    private val _favoritesFlow = MutableSharedFlow<List<ResponseDocument>>(replay = 0)
    val favoritesFlow = _favoritesFlow.asSharedFlow()


    fun insert(responseDocument: ResponseDocument) {
        repository.insert(responseDocument)
            .addOnSuccessListener {
                viewModelScope.launch {
                    _toast.emit("insert 성공")
                }
            }.addOnCanceledListener {
                viewModelScope.launch {
                    _toast.emit("insert 실패")
                }
            }
    }

    init {

        repository.select().addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                var responseDocument = snapshot.getValue(ResponseDocument::class.java)
                toggle(responseDocument!!)
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {}
            override fun onChildRemoved(snapshot: DataSnapshot) {}
            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}
            override fun onCancelled(error: DatabaseError) {}

        })
    }

    fun toggle(responseDocument: ResponseDocument) {
        if (favorites.contains(responseDocument)) {
            repository.remove(responseDocument!!).addOnCompleteListener {
                viewModelScope.launch {
                    _toast.emit("insert 성공")
                }
            }.addOnCanceledListener {
                viewModelScope.launch {
                    _toast.emit("insert 실패")
                }
            }
            favorites.remove(responseDocument)
        } else {
            favorites.add(responseDocument!!)
        }
        viewModelScope.launch {
            _favoritesFlow.emit(favorites.toList())
        }
    }
}