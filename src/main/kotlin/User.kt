import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun UserButton(state: MutableState<State>) {
    Button(modifier = Modifier.width(300.dp),
        onClick = { state.value = State.UserLogin }) {
        Text("Пользователь")
    }
}

@Composable
fun UserLoginWindow(state: MutableState<State>) {
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val userName = remember { mutableStateOf("") }
        DataInputRow(Modifier.fillMaxWidth() ,"Имя", userName.value) { str ->
            run { userName.value = str }
        }
        Spacer(Modifier.padding(bottom = 2.dp))
        val checkUserName = remember { mutableStateOf(false) }
        Button(modifier = Modifier.fillMaxWidth(),
            onClick = {
                checkUserName.value = true
            }) {
            Text("Войти")
        }
        if (checkUserName.value)
            if (Users.getImmutableInstance().contains(userName.value)) {
                state.value = State.User
            } else {
                checkUserName.value = false
                state.value = State.UserError
            }
    }
}

@Composable
fun UserErrorWindow(state: MutableState<State>) {
    Text("Ты не туда зашел, ♂fucking slave♂", fontSize = 20.sp)
    Button(modifier = Modifier.width(300.dp),
        onClick = { state.value = State.Login }) {
        Text("Назад")
    }
}