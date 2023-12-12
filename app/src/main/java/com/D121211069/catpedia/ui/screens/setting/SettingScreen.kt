package com.D121211069.catpedia.ui.screens.setting

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChatBubble
import androidx.compose.material.icons.filled.Headset
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.D121211069.catpedia.R
import com.D121211069.catpedia.ui.component.BottomBar
import com.D121211069.catpedia.ui.theme.poppinsFontFamily

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun SettingScreen(navController: NavHostController) {
    Scaffold(
        bottomBar = {
            BottomBar(navController = navController)
        },
    ) {
        Column(
            modifier = Modifier.padding(it)
        ) {
            Text(
                text = "Setting",
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier
                    .padding(top = 15.dp, bottom = 20.dp, start = 10.dp),
                fontSize = 28.sp,
                lineHeight = 28.sp,
            )
            OptionSection(Icons.Default.Language, "Language")
            OptionSection(Icons.Default.Notifications, "Notification")
            OptionSection(Icons.Default.ChatBubble, "Send Feedback")
            OptionSection(Icons.Default.Help, "Help")
        }
    }
}

@Composable
fun OptionSection(icon : ImageVector, iconText : String) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = iconText,
            tint = Color.Black,
            modifier = Modifier
                .padding(start = 10.dp)
                .size(35.dp)
        )
        Text(
            text = iconText,
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.Normal,
            color = Color.Black,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 15.dp),
            textAlign = TextAlign.Justify,
            fontSize = 15.sp,
            lineHeight = 20.sp,
        )

        Spacer(modifier = Modifier.weight(1f))

        Image(
            painter = painterResource(id = R.drawable.arrow_back),
            contentDescription = "arrow back",
            modifier = Modifier
                .size(30.dp)
                .padding(end = 15.dp)
                .rotate(180f),
        )
    }
}
