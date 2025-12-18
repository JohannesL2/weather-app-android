package com.example.weather_app.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import coil.compose.AsyncImage
import androidx.compose.ui.layout.ContentScale
import com.example.weather_app.data.model.WeatherModel
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.shape.RoundedCornerShape


@Composable
fun WeatherDetails(
    data: WeatherModel,
    modifier: Modifier = Modifier
    ) {
    val condition = data.weather.firstOrNull()?.main ?: "Unknown"
    val emoji = weatherEmoji(condition)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(
                topStart = 16.dp,
                topEnd = 16.dp,
                bottomStart = 0.dp,
                bottomEnd = 0.dp))
            .background(Color.LightGray)
            .padding(16.dp)
    ) {
        val locationText = "${data.name}, ${data.sys.country}"
        Text(locationText, fontSize = 24.sp)

        val conditionText = data.weather.firstOrNull()?.main ?: "Loading..."
        Text(conditionText, fontSize = 20.sp)

        val temperatureText = "${data.main.temp_c}°C"
        Text(temperatureText, fontSize = 64.sp)

        AsyncImage(
            model = data.weather.firstOrNull()?.icon?.let { "https://openweathermap.org/img/wn/$it@2x.png" } ?: "",
            contentDescription = "Weather Icon",
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(100.dp)
        )
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(
                topStart = 0.dp,
                topEnd = 0.dp,
                bottomStart = 16.dp,
                bottomEnd = 16.dp))
            .background(Color.Green)
            .padding(16.dp)
    ) {
        Text(
            text = "$emoji $condition",
            fontSize = 22.sp
        )
    }
}

fun weatherEmoji(condition: String): String = when {
    condition.contains("rain", true) -> "🌧️"
    condition.contains("cloud", true) -> "☁️"
    condition.contains("snow", true) -> "❄️"
    condition.contains("sun", true) -> "☀️"
    else -> "🌡️"
}