package com.example.mynotesapp

import androidx.room.*

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(note:Note)

    @Delete
    fun delete(note:Note)

    @Query("Select * from notes_table order by id ASC")
    fun getAllNotes(): List<Note>
}