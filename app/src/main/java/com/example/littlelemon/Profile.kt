package com.example.littlelemon

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.ui.theme.DarkGray
import com.example.littlelemon.ui.theme.Peach

@Composable
fun Profile(navController: NavController) {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences(USER_PROFILE, Context.MODE_PRIVATE)

    val firstName = sharedPreferences.getString(FIRST_NAME, "No First Name") ?: "No First Name"
    val lastName = sharedPreferences.getString(LAST_NAME, "No Last Name") ?: "No Last Name"
    val email = sharedPreferences.getString(EMAIL, "No Email") ?: "No Email"

    Scaffold(
        topBar = {
            TopAppBar(
                onLogoClick = {
                    navController.navigate(Home.route)
                },
                onProfileClick = {
                    navController.navigate(Profile.route)
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = "Personal information",
                    color = DarkGray,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.Start)
                )

                Spacer(modifier = Modifier.weight(0.4f))

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    ProfileInfoField(label = "First name", value = firstName)
                    ProfileInfoField(label = "Last name", value = lastName)
                    ProfileInfoField(label = "Email", value = email)
                }

                Spacer(modifier = Modifier.weight(1f))

                Button(
                    onClick = {
                        navController.navigate(Onboarding.route) {
                            popUpTo(Profile.route) { inclusive = true }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFFD700),
                        contentColor = Color.Black
                    ),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .border(
                            width = 1.dp,
                            brush = SolidColor(Peach),
                            shape = RoundedCornerShape(8.dp)
                        )
                ) {
                    Text(text = "Log out", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}


@Composable
fun ProfileInfoField(
    label: String,
    value: String
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = label,
            color = DarkGray,
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier.padding(start = 4.dp, bottom = 4.dp)
        )

        OutlinedTextField(
            value = value,
            onValueChange = {},
            readOnly = true,
            singleLine = true,
            textStyle = TextStyle(
                color = DarkGray,
                fontSize = 16.sp
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    val navController = rememberNavController()
    Profile(navController = navController)
}