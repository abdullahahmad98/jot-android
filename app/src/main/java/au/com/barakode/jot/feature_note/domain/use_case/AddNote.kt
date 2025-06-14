package au.com.barakode.jot.feature_note.domain.use_case

import au.com.barakode.jot.feature_note.domain.model.InvalidNoteException
import au.com.barakode.jot.feature_note.domain.model.Note
import au.com.barakode.jot.feature_note.domain.repository.NoteRepository
import kotlin.jvm.Throws

class AddNote(
    private val repository: NoteRepository
) {

    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note) {
        if( note.title.isBlank() ) {
            throw InvalidNoteException("The title of the note can't be empty.")
        }
        if (note.content.isBlank()) {
            throw InvalidNoteException("The content of the note can't be empty.")
        }
        repository.insertNote(note)
    }
}