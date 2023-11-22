package com.rafiul.movieappcompose.screens.details

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.rafiul.movieappcompose.R
import com.rafiul.movieappcompose.models.Movie
import com.rafiul.movieappcompose.models.getMovies
import com.rafiul.movieappcompose.ui.theme.Cream
import com.rafiul.movieappcompose.ui.theme.Purple80
import com.rafiul.movieappcompose.widgets.ImagePreviewer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(navController: NavController, movieData: String?) {
    val movie: Movie? = getMovies().find { it.id == movieData }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = movie?.title ?: "",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(all = 8.dp)
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Purple80),
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Go Back",
                        modifier = Modifier
                            .padding(all = 8.dp)
                            .clickable {
                                navController.popBackStack()
                            },
                    )
                },
            )
        },
    ) {
        if (movie != null) {
            MovieDetailing(
                movie = movie, modifier = Modifier
                    .padding(it)
                    .background(color = Cream)
                    .verticalScroll(rememberScrollState(), true)
            )
        } else {
            Text(
                text = "Error",
                modifier = Modifier.padding(horizontal = 25.dp, vertical = 25.dp),
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.ExtraBold,
                color = Color.Red
            )
        }
    }
}

@Composable
fun MovieDetailing(movie: Movie, modifier: Modifier) {
    Column(
        modifier = modifier
    ) {

        for (imageUrl in movie.images) {
           ImagePreviewer(imageUrl = imageUrl)
        }

        Spacer(modifier = Modifier.height(25.dp))

        Text(
            text = movie.title,
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "Director: ${movie.director} ",
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Justify
        )
        Text(
            text = "Release: ${movie.year}",
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Justify
        )


        Text(
            text = "Genre: ${movie.genre}",
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Justify
        )

        Text(
            text = "Casting: ${movie.actors}",
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Justify
        )

        Text(
            text = "Rating: ${movie.rating}",
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Justify
        )

        Text(
            buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Color.Black,
                        fontSize = 13.sp,
                    )
                ) {
                    append("Plot: ")
                }

                withStyle(
                    style = SpanStyle(
                        color = Color.Black,
                        fontSize = 13.sp,
                    )
                ) {
                    append(movie.plot)
                }
            },
        )
    }
}