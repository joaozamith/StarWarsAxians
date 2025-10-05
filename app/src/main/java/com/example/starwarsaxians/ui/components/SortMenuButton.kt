package com.example.starwarsaxians.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Sort
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun SortMenuButton(
    sortAscending: Boolean?,
    onSortSelected: (Boolean?) -> Unit,
    modifier: Modifier = Modifier
) {
    var showMenu by remember { mutableStateOf(false) }

    Box(modifier = modifier) {
        IconButton(onClick = { showMenu = true }) {
            Icon(
                imageVector = Icons.Default.Sort,
                contentDescription = "Sort",
                tint = MaterialTheme.colorScheme.primary
            )
        }

        DropdownMenu(
            expanded = showMenu,
            onDismissRequest = { showMenu = false }
        ) {
            DropdownMenuItem(
                text = {
                    Text(
                        "No Sorting",
                        color = if (sortAscending == null)
                            MaterialTheme.colorScheme.primary
                        else Color.White
                    )
                },
                onClick = {
                    onSortSelected(null)
                    showMenu = false
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "None",
                        tint = Color.Gray
                    )
                }
            )

            DropdownMenuItem(
                text = {
                    Text(
                        "Sort by Name (A → Z)",
                        color = if (sortAscending == true)
                            MaterialTheme.colorScheme.primary
                        else Color.White
                    )
                },
                onClick = {
                    onSortSelected(true)
                    showMenu = false
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowUpward,
                        contentDescription = "Ascending",
                        tint = Color.Gray
                    )
                }
            )

            DropdownMenuItem(
                text = {
                    Text(
                        "Sort by Name (Z → A)",
                        color = if (sortAscending == false)
                            MaterialTheme.colorScheme.primary
                        else Color.White
                    )
                },
                onClick = {
                    onSortSelected(false)
                    showMenu = false
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowDownward,
                        contentDescription = "Descending",
                        tint = Color.Gray
                    )
                }
            )
        }
    }
}