import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

object AppMaterialScheme {
    private val AppColors = Colors(
        primary = Color(160, 50, 50),
        onPrimary = Color.Black,
        secondary = Color.DarkGray,
        onSecondary = Color.Black,
        background = Color(80, 80, 80),
        onBackground = Color.Black,
        surface = Color(80, 80, 80),
        onSurface = Color.Black,
        isLight = false,
        primaryVariant = Color(160, 50, 50),
        secondaryVariant = Color.DarkGray,
        error = Color.Red,
        onError = Color.Black
    )
    private val AppShapes = Shapes(RoundedCornerShape(0.dp), RoundedCornerShape(0.dp), RoundedCornerShape(0.dp))

    val Shapes: Shapes
        get() = AppShapes.copy()

    val Colors: Colors
        get() = AppColors.copy()
}