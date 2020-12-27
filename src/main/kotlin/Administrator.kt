import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AdministratorButton(state: MutableState<State>) {
    Button(modifier = Modifier.width(300.dp),
        onClick = { state.value = State.Administrator }) {
        Text("Администратор")
    }
}

@Composable
fun AdministratorWindow() {
    Text("Добро пожаловать, ♂boss of this gym♂", fontSize = 20.sp)
}