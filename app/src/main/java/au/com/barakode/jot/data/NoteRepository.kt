package au.com.barakode.jot.data

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import java.time.Instant

class NoteRepository(private val noteDao: NoteDao) {
    private val coroutineScope = CoroutineScope(Dispatchers.Main + Job())
    
    val allNotes: Flow<List<Note>> = noteDao.getAllNotes()
    
    suspend fun insertNote(note: Note): Long {
        return noteDao.insertNote(note)
    }
    
    suspend fun updateNote(note: Note) {
        noteDao.updateNote(note)
    }
    
    suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(note)
    }
    
    private suspend fun deleteExpiredNotes() {
        val now = Instant.now()
        noteDao.deleteExpiredNotes(now)
    }
    
    fun startExpirationChecker() {
        coroutineScope.launch {
            while (isActive) {
                deleteExpiredNotes()
                delay(60000) // Check every minute
            }
        }
    }
}