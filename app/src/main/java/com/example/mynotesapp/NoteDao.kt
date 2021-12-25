package com.example.mynotesapp

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NoteDao {

    @Insert
    fun insert(note:Note)

    @Delete
    fun delete(note:Note)

    @Query("Select * from notes_table order by id ASC")
    fun getAllNotes(): List<Note>
}