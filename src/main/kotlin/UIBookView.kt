import androidx.compose.desktop.AppWindowAmbient
import androidx.compose.desktop.Window
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun UIBookView(book: BookCopy, onDismissRequest: () -> Unit) {
    val isFirst = mutableStateOf(true)
    Window(onDismissRequest = onDismissRequest) {
        MaterialTheme(
            shapes = Shapes(RoundedCornerShape(0.dp), RoundedCornerShape(0.dp), RoundedCornerShape(0.dp)),
            colors = MaterialTheme.colors.copy(
                primary = Color(80, 50, 50),
                onPrimary = Color.Black,
                background = Color(80,80,80)
            )
        ) {
            if (isFirst.value) {
                AppWindowAmbient.current?.setTitle("${book.name}")
                AppWindowAmbient.current?.setSize(600, 400)
                isFirst.value = false
            }
            Box(Modifier.background(Color(80, 80, 80))) {

            }
        }
    }
}