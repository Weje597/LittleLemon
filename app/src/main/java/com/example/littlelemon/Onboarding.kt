package com.example.littlelemon

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.littlelemon.ui.theme.DarkGray
import com.example.littlelemon.ui.theme.Green
import com.example.littlelemon.ui.theme.LightGray
import com.example.littlelemon.ui.theme.Peach
import com.example.littlelemon.ui.theme.Yellow

@Composable
fun Onboarding(navController: NavController) {

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences(USER_PROFILE, Context.MODE_PRIVATE)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 64.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = "App logo",
            modifier = Modifier
                .height(60.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Green)
                .padding(vertical = 32.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Let's get to know you",
                color = LightGray,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Column(modifier = Modifier.padding(horizontal = 24.dp)) {
            Text(
                text = "Personal information",
                color = DarkGray,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(32.dp))

            LoginInputField(
                label = "First Name",
                placeholder = "Enter your first name",
                value = firstName,
                onValueChange = { firstName = it }
            )

            Spacer(modifier = Modifier.height(16.dp))

            LoginInputField(
                label = "Last Name",
                placeholder = "Enter your last name",
                value = lastName,
                onValueChange = { lastName = it }
            )

            Spacer(modifier = Modifier.height(16.dp))

            LoginInputField(
                label = "Email",
                placeholder = "Enter your email",
                value = email,
                onValueChange = { email = it }
            )
            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    if (firstName.isEmpty() && lastName.isEmpty() && email.isEmpty()) {
                        Toast.makeText(
                            context,
                            "Registration unsuccessful. Please enter all data.",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        with(sharedPreferences.edit()) {
                            putString(FIRST_NAME, firstName)
                            putString(LAST_NAME, lastName)
                            putString(EMAIL, email)
                            apply()
                        }

                        Toast.makeText(context, "Registration successful!", Toast.LENGTH_SHORT)
                            .show()

                        navController.navigate(Home.route) {
                            popUpTo(Onboarding.route) { inclusive = true }
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 24.dp)
                    .border(
                        width = 1.dp,
                        brush = SolidColor(Peach),
                        shape = RoundedCornerShape(8.dp)
                    ),

                colors = ButtonDefaults.buttonColors(containerColor = Yellow),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = "Register",
                    color = DarkGray,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                        .padding(vertical = 12.dp)
                )
            }
        }
    }
}

@Composable
fun LoginInputField(
    label: String,
    placeholder: String,
    value: String,
    onValueChange: (String) -> Unit
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
            onValueChange = onValueChange,
            placeholder = {
                Text(
                    text = placeholder,
                    color = DarkGray.copy(alpha = 0.6f),
                    textAlign = TextAlign.Start
                )
            },
            singleLine = true,
            textStyle = TextStyle(
                color = DarkGray,
                textAlign = TextAlign.Start
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }
}


