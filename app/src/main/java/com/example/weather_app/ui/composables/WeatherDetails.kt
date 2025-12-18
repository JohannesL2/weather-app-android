package com.example.weather_app.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.draw.shadow


@Composable
fun WeatherDetails(
    data: WeatherModel,
    modifier: Modifier = Modifier
    ) {
    val condition = data.weather.firstOrNull()?.main ?: "Unknown"
    val emoji = weatherEmoji(condition)

    Spacer(modifier = Modifier.height(20.dp))
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(16.dp),
                clip = false
            )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .clip(
                    RoundedCornerShape(
                        topStart = 16.dp,
                        topEnd = 16.dp,
                        bottomStart = 0.dp,
                        bottomEnd = 0.dp
                    )
                )
                .background(Color(0xFFF2F2F2))
                .padding(16.dp)
        ) {
            val locationText = "${data.name}, ${data.sys.country}"
            Text(locationText, fontSize = 24.sp)

            val conditionText = data.weather.firstOrNull()?.main ?: "Loading..."
            Text(conditionText, fontSize = 20.sp)

            val temperatureText = "${data.main.temp_c}°C"
            Text(temperatureText, fontSize = 64.sp)

            AsyncImage(
                model = data.weather.firstOrNull()?.icon?.let { "https://openweathermap.org/img/wn/$it@2x.png" }
                    ?: "",
                contentDescription = "Weather Icon",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(100.dp)
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .clip(
                    RoundedCornerShape(
                        topStart = 0.dp,
                        topEnd = 0.dp,
                        bottomStart = 16.dp,
                        bottomEnd = 16.dp
                    )
                )
                .background(Color(0xFFE6F4EA))
                .padding(16.dp)
        ) {
            Text(
                text = "$emoji $condition",
                fontSize = 22.sp
            )
        }
    }

    Spacer(modifier = Modifier.height(40.dp))

//Second column wrapper

Column(
modifier = Modifier
.fillMaxWidth()
.shadow(
elevation = 8.dp,
shape = RoundedCornerShape(16.dp),
clip = false
)
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(
                    topStart = 16.dp,
                    topEnd = 16.dp,
                    bottomStart = 0.dp,
                    bottomEnd = 0.dp
                )
            )
            .background(Color(0xFFF2F2F2))
            .padding(16.dp)
    ) {
        val feelslikeText = "Feels like: ${data.main.feels_like_c}°C"
        Text(feelslikeText, fontSize = 24.sp)

        val minmaxText = "Min: ${data.main.temp_min_c}°C / Max: ${data.main.temp_max_c}°C"
        Text(minmaxText, fontSize = 24.sp)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(
                    topStart = 0.dp,
                    topEnd = 0.dp,
                    bottomStart = 16.dp,
                    bottomEnd = 16.dp
                )
            )
            .background(Color(0xFFE6F4EA))
            .padding(16.dp)
    ) {
        Text(
            text = when {
                condition.contains("rain", true) -> "☔ Don't forget your umbrella!"
                condition.contains("sun", true) -> "😎 Time for sunglasses!"
                condition.contains("snow", true) -> "❄️ Stay warm!"
                else -> "🌡️ Enjoy your day!"
            },
            fontSize = 18.sp
        )
    }
}
}

fun weatherEmoji(condition: String): String = when {
    condition.contains("rain", true) -> "🌧️"
    condition.contains("cloud", true) -> "☁️"
    condition.contains("snow", true) -> "❄️"
    condition.contains("sun", true) -> "☀️"
    else -> "🌡️"
}