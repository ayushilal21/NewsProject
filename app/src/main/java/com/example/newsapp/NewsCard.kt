package com.example.newsapp

import android.content.Intent
import android.net.Uri
import android.widget.ImageView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.squareup.picasso.Picasso

@Composable
fun NewsCard(newsItem: NewsItem) {
    val context = LocalContext.current

    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp),
        shape = RoundedCornerShape(20.dp),
        backgroundColor = Color(0xFF80D6FF), // Light Blue background
        elevation = 4.dp) {
        Row(modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically) {
            val imageUrl = newsItem.urlToImage ?: "https://cdn.pixabay.com/photo/2017/06/10/07/22/news-2389226_1280.png"
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFE0FFAA)) // Light Yellow Circle
            ) {
                if (imageUrl != null) {
                    AndroidView(
                        factory = { context ->
                            ImageView(context).apply {
                                Picasso.get()
                                    .load(imageUrl)
                                    .resize(80, 80) // Resize to fit the Box
                                    .centerCrop()
                                    .into(this)
                            }
                        },
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)){
                Text(
                    text = newsItem.title,
                    style = MaterialTheme.typography.h6,
                    color = Color(0xFF1C2020), // Cyan color for the title
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = newsItem.description ?: "",
//                    text = newsItem.description ?: "No description available",
                    style = MaterialTheme.typography.body2,
                    color = Color(0xFF202627) // Light cyan for description
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row {
                    Button(
                        onClick = {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(newsItem.url))
                            context.startActivity(intent)
                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF9E47FF)), // Purple button
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .height(40.dp)
                    ) {
                        Text(text = "Know More", color = Color.White)
                    }

                    Spacer(modifier = Modifier.width(52.dp))

                    Button(
                        onClick = {
                            val shareIntent = Intent().apply {
                                action = Intent.ACTION_SEND
                                putExtra(Intent.EXTRA_TEXT, newsItem.url)
                                type = "text/plain"
                            }
                            context.startActivity(Intent.createChooser(shareIntent, "Share via"))
                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF9E47FF)), // Pink button
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier.height(40.dp)
                    ) {
                        Text(text = "Share", color = Color.White)
                    }
                }
            }
        }
    }
}
