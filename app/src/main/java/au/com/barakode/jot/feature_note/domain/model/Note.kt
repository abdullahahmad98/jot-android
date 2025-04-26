package au.com.barakode.jot.feature_note.domain.model

import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    val title: String,
    val content: String,
    val timestamp: Long,
    val color: Int,
    @PrimaryKey val id: Int? = null
) {
    companion object {
        val noteColors = listOf(
            Color(0xFFB00020),
            Color(0xFF3700B3),
            Color(0xFF03DAC5),
            Color(0xFFFFC107),
            Color(0xFF6200EE),
            Color(0xFF03DAC6)
        )
    }
}
