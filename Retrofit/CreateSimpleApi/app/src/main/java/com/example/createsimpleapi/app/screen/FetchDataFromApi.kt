package com.example.createsimpleapi.app.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

data class ApiModel(
    @SerializedName("body")
    val body: String? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("userId")
    val userId: Int? = null
)

interface ApiServices {
    @GET("posts")
    suspend fun getAllPosts(): List<ApiModel>

}


class ScreenViewModel : ViewModel() {
    var state by mutableStateOf(emptyList<ApiModel>())
    private var apiServices: ApiServices


    init {
        val retroFit: Retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .build()
        apiServices = retroFit.create(ApiServices::class.java)
        getData()

    }

    private fun getData() {
        viewModelScope.launch(Dispatchers.IO) {
            state = apiServices.getAllPosts()

        }
    }


}

@Composable
fun Screen() {
    val vm: ScreenViewModel = viewModel()

    LazyColumn {
        items(vm.state) { post ->
            PostScreen(post)
        }
    }
}

@Composable
fun PostScreen(post: ApiModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Card(modifier = Modifier.background(Color.Gray)) {
            Text(text = post.id.toString())
            Text(text = post.title.toString())
            Text(text = post.userId.toString())
            Text(text = post.body.toString())
        }
        Spacer(modifier = Modifier.size(10.dp))

    }
}