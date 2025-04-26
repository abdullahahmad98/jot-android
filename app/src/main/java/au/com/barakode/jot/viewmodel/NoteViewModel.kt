package au.com.barakode.jot.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import au.com.barakode.jot.data.JotDatabase
import au.com.barakode.jot.data.Note
import au.com.barakode.jot.data.NoteRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.temporal.ChronoUnit

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: NoteRepository
    val allNotes: StateFlow<List<Note>>

    init {
        val noteDao = JotDatabase.getDatabase(application).noteDao()
        repository = NoteRepository(noteDao)
        allNotes = repository.allNotes.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
        repository.startExpirationChecker()
    }

    fun addNote(content: String, expirationHours: Long = 24) {
        viewModelScope.launch {
            val note = Note(
                content = content,
                expiresAt = Instant.now().plus(expirationHours, ChronoUnit.HOURS)
            )
            repository.insertNote(note)
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            repository.deleteNote(note)
        }
    }

    fun updateNote(note: Note) {
        viewModelScope.launch {
            repository.updateNote(note)
        }
    }
}