package au.com.barakode.jot.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import au.com.barakode.jot.data.Note
import java.time.Duration
import java.time.Instant

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteCard(
    note: Note,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = note.content,
                style = MaterialTheme.typography.bodyLarge,
                maxLines = 5,
                overflow = TextOverflow.Ellipsis
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val timeUntilExpiry = Duration.between(Instant.now(), note.expiresAt)
                val hoursLeft = timeUntilExpiry.toHours()
                val minutesLeft = timeUntilExpiry.toMinutesPart()
                
                Text(
                    text = "Expires in: ${hoursLeft}h ${minutesLeft}m",
                    style = MaterialTheme.typography.bodySmall
                )
                
                IconButton(onClick = onDelete) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete note"
                    )
                }
            }
        }
    }
}