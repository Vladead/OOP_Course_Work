import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun DataInputRow(modifier: Modifier = Modifier, name: String, curValue: String, onValueChange: (String) -> Unit) {
        TextField(curValue,
            modifier = modifier,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            onValueChange = onValueChange,
            placeholder = { },
            singleLine = true,
            label = { Text(name) }
        )
}
