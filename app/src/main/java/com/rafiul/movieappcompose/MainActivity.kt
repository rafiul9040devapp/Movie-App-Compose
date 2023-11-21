package com.rafiul.movieappcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rafiul.movieappcompose.navigation.MovieNavigation
import com.rafiul.movieappcompose.ui.theme.MovieAppComposeTheme
import com.rafiul.movieappcompose.ui.theme.Purple40

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                MovieNavigation()
            }
        }
    }
}


@Composable
fun MyApp(content: @Composable () -> Unit) {

    MovieAppComposeTheme {
        content()
    }
}


@Composable
fun MovieRow(movie: String, onItemClick: (String) -> Unit = {}) {
    Card(
        modifier = Modifier
            .padding(all = 8.dp)
            .fillMaxWidth()
            .height(130.dp)
            .shadow(
                elevation = 5.dp,
                shape = RoundedCornerShape(corner = CornerSize(16.dp)),
                spotColor = Purple40
            )
            .clickable {
                onItemClick(movie)
            },
        colors = CardDefaults.cardColors(containerColor = Color.White, contentColor = Color.Black)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {

            Surface(
                modifier = Modifier
                    .padding(12.dp)
                    .size(100.dp)
                    .shadow(elevation = 5.dp, shape = RectangleShape, spotColor = Color.Gray)
            ) {
                Icon(imageVector = Icons.Default.AccountBox, contentDescription = "Movie Image")

            }

            Text(text = movie)
        }

    }

}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApp {
        MovieNavigation()
    }
}