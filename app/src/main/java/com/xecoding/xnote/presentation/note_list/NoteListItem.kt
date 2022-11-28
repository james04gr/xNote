package com.xecoding.xnote.presentation.note_list

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.xecoding.xnote.domain.model.Note
import com.xecoding.xnote.domain.model.Note.Companion.toColor
import kotlin.random.Random

@Composable
fun NoteListItem(
    note: Note,
    onItemClicked: (Note) -> Unit,
    onDeleteClicked: (Note) -> Unit
) {
    val cornerRadius: Dp = 20.dp
    val clipCorner: Dp = 10.dp

    Box(
        modifier = Modifier
            .padding(4.dp)
            .clickable {
                onItemClicked(note)
            }
    ) {
        Canvas(modifier = Modifier.matchParentSize()) {
            val clipPath = Path().apply {
                lineTo(size.width - clipCorner.toPx(), 0f)
                lineTo(size.width, clipCorner.toPx())
                lineTo(size.width, size.height)
                lineTo(0f, size.height)
                lineTo(0f, clipCorner.toPx())
                lineTo(clipCorner.toPx(), 0f)
                close()
            }

            clipPath(clipPath) {
                drawRoundRect(
                    color = note.color.toColor(),
                    size = size,
                    cornerRadius = CornerRadius(cornerRadius.toPx())
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(end = 32.dp)
        ) {
            Text(
                text = note.title,
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.onSurface,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = note.description,
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.onSurface,
                maxLines = 10,
                overflow = TextOverflow.Ellipsis
            )
        }
        IconButton(
            modifier = Modifier.align(Alignment.BottomEnd),
            onClick = {
                onDeleteClicked(note)
            }
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete note"
            )
        }
    }
}