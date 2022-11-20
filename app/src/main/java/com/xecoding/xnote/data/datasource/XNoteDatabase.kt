package com.xecoding.xnote.data.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.xecoding.xnote.domain.model.Note

@Database(
    entities = [Note::class],
    version = 1,
    exportSchema = false
)
abstract class XNoteDatabase: RoomDatabase() {

    abstract fun noteDao(): NoteDao

}