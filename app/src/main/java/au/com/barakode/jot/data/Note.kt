package au.com.barakode.jot.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val content: String,
    val createdAt: Instant = Instant.now(),
    val expiresAt: Instant,
    val title: String = ""
)