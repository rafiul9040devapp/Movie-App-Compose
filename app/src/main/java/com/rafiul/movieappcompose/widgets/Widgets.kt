package com.rafiul.movieappcompose.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.text.style.TextMotion
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.rafiul.movieappcompose.R
import com.rafiul.movieappcompose.models.Movie
import com.rafiul.movieappcompose.models.getMovies
import com.rafiul.movieappcompose.ui.theme.Cream
import com.rafiul.movieappcompose.ui.theme.Purple40


@Preview
@Composable
fun MovieRow(movie: Movie = getMovies()[0], onItemClick: (String) -> Unit = {}) {


    val expanded = remember {
        mutableStateOf(false)
    }

    Card(
        modifier = Modifier
            .padding(all = 8.dp)
            .fillMaxWidth()
            .shadow(
                elevation = 5.dp,
                shape = RoundedCornerShape(corner = CornerSize(16.dp)),
                spotColor = Purple40
            )
            .clickable {
                onItemClick(movie.id)
            },
        colors = CardDefaults.cardColors(containerColor = Cream, contentColor = Color.Black)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {

            Surface(
                modifier = Modifier
                    .padding(12.dp)
                    .size(100.dp)
                    .shadow(elevation = 15.dp, shape = CircleShape, spotColor = Color.Black)
            ) {

                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(movie.images[0])
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(R.drawable.placeholder),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.clip(CircleShape)
                )


            }

            Column(
                modifier = Modifier
                    .padding(all = 10.dp)
                    .fillMaxWidth(1f)
            ) {
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.headlineSmall,
                    textAlign = TextAlign.Justify
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

                AnimatedVisibility(visible = expanded.value) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(1f)
                    ) {

                        Text(
                            buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        color = Color.Black,
                                        fontSize = 13.sp,
                                        fontStyle = null
                                    )
                                ) {
                                    append("Plot: ")
                                }

                                withStyle(
                                    style = ParagraphStyle()
                                ) {
                                    append(movie.plot)
                                }
                            },
                            modifier = Modifier.padding(all = 6.dp)
                        )

                        Divider(modifier = Modifier
                            .padding(all = 3.dp)
                            .height(2.dp))


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
                    }
                }

                Icon(
                    imageVector = if (expanded.value) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = "Down Arrow",
                    modifier = Modifier
                        .size(25.dp)
                        .clickable {
                            expanded.value = !expanded.value
                        }
                )
            }


        }

    }

}



@Composable
fun ImagePreviewer(imageUrl:String){
    Surface(
        modifier = Modifier
            .padding(all = 8.dp)
            .shadow(elevation = 15.dp, shape = RectangleShape, spotColor = Color.Black)
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


