package com.xecoding.xnote.domain.model

import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val title: String,
    val description: String,
    val color: Int,
    val type: Int,
    val phone: String,
    val date: String
) {
    companion object {
        val colorList = listOf(Color.Green, Color.LightGray, Color.Yellow, Color.Cyan, Color.Magenta)
        fun Int.toColor(): Color = colorList[this]
        fun Color.toInt(): Int = colorList.indexOf(this)
    }
}