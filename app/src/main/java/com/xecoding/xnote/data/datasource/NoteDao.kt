package com.xecoding.xnote.data.datasource

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.xecoding.xnote.domain.model.Note
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(note: Note): Completable

    @Query("""Select * from note""")
    fun getNotes(): Flowable<List<Note>>

    @Query("""Select * from note where id = :noteId""")
    fun getNote(noteId: Long): Single<Note>

    @Delete
    fun delete(note: Note): Completable

    @Query("""Delete from note""")
    fun deleteNotes(): Completable

}