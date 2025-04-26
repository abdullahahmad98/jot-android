package au.com.barakode.jot.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import au.com.barakode.jot.data.Note
import au.com.barakode.jot.viewmodel.NoteViewModel
import java.time.Duration
import java.time.Instant

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(viewModel: NoteViewModel) {
    var showAddDialog by remember { mutableStateOf(false) }
    var noteContent by remember { mutableStateOf("") }
    
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { showAddDialog = true }) {
                Icon(Icons.Default.Add, contentDescription = "Add Note")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            val notes by viewModel.allNotes.collectAsState()
            
            if (notes.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No notes yet. Add one!",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(notes) { note ->
                        NoteCard(
                            note = note,
                            onDelete = { viewModel.deleteNote(note) }
                        )
                    }
                }
            }
        }
        
        if (showAddDialog) {
            AlertDialog(
                onDismissRequest = { showAddDialog = false },
                title = { Text("Add Note") },
                text = {
                    TextField(
                        value = noteContent,
                        onValueChange = { noteContent = it },
                        placeholder = { Text("Enter your note") },
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                confirmButton = {
                    Button(onClick = {
                        if (noteContent.isNotBlank()) {
                            viewModel.addNote(noteContent)
                            noteContent = ""
                            showAddDialog = false
                        }
                    }) {
                        Text("Add")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showAddDialog = false }) {
                        Text("Cancel")
                    }
                }
            )
        }
    }
}