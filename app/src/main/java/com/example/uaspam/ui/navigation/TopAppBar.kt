package com.example.uaspam.ui.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.uaspam.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CostumeTopAppBar(
    title: String,
    canNavigateBack: Boolean,
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    navigateUp: () -> Unit = {},
    onRefresh: () -> Unit = {},

    ) {
    CenterAlignedTopAppBar(
        title = { Text(title) },
        actions = {
            Icon(imageVector = Icons.Default.Refresh, contentDescription = "", modifier = Modifier.clickable {
                onRefresh()
            })
        },
        modifier = modifier,
        scrollBehavior = scrollBehavior, navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
                }
            }
        }
    )
}

@Composable
fun TopAppBarHome(){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 40.dp)

            .background(color = colorResource(id = R.color.teal_700), shape = RoundedCornerShape(bottomEnd = 60.dp)
            )
    )
    {
        Row (
            modifier = Modifier.fillMaxWidth().padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Menu",
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .size(30.dp),
                    tint = Color.White
                )
                Spacer(modifier = Modifier.padding(5.dp))
                Text(
                    text = "Selamat Datang",
                    modifier = Modifier.padding(end = 30.dp),
                    color = Color.White,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = "Toko Buku",
                    color = Color.White,
                    fontSize = 22.sp
                )
            }
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .background(color = Color.White, shape = RoundedCornerShape(40.dp)),
                contentAlignment = Alignment.Center){
                Image(
                    painter = painterResource(id = R.drawable.gambarbuku),
                    contentDescription = "",
                    Modifier.size(55.dp)
                )
            }
        }
    }
}

