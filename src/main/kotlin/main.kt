import androidx.compose.desktop.AppWindowAmbient
import androidx.compose.desktop.Window
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.*

@Suppress("EXPERIMENTAL_API_USAGE")
fun main() = Window(title = "Well, course work") {
    MaterialTheme(
        shapes = Shapes(RoundedCornerShape(0.dp), RoundedCornerShape(0.dp), RoundedCornerShape(0.dp)),
        colors = MaterialTheme.colors.copy(
            primary = Color(80, 50, 50),
            onPrimary = Color.Black,
            background = Color(80, 80, 80)
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

        val state = remember { mutableStateOf(State.Login) }
        Box(Modifier.background(Color(80, 80, 80))) {
            Column(
                Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                when (state.value) {
                    State.Login -> {
                        Text("Хто я?", fontSize = 100.sp)
                        createUserButton(state)
                        createAdministratorButton(state)
                    }
                    State.UserLogin -> {
                        createUserLoginWindow(state)
                    }
                    State.UserError -> {
                        createUserErrorWindow(state)
                    }
                    State.User -> {
                        createUserWindow(state)
                    }
                    State.Administrator -> {
                        createAdministratorWindow()
                    }
                }
            }
        }
    }
}

enum class State (access: Boolean) {
    Login(false),
    User(false),
    UserLogin(false),
    UserError(false),
    Administrator(true)
}

@Composable
fun createUserButton(state: MutableState<State>) {
    Button(modifier = Modifier.width(300.dp),
        onClick = { state.value = State.UserLogin }) {
        Text("Пользователь")
    }
}

@Composable
fun createUserWindow(state: MutableState<State>) {
    Text("В разработке", fontSize = 20.sp)
}

@Composable
fun createUserLoginWindow(state: MutableState<State>) {
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val userName = remember { mutableStateOf("") }
        DataInputRow("Имя", userName.value, onValueChange = { str ->
            run {
                userName.value = str
            }
        })

        val checkUserName = remember { mutableStateOf(false) }
        Button(modifier = Modifier.fillMaxWidth(),
            onClick = {
                checkUserName.value = true
            }) {
            Text("Войти")
        }
        val correctName = "performance artist"
        if (checkUserName.value)
            if (userName.value == correctName) {
                state.value = State.User
            } else {
                checkUserName.value = false
                state.value = State.UserError
            }
    }
}

@Composable
fun createUserErrorWindow(state: MutableState<State>) {
    Text("Ты не туда зашел, ♂fucking slave♂", fontSize = 20.sp)
    Button(modifier = Modifier.width(300.dp),
        onClick = { state.value = State.UserLogin }) {
        Text("Назад")
    }
}

@Composable
fun createAdministratorButton(state: MutableState<State>) {
    Button(modifier = Modifier.width(300.dp),
        onClick = { state.value = State.Administrator }) {
        Text("Администратор")
    }
}

@Composable
fun createAdministratorWindow() {
    Text("Добро пожаловать, ♂boss of this gym♂", fontSize = 20.sp)
}
