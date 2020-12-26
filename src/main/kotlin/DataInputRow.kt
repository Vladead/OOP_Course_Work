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

@Composable
fun DataInputRow(name: String, curValue: String, onValueChange: (String) -> Unit) {
    Row(Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically) {
        Text(name)
        TextField(curValue,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                onValueChange = onValueChange,
                placeholder = { Text("Value") }
        )
    }
}
