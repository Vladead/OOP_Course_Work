import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun DataInputRow(modifier: Modifier = Modifier, name: String, curValue: String, keyboardType: KeyboardType = KeyboardType.Text, singleLine: Boolean, onValueChange: (String) -> Unit) {
        TextField(curValue,
            modifier = modifier,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            onValueChange = onValueChange,
            placeholder = { },
            singleLine = singleLine,
            label = { Text(name) }
        )
}
