package com.example.littlelemon.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.littlelemon.R

// Define font families
private val MarkaziText = FontFamily(
    Font(R.font.markazi_text_medium, FontWeight.Medium, FontStyle.Normal),
    Font(R.font.markazi_text_regular, FontWeight.Normal, FontStyle.Normal),
    Font(R.font.markazi_text_bold, FontWeight.Bold, FontStyle.Normal)
)

private val Karla = FontFamily(
    Font(R.font.karla_medium, FontWeight.Medium, FontStyle.Normal),
    Font(R.font.karla_regular, FontWeight.Normal, FontStyle.Normal),
    Font(R.font.karla_bold, FontWeight.Bold, FontStyle.Normal),
    Font(R.font.karla_extrabold, FontWeight.ExtraBold, FontStyle.Normal)
)

val AppTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = MarkaziText,
        fontSize = 64.sp,
        fontWeight = FontWeight.Medium
    ),
    displayMedium = TextStyle(
        fontFamily = MarkaziText,
        fontSize = 40.sp,
        fontWeight = FontWeight.Normal
    ),
    titleLarge = TextStyle(
        fontFamily = Karla,
        fontSize = 20.sp,
        fontWeight = FontWeight.ExtraBold
    ),
    titleMedium = TextStyle(
        fontFamily = Karla,
        fontSize = 16.sp,
        fontWeight = FontWeight.ExtraBold
    ),
    titleSmall = TextStyle(
        fontFamily = Karla,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold
    ),
    bodyLarge = TextStyle(
        fontFamily = Karla,
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 24.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = Karla,
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium
    )
)