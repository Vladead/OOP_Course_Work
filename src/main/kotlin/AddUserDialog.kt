import androidx.compose.desktop.AppWindowAmbient
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog

@Composable
fun AddUserDialog(
    onDismissRequest: () -> Unit,
    onConfirm: (String) -> Unit,
) {
    val isFirst = mutableStateOf(true)
    val userName = mutableStateOf("")

    Dialog(onDismissRequest = onDismissRequest) {
        MaterialTheme(
            shapes = AppMaterialScheme.Shapes,
            colors = AppMaterialScheme.Colors
        ) {
            if (isFirst.value) {
                AppWindowAmbient.current?.setTitle("Зал приглашений")
                AppWindowAmbient.current?.setSize(400, 400)
                isFirst.value = false
            }
            Box(Modifier.background(MaterialTheme.colors.surface)) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceAround,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    DataInputRow(
                        name = "Имя школяра",
                        curValue = userName.value,
                        singleLine = true
                    ) { userName.value = it }
                    Row(horizontalArrangement = Arrangement.SpaceAround) {
                        Button(onClick = {
                            onConfirm(userName.value)
                        }, enabled = userName.value.isNotBlank()) {
                            Text("Пригласить")
                        }
                        Button(onClick = onDismissRequest) {
                            Text("Передумать")
                        }
                    }
                }
            }
        }
    }
}