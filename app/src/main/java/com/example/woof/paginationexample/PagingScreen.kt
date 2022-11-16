package com.example.woof.paginationexample

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun PagingScreen(){
    val viewModel = viewModel<MainViewModel>()
    val state = viewModel.state
    LazyColumn(modifier = Modifier.fillMaxSize()){
        items(state.items.size){ index ->
            val item = state.items[index]
            if(index >= state.items.size - 1 && !state.isLoading){
                viewModel.loadNextItems()
            }
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)) {
                Text(text = item.title, fontSize = 20.sp, color = Color.Black)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = item.description)
            }
        }

        item{
            if(state.isLoading){
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp), horizontalArrangement = Arrangement.Center) {
                    CircularProgressIndicator(color = Color.Magenta)
                }
            }
        }
    }
}