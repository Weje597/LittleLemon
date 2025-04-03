package com.example.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout


@Composable
fun TopAppBar(onLogoClick:() -> Unit, onProfileClick:() -> Unit) {
    ConstraintLayout(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .windowInsetsPadding(WindowInsets.statusBars)
    ) {
        val (logo, profileIcon) = createRefs()

        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = "App logo",
            modifier = Modifier
                .height(60.dp)
                .clip(RoundedCornerShape(8.dp))
                .clickable { onLogoClick() }
                .constrainAs(logo) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                },
            contentScale = ContentScale.Crop
        )

        Image(
            painter = painterResource(R.drawable.profile),
            contentDescription = "Profile",
            modifier = Modifier
                .height(50.dp)
                .clip(RoundedCornerShape(8.dp))
                .clickable { onProfileClick() }
                .constrainAs(profileIcon) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(logo.end)
                },
            contentScale = ContentScale.Fit
        )
    }
}