package com.example.artspace
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                ArtSpaceLayout("Android")
            }
        }
    }

    @Composable
    fun ArtSpaceLayout(name: String) {
        Scaffold { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(26.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                // Espacio reservado para la obra de arte.
                Image(
                    painter = painterResource(R.drawable.bust_of_paris_antonio_canova_1809),
                    contentDescription = "Busto de Paris",
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentScale = ContentScale.Crop
                )

                // Información provisional para ajustar el diseño.
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shape = MaterialTheme.shapes.medium,
                ) {
                    Column(
                        modifier = Modifier.padding(18.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            stringResource(R.string.art_title),
                            style = MaterialTheme.typography.headlineSmall.copy(
                                fontSize = 28.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )
                        )
                        Text(
                            text = stringResource(R.string.art_details),
                            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 25.sp),
                            textAlign = TextAlign.Center
                        )
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(
                        36.dp, Alignment.CenterHorizontally
                    )
                ) {
                    Button(onClick = {}, modifier = Modifier.width(150.dp),
                        contentPadding = PaddingValues(horizontal = 25.dp, vertical = 20.dp)) {
                        Text(text = stringResource(R.string.previous_button),
                            style = MaterialTheme.typography.labelLarge.copy(fontSize = 25.sp),
                            textAlign = TextAlign.Center)
                    }
                    Button(onClick = {}, modifier = Modifier.width(150.dp),
                        contentPadding = PaddingValues(horizontal = 25.dp, vertical = 20.dp)) {
                        Text(text = stringResource(R.string.next_button),
                            style = MaterialTheme.typography.labelLarge.copy(fontSize = 25.sp),
                            textAlign = TextAlign.Center)
                    }
                }
            }
        }
    }

        @Preview(showBackground = true)
        @Composable
        fun ArtSpaceLayoutPreview() {
            ArtSpaceTheme {
                ArtSpaceLayout("Android")
            }
        }
    }