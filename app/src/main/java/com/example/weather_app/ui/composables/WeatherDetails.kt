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
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
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
}