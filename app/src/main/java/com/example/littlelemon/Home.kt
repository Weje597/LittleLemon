package com.example.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.ui.theme.DarkGray
import com.example.littlelemon.ui.theme.Green
import com.example.littlelemon.ui.theme.LightGray
import com.example.littlelemon.ui.theme.Yellow
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.littlelemon.MenuItemRoom


@Composable
fun Home(navController: NavController, database: AppDatabase) {
    val databaseMenuItems by database.menuItemDao().getAll().observeAsState(emptyList())
    var orderMenuItems by remember { mutableStateOf(false) }
    var searchPhrase by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("") }

    var menuItems = databaseMenuItems
    if (orderMenuItems) {
        menuItems = menuItems.sortedBy { it.title }
    }
    menuItems = menuItems.filter {
        (searchPhrase.isEmpty() || it.title.contains(searchPhrase, ignoreCase = true)) &&
                (selectedCategory.isEmpty() || it.category.equals(
                    selectedCategory,
                    ignoreCase = true
                ))
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TopAppBar(
            onLogoClick = {
                navController.navigate(Home.route)
            },
            onProfileClick = {
                navController.navigate(Profile.route)
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        HeroSection(searchPhrase, onSearchPhraseChange = { searchPhrase = it })

        CategorySection(selectedCategory, onCategoryChange = { selectedCategory = it })

        MenuItemsList(items = menuItems)
    }
}

@Composable
fun HeroSection(
    searchPhrase: String,
    onSearchPhraseChange: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Green)
            .padding(16.dp)
    ) {
        ConstraintLayout {
            val (text, pict) = createRefs()

            Column(
                modifier = Modifier.constrainAs(text) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
            ) {
                Text(
                    text = "Little Lemon",
                    style = MaterialTheme.typography.displayLarge,
                    color = Yellow,
                )

                Text(
                    text = "Chicago",
                    style = MaterialTheme.typography.displayMedium,
                    color = LightGray,
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = stringResource(R.string.description),
                    style = MaterialTheme.typography.bodyLarge,
                    color = LightGray,
                )

                Spacer(modifier = Modifier.height(32.dp))

                SearchBar(
                    hint = "Enter search phrase",
                    searchPhrase = searchPhrase,
                    onSearchPhraseChange = onSearchPhraseChange
                )
            }

            Image(
                painter = painterResource(R.drawable.header_image),
                contentDescription = null,
                modifier = Modifier
                    .size(150.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .constrainAs(pict) {
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    },
                contentScale = ContentScale.Crop
            )

        }
    }
}

@Composable
fun SearchBar(
    hint: String = "",
    searchPhrase: String,
    onSearchPhraseChange: (String) -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(
                color = LightGray, shape = RoundedCornerShape(10.dp)
            )
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painter = painterResource(id = R.drawable.search),
            contentDescription = null,
            tint = DarkGray,
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        TextField(
            value = searchPhrase,
            onValueChange = onSearchPhraseChange,
            placeholder = {
                Text(
                    text = hint,
                    color = DarkGray,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 32.dp)
                )
            },
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
            textStyle = TextStyle(
                color = DarkGray,
                fontSize = 16.sp,
                textAlign = if (searchPhrase.isEmpty()) TextAlign.Center else TextAlign.Start
            ),
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                cursorColor = Color.Transparent,
                focusedTextColor = Color.Transparent,
                unfocusedTextColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )
    }
}

@Composable
fun CategorySection(
    selectedCategory: String,
    onCategoryChange: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
    ) {
        Text(
            text = "ORDER FOR DELIVERY!",
            style = MaterialTheme.typography.titleLarge,
            color = Color.Black,
        )
        val scrollState = rememberScrollState()
        val categories = listOf("Starters", "Mains", "Desserts", "Drinks")

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
                .horizontalScroll(scrollState)
        ) {
            categories.forEach { category ->
                Button(
                    onClick = { onCategoryChange(category.lowercase()) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (selectedCategory == category.lowercase()) Green else LightGray,
                        contentColor = if (selectedCategory == category.lowercase()) LightGray else Green
                    ),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .height(40.dp)
                        .padding(horizontal = 4.dp)
                ) {
                    Text(
                        text = category,
                        style = MaterialTheme.typography.titleSmall,
                        color = if (selectedCategory == category.lowercase()) LightGray else Green
                    )
                }
            }
        }
        Divider(color = Green, thickness = 1.dp, modifier = Modifier.padding(top = 16.dp))
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun MenuItemsList(items: List<MenuItemRoom>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .padding(top = 10.dp)
    ) {
        items(items) { menuItem ->
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight(),
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Text(
                            text = menuItem.title,
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )
                        Text(
                            text = menuItem.description,
                            style = MaterialTheme.typography.bodyLarge,
                            maxLines = 2,
                            lineHeight = 16.sp,
                            color = Green,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )
                        Text(
                            text = "$%.2f".format(menuItem.price),
                            style = MaterialTheme.typography.bodyLarge,
                            color = Green,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    GlideImage(
                        model = menuItem.image,
                        contentDescription = "Menu Item Image",
                        modifier = Modifier
                            .size(80.dp)
                            .clip(RoundedCornerShape(10.dp)),
                        contentScale = ContentScale.Fit
                    )
                }
            }
            Divider(
                color = Green,
                thickness = 1.dp,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
            )
        }
    }
}



