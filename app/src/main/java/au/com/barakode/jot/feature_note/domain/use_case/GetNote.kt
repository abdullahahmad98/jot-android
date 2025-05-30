package au.com.barakode.jot.feature_note.domain.use_case

import au.com.barakode.jot.feature_note.domain.model.Note
import au.com.barakode.jot.feature_note.domain.repository.NoteRepository

class GetNote(
    private val noteRepository: NoteRepository
) {

    suspend operator fun invoke(id: Int): Note? {
        return noteRepository.getNoteById(id)
    }
}