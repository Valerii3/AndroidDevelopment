package com.example.basiclayouts

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.basiclayouts.ui.theme.BasicLayoutsTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BasicLayoutsTheme {
               AppPortrait()
            }
        }
    }
}


@Composable
fun SearchBar(modifier: Modifier = Modifier) {
    var text = remember { mutableStateOf("") }

    TextField(
        leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "") },
        value = text.value,
        onValueChange = { text.value = it},
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp),
        placeholder = { Text("Search")},
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            focusedContainerColor = MaterialTheme.colorScheme.surface
        )
    )
}

@Composable
fun AlignYourBody(modifier: Modifier = Modifier) {
    Column(verticalArrangement = Arrangement.Top) {


        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 16.dp)) {
            items(10) { index ->
                Element(R.drawable.passport_photo,"$index")
            }
        }
    }

}


@Composable
fun AppPortrait() {
    BasicLayoutsTheme  {
        Scaffold(
            bottomBar = { BottomNavigation() },
        ) { padding ->
            HomeScreen(Modifier.padding(padding))
        }
    }
}


@Composable
fun FavoriteCollectionsGrid(
    modifier: Modifier = Modifier
) {
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.height(168.dp)
    ) {
        items(10) { item ->
            FavoriteCollectionCard(drawable = R.drawable.passport_photo, text = "some text", Modifier.height(80.dp))
        }
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(modifier) {
        Spacer(Modifier.height(16.dp))
        SearchBar(Modifier.padding(horizontal = 16.dp))
        HomeSection(title = "Align Your Body") {
            AlignYourBody()
        }
        HomeSection(title = "Favourite collections") {
            FavoriteCollectionsGrid()
        }
        Spacer(Modifier.height(16.dp))
    }
}


@Composable
fun HomeSection(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(modifier.verticalScroll(rememberScrollState())
    ) {
        Text(
            text = title,
            modifier = modifier
                .padding(horizontal = 16.dp)
                .paddingFromBaseline(top = 40.dp, bottom = 16.dp),
            color = Color.Gray,
            style = MaterialTheme.typography.titleMedium
        )
        content()
    }
}

@Composable
fun Element(@DrawableRes drawable: Int,
            text: String,
            modifier: Modifier = Modifier) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(drawable),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(88.dp)
                .clip(CircleShape)
        )
        Text(text = text, modifier = Modifier.paddingFromBaseline(top = 24.dp, bottom = 8.dp),
            style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
fun FavoriteCollectionCard(
    @DrawableRes drawable: Int,
    text: String,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.width(255.dp)
        ) {
            Image(
                painter = painterResource(drawable),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(80.dp)
            )
            Text(
                text = text,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

@Composable
fun BottomNavigation(modifier: Modifier = Modifier) {
    var selectedItem = remember { mutableIntStateOf(0) }

    NavigationBar(containerColor = MaterialTheme.colorScheme.surfaceVariant) {
        NavigationBarItem(selected = selectedItem.value == 1, onClick = { selectedItem.value = 1 }, icon = { Icon(imageVector = Icons.Default.AccountCircle, contentDescription = null) }, label = { Text("Profile")})
        NavigationBarItem(selected = selectedItem.value == 2, onClick = { selectedItem.value = 2 }, icon = { Icon(imageVector = Icons.Default.Favorite, contentDescription = null) }, label = { Text("Home")})
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun FavouriteCollectionGridPreview() {
    BasicLayoutsTheme {
        FavoriteCollectionsGrid()
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 720)
@Composable
fun AppPortraitPreview() {
    BasicLayoutsTheme  {
        Scaffold(
            bottomBar = { BottomNavigation() }
        ) { padding ->
            HomeScreen(Modifier.padding(padding))
        }
    }
}



@Preview(showBackground = true, widthDp = 320, heightDp = 180)
@Composable
fun HomeScreenPreview() {
    BasicLayoutsTheme {
        HomeScreen()
    }
}


@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun HomeSectionPreview() {
    BasicLayoutsTheme {
        HomeSection("Align Your Body") {
            AlignYourBody()
        }
    }
}


@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun AlignYourBodyPreview() {
    BasicLayoutsTheme {
        AlignYourBody()
    }
}


@Preview
@Composable
fun BottomNavigationPreview() {
    BasicLayoutsTheme {
        BottomNavigation()
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun FavoriteCollectionCardPreview() {
    BasicLayoutsTheme {
        FavoriteCollectionCard(
            text = "some text",
            drawable = R.drawable.passport_photo,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun ElementPreview() {
    BasicLayoutsTheme {
        Element(R.drawable.passport_photo,"Inversion")
    }
}


@Preview(showBackground = true, widthDp = 320)
@Composable
fun SearchBarPreview() {
    BasicLayoutsTheme {
        SearchBar()
    }
}