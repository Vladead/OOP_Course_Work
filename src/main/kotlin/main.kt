import androidx.compose.desktop.AppWindowAmbient
import androidx.compose.desktop.Window
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.unit.dp

@Suppress("EXPERIMENTAL_API_USAGE")
fun main() = Window(title = "Well, course work") {
    MaterialTheme(
        shapes = Shapes(RoundedCornerShape(0.dp), RoundedCornerShape(0.dp), RoundedCornerShape(0.dp)),
        colors = MaterialTheme.colors.copy(
            primary = Color(80, 50, 50),
            onPrimary = Color.Black,
            background = Color(80, 80, 80),
            surface = Color(80,80,80),
            isLight = false
        )
    ) {
        AppWindowAmbient.current?.events?.onOpen = {
            try {

            } catch (exception: Exception) {
                println("File not found")
            }
        }
        AppWindowAmbient.current?.events?.onClose = {
            // TODO написать сериализацию
        }
        Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colors.background)) {
            UIBookList()
        }
    }
}
