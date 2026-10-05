package com.example.artspace
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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

private data class Artwork(
    val imageResource: Int,
    val titleResource: Int,
    val detailsResource: Int,
)

class MainActivity : ComponentActivity() {
    // Cada obra agrupa los recursos que deben cambiar juntos al navegar.
    private val artworks = listOf(
        Artwork(
            R.drawable.bust_of_paris_antonio_canova_1809,
            R.string.art_title,
            R.string.art_details
        ),
        Artwork(
            R.drawable.the_bedroom_vincent_van_gogh_1889,
            R.string.art_title_bedroom,
            R.string.art_details_bedroom
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                ArtSpaceLayout("Android")
            }
        }
    }

    // Calcula el índice circular para avanzar o retroceder sin salir de la lista.
    private fun changeArtworkIndex(currentIndex: Int, offset: Int): Int =
        Math.floorMod(currentIndex + offset, artworks.size)

    @Composable
    fun ArtSpaceLayout(name: String) {
        // rememberSaveable mantiene la obra actual incluso tras recrear la pantalla.
        var currentArtworkIndex by rememberSaveable { mutableIntStateOf(0) }
        val currentArtwork = artworks[currentArtworkIndex]

        Scaffold { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                // 1. Mantiene el conjunto compacto y centrado verticalmente.
                verticalArrangement = Arrangement.spacedBy(26.dp, Alignment.CenterVertically)
            ) {
                // 2. Limita la altura del marco para que no empuje la información hacia abajo.
                BoxWithConstraints(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 320.dp),
                    contentAlignment = Alignment.Center
                ) {
                    val artworkPainter = painterResource(currentArtwork.imageResource)
                    val intrinsicSize = artworkPainter.intrinsicSize
                    val aspectRatio = if (
                        intrinsicSize.width > 0f && intrinsicSize.height > 0f
                    ) {
                        intrinsicSize.width / intrinsicSize.height
                    } else {
                        1f
                    }
                    val framePadding = 15.dp
                    val maxImageWidth = (maxWidth - framePadding * 2).coerceAtLeast(0.dp)
                    val maxImageHeight = (maxHeight - framePadding * 2).coerceAtLeast(0.dp)
                    val imageHeight = minOf(maxImageHeight, maxImageWidth / aspectRatio)
                    val imageWidth = imageHeight * aspectRatio

                    // 3. Calcula el tamaño manteniendo la proporción y dejando un margen equilibrado.
                    Box(
                        modifier = Modifier
                            .size(
                                width = imageWidth + framePadding * 2,
                                height = imageHeight + framePadding * 2
                            )
                            // 3. Dibuja el marco alrededor de la imagen y su margen.
                            .border(2.dp, MaterialTheme.colorScheme.surfaceVariant)
                            .padding(framePadding),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = artworkPainter,
                            contentDescription = stringResource(currentArtwork.titleResource),
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Fit
                        )
                    }
                }

                // Información provisional para ajustar el diseño.
                Surface(
                    // 1. Deja que el fondo se ajuste al texto, con un máximo para títulos largos.
                    modifier = Modifier.widthIn(max = 320.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shape = MaterialTheme.shapes.medium,
                ) {
                    Column(
                        modifier = Modifier.padding(18.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            stringResource(currentArtwork.titleResource),
                            style = MaterialTheme.typography.headlineSmall.copy(
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )
                        )
                        Text(
                            text = stringResource(currentArtwork.detailsResource),
                            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp),
                            textAlign = TextAlign.Center
                        )
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(
                        20.dp, Alignment.CenterHorizontally
                    )
                ) {
                    Button(
                        // El desplazamiento -1 selecciona la obra anterior.
                        onClick = {
                            currentArtworkIndex = changeArtworkIndex(currentArtworkIndex, -1)
                        },
                        modifier = Modifier.width(130.dp),
                        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 12.dp)) {
                        Text(text = stringResource(R.string.previous_button),
                            style = MaterialTheme.typography.labelLarge.copy(fontSize = 16.sp),
                            textAlign = TextAlign.Center)
                    }
                    Button(
                        // El desplazamiento +1 selecciona la obra siguiente.
                        onClick = {
                            currentArtworkIndex = changeArtworkIndex(currentArtworkIndex, 1)
                        },
                        modifier = Modifier.width(130.dp),
                        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 12.dp)) {
                        Text(text = stringResource(R.string.next_button),
                            style = MaterialTheme.typography.labelLarge.copy(fontSize = 16.sp),
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