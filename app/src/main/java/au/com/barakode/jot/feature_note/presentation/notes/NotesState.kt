package au.com.barakode.jot.feature_note.presentation.notes

import au.com.barakode.jot.feature_note.domain.model.Note
import au.com.barakode.jot.feature_note.domain.util.NoteOrder
import au.com.barakode.jot.feature_note.domain.util.OrderType

data class NotesState(
    val notes: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)
