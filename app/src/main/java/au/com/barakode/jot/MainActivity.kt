package au.com.barakode.jot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import au.com.barakode.jot.ui.NoteScreen
import au.com.barakode.jot.ui.theme.JotTheme
import au.com.barakode.jot.viewmodel.NoteViewModel

class MainActivity : ComponentActivity() {
    private val viewModel: NoteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JotTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NoteScreen(viewModel)
                }
            }
        }
    }
}