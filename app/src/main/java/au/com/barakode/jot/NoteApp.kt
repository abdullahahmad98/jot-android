package au.com.barakode.jot

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NoteApp : Application() {

    override fun onCreate() {
        super.onCreate()
        // Initialize any necessary components here
    }
}