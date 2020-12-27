import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MainMenu(state: MutableState<State>) {
    val menuState = remember { mutableStateOf(mainMenuState.Books) }
    Row {
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Button(
                    onClick = { menuState.value = mainMenuState.Books },
                    modifier = Modifier.preferredSize(300.dp, 50.dp)
                ) {
                    Text("Скрижали")
                }
                if (state.value.access)
                    Button(
                        onClick = { menuState.value = mainMenuState.Users },
                        modifier = Modifier.preferredSize(300.dp, 50.dp)
                    ) {
                        Text("Школяры")
                    }
            }
            Button(onClick = { state.value = State.Login }, modifier = Modifier.preferredSize(300.dp, 50.dp)) {
                Text("Бегство")
            }
        }
        when (menuState.value) {
            mainMenuState.Books ->
                UIBookList(state)
            mainMenuState.Users -> {

            }
        }
    }
}