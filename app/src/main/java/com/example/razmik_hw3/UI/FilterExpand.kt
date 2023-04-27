package com.example.razmik_hw3.UI

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.razmik_hw3.DataClasses.Filter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.razmik_hw3.R

@Composable
fun FilterExpand(
    filters: List<Filter>,
    onFilterSelected: (Filter) -> Unit
) {
    var isExpanded by remember {mutableStateOf(false)}

    IconButton(
        onClick = {isExpanded = true}){

        Icon(
            imageVector = Icons.Filled.List,
            contentDescription = "FilterLIst"
            )
    }

    DropdownMenu(

        modifier = Modifier.fillMaxWidth()
                            .background(Color.LightGray),
        expanded = isExpanded,
        onDismissRequest = { isExpanded = false }) {
        Divider(
            color = Color.Black,
            modifier = Modifier.fillMaxWidth()
        )
        filters.forEach {
                filter ->
            DropdownMenuItem(onClick =
            {
                isExpanded = false
                onFilterSelected(filter)

            }) {

                Text(
                    text = filter.category,
                    style = MaterialTheme.typography.button
                )
            }
        }

    }
}