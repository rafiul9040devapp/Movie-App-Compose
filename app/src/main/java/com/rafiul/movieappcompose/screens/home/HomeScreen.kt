package com.rafiul.movieappcompose.screens.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.rafiul.movieappcompose.models.Movie
import com.rafiul.movieappcompose.models.getMovies
import com.rafiul.movieappcompose.navigation.MovieScreens
import com.rafiul.movieappcompose.ui.theme.Cream
import com.rafiul.movieappcompose.ui.theme.Purple80
import com.rafiul.movieappcompose.widgets.MovieRow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {

    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
    Scaffold(
        topBar = {
           CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Movies",
                        textAlign = TextAlign.Justify,
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Light
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    scrolledContainerColor = Cream,
//                    navigationIconContentColor = topAppBarElementColor,
//                    titleContentColor = topAppBarElementColor,
//                    actionIconContentColor= topAppBarElementColor,
                ),
                scrollBehavior = scrollBehavior
            )
        },
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
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
    movieList: List<Movie> = getMovies()
) {
    LazyColumn {
        items(items = movieList) { movieName ->
            MovieRow(movie = movieName) { movieDetails ->
                Log.d("Movie Details", movieDetails)

                navController.navigate(route = MovieScreens.DetailsScreen.name + "/$movieDetails")
            }
        }
    }
}