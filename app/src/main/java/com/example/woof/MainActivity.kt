package com.example.woof

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.woof.data.Dog
import com.example.woof.data.dogs
import com.example.woof.ui.theme.WoofTheme
import com.example.woof.viewmodelexample.GameScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WoofTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    GameScreen()
                    WoofApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WoofApp() {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar (
                title = {
                    Text(
                        text = stringResource(R.string.app_name),
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                navigationIcon = {
                    Image(
                        modifier = Modifier
                            .size(64.dp)
                            .padding(8.dp),
                        painter = painterResource(R.drawable.ic_woof_logo),
                        contentDescription = null
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
            )
        },
        content = { innerPadding ->
            LazyColumn(modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(top = innerPadding.calculateTopPadding())) {
                items(dogs) {
                    DogItem(dog = it)
                }
            }
        }
    )
}

@Composable
fun DogItem(dog: Dog, modifier: Modifier = Modifier) {

    var expanded by remember { mutableStateOf(false) }

    Card(modifier = modifier.padding(8.dp), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface), elevation = CardDefaults.cardElevation(5.dp)) {
        Column( modifier = Modifier
            .animateContentSize(
                animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy, stiffness = Spring.StiffnessLow)
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                ) {
                DogIcon(dog.imageResourceId)
                DogInformation(dog.name, dog.age)
                Spacer(Modifier.weight(1f))
                DogItemButton(
                    expanded = expanded,
                    modifier = Modifier.align(Alignment.CenterVertically),
                    onClick = {
                        expanded = !expanded
                    }
                )
            }
            if(expanded){
                DogHobby(dogHobby = dog.hobbies)
            }

        }
        
    }

}

@Composable
fun DogIcon(@DrawableRes dogIcon: Int, modifier: Modifier = Modifier) {
    Image(
        modifier = modifier
            .size(64.dp)
            .padding(8.dp)
            .clip(RoundedCornerShape(50)),

        painter = painterResource(dogIcon),
        contentScale = ContentScale.Crop,
        contentDescription = null
    )
}


@Composable
fun DogInformation(@StringRes dogName: Int, dogAge: Int, modifier: Modifier = Modifier) {
    Column {
        Text(
            text = stringResource(dogName),
            style = MaterialTheme.typography.titleMedium,
            modifier = modifier.padding(top = 8.dp),
            color = MaterialTheme.colorScheme.onSurface
        )
        Text(
            text = stringResource(R.string.years_old, dogAge),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
private fun DogItemButton(
    expanded: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(onClick = onClick, modifier = modifier) {
        Icon(
            imageVector = if(expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
            tint = MaterialTheme.colorScheme.secondary,
            contentDescription = stringResource(R.string.expand_button_content_description)
        )
    }
}

@Composable
fun DogHobby(@StringRes dogHobby: Int, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(
            start = 16.dp,
            top = 8.dp,
            bottom = 16.dp,
            end = 16.dp
        )
    ) {
        Text(
            text = stringResource(R.string.about),
            style = MaterialTheme.typography.titleSmall,
        )
        Text(
            text = stringResource(dogHobby),
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

@Preview
@Composable
fun DarkThemePreview() {
    WoofTheme(darkTheme = true) {
        WoofApp()
    }
}
