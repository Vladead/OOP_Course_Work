import androidx.compose.desktop.AppWindowAmbient
import androidx.compose.desktop.Window
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.imageFromResource
import androidx.compose.ui.unit.dp

@Composable
fun UIBookView(book: BookCopy?, onDismissRequest: () -> Unit, onAddTransaction: () -> Unit) {
    val isFirst = mutableStateOf(true)
    val isAdmin = true
    Window(onDismissRequest = onDismissRequest) {
        MaterialTheme(
            shapes = Shapes(RoundedCornerShape(0.dp), RoundedCornerShape(0.dp), RoundedCornerShape(0.dp)),
            colors = MaterialTheme.colors.copy(
                primary = Color(80, 50, 50),
                onPrimary = Color.Black,
                background = Color(80, 80, 80),
                surface = Color(80, 80, 80),
                isLight = false
            )
        ) {
            if (isFirst.value) {
                AppWindowAmbient.current?.setTitle("Скрижали - ${book?.name}")
                AppWindowAmbient.current?.setSize(600, 400)
                isFirst.value = false
            }
            Box(Modifier.background(Color(80, 80, 80)).fillMaxSize()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        imageFromResource("zone_archive.png"),
                        modifier = Modifier.border(2.dp, Color.DarkGray).padding(20.dp)
                    )
                    Column(
                        modifier = Modifier.fillMaxHeight().preferredSize(800.dp, 800.dp),
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Column(modifier = Modifier.padding(top = 10.dp)) {
                            Text("${book?.name}")
                            Text("${book?.authors}")
                        }
                        Text("${book?.description}")
                        if (isAdmin)
                            Button(modifier = Modifier.padding(bottom = 10.dp), onClick = onAddTransaction) {
                                Text("Дополнить легенду")
                            }
                    }
                }
            }
        }
    }
}