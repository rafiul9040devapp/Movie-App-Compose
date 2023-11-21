package com.rafiul.movieappcompose.screens.home

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.rafiul.movieappcompose.MovieRow
import com.rafiul.movieappcompose.navigation.MovieScreens
import com.rafiul.movieappcompose.ui.theme.Purple80

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Movies",
                        textAlign = TextAlign.Justify,
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Purple80)
            )
        }
    ) {
        Column(
            modifier = Modifier.padding(it)
        ) {
            MainContent(navController = navController)
        }
    }
}

@Composable
fun MainContent(
    navController: NavController,
    movieList: List<String> = listOf(
        "Avatar",
        "Avatar",
        "Avatar",
        "Avatar",
        "Avatar",
        "Avatar",
        "Avatar",
        "Avatar",
        "Avatar",
        "Avatar",
        "Avatar"
    )
) {
    LazyColumn {
        items(items = movieList) { movieName ->
            MovieRow(movie = movieName) { movieDetails ->
                Log.d("Movie Details", movieDetails)

                navController.navigate(route = MovieScreens.DetailsScreen.name)
            }
        }
    }
}