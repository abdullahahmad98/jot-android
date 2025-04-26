package au.com.barakode.jot.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Note::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class JotDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao

    companion object {
        @Volatile
        private var INSTANCE: JotDatabase? = null

        fun getDatabase(context: Context): JotDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    JotDatabase::class.java,
                    "jot_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}