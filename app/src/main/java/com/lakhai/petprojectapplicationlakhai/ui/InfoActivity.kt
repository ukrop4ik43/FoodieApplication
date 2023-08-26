package com.lakhai.petprojectapplicationlakhai.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lakhai.petprojectapplicationlakhai.R

class InfoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InfoForUser()
        }
    }
    @Preview
    @Composable
    fun InfoForUser() {
        val scrollState = rememberScrollState()
        Box(
            modifier = with (Modifier){

                fillMaxSize()
                    .paint(
                        // Replace with your image id
                        painterResource(id = R.mipmap.splash_back),
                        contentScale = ContentScale.FillBounds)

            })
        Column (
            Modifier.background(Color(215,215,215,240)).fillMaxSize().verticalScroll(scrollState,enabled = true), Arrangement.Center, Alignment.CenterHorizontally
        ){

            Text(
                "About app", style = TextStyle(
                    color = Color(39,45,47),
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center, fontFamily = FontFamily(Font(R.font.splash_font))
                ), modifier = Modifier.padding(16.dp)
            )
            Text(
                "Welcome to Foodie, your ultimate companion for organizing receipts based on the products you have. Our app is designed to simplify the process of tracking and managing receipts, making it easier than ever to find and organize receipts in a snap.\n" +
                        "\n" +
                        "With our app, all you need to do is write down ingredients that you have, and our smart algorithms will automatically find receipt for you. Using advanced product recognition and categorization, we'll help you create a comprehensive digital record of your food, allowing you to quickly locate receipts based on the specific items you're looking for." +
                        "\n"
                , style = TextStyle(
                    color = Color(39,45,47),
                    fontSize = 25.sp,
                    textAlign = TextAlign.Center, fontFamily = FontFamily(Font(R.font.splash_font))
                ), modifier = Modifier.padding(16.dp)
            )
            Text(
                "Good luck\n and Bon Appetit!", style = TextStyle(
                    color = Color(39,45,47),
                    fontSize = 35.sp,
                    textAlign = TextAlign.Center, fontFamily = FontFamily(Font(R.font.splash_font))
                ), modifier = Modifier.padding(16.dp)
            )

        }

    }
    override fun onBackPressed() {
        val myIntent = Intent(this@InfoActivity, MenuActivity::class.java)
        startActivity(myIntent)
        finish()
    }
}