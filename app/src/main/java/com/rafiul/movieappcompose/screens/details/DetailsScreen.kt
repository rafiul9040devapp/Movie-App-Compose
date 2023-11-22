package com.rafiul.movieappcompose.screens.details

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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


    //alternative approach
//    val movieList = getMovies().filter {
//        movie -> movie.id == movieData
//    }


    //it returns the actual value of the list
    val movie: Movie? = getMovies().find { movieFromList ->
        movieFromList.id == movieData
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = movie?.title ?: "",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Light,
                        modifier = Modifier.padding(all = 4.dp),
                        textAlign = TextAlign.Justify
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
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

//        for (imageUrl in movie.images) {
//            ImagePreviewer(imageUrl = imageUrl)
//        }

        ImagePreviewer(imageUrl = movie.images[0])

        Spacer(modifier = Modifier.height(25.dp))

        Text(
            text = movie.title,
            style = MaterialTheme.typography.headlineMedium,
            color = Color.Black,
            textAlign = TextAlign.Justify
        )

        Column(
            modifier = Modifier.padding(all = 10.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {

            Text(
                text = "Director: ${movie.director} ",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black,
                textAlign = TextAlign.Justify
            )
            Text(
                text = "Release: ${movie.year}",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black,
                textAlign = TextAlign.Justify
            )


            Text(
                text = "Genre: ${movie.genre}",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black,
                textAlign = TextAlign.Justify
            )

            Text(
                text = "Casting: ${movie.actors}",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black,
                textAlign = TextAlign.Justify
            )

            Text(
                text = "Rating: ${movie.rating}",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black,
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


        Spacer(modifier = Modifier.height(25.dp))

        Text(
            text = "Movie Images",
            style = MaterialTheme.typography.headlineMedium,
            color = Color.Black,
            textAlign = TextAlign.Justify
        )


        Spacer(modifier = Modifier.height(25.dp))
        HorizontalImagePreviewer(movie)

    }
}

@Composable
private fun HorizontalImagePreviewer(movie: Movie) {
    LazyRow {
        items(items = movie.images) { imageUrl ->
            Card(
                modifier = Modifier
                    .padding(all = 12.dp)
                    .size(size = 240.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),

                ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(imageUrl)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(R.drawable.placeholder),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                )
            }
        }
    }
}