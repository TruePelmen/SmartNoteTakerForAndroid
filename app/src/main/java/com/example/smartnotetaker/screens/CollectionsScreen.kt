package com.example.smartnotetaker.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.smartnotetaker.MainViewModel

@Composable
fun CollectionsScreen(
    viewModel: MainViewModel,
    navController: NavController,
    modifier: Modifier
) {
    val collections = viewModel.getCollections()
    Column(modifier = modifier.fillMaxSize()) {
        Row{
            Text(
                text = "Collections",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(20.dp))

        }

        Spacer(modifier = Modifier.height(16.dp))
        if (collections.isEmpty()) {
            Text(text = "No collections yet.", style = MaterialTheme.typography.bodyMedium)
        } else {
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(collections) { collection ->
                    CollectionItem(collection, onCollectionClicked = {
                        navController.navigate("Notes/${collection.id}")
                    }, onEditClicked = {
                        navController.navigate("EditCollection/${collection.id}")
                    },
                        onDeleteClicked = {
                            navController.navigate("DeleteCollectionDialog/${collection.id}")
                        },
                        onItemClicked = {
                            navController.navigate("Notes/${collection.id}")
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        FloatingActionButton(
            onClick = { navController.navigate("AddCollection") },
            modifier = Modifier.padding(20.dp).align(Alignment.End)) {
            Icon(Icons.Filled.AddCircle, contentDescription = "Add new collection")
        }
    }
}
