import androidx.compose.desktop.AppWindowAmbient
import androidx.compose.desktop.Window
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import java.io.File

@Suppress("EXPERIMENTAL_API_USAGE")
fun main() = Window(title = "Well, course work") {
    MaterialTheme(
        shapes = AppMaterialScheme.Shapes,
        colors = AppMaterialScheme.Colors
    ) {
        AppWindowAmbient.current?.events?.onOpen = {
            try {
                var tempCatalogue = BooksCatalogue.getMutableInstance()
                tempCatalogue.addAll(BooksCatalogue.decodeToMutableList("booksCatalogue.library"))
            } catch (exception: Exception) {
                println("Books file not found")
            }
            try {
                var tempUsers = Users.getMutableInstance()
                tempUsers.addAll(Users.decodeToMutableList("usersList.users"))
            } catch (exception: Exception) {
                println("Users file not found")
            }
        }
        AppWindowAmbient.current?.events?.onClose = {
            BooksCatalogue.encodeToFile("booksCatalogue.library", BooksCatalogue.getImmutableInstance())
            Users.encodeToFile("usersList.users", Users.getImmutableInstance())
        }

        val state = remember { mutableStateOf(State.MainMenu) }
        Box(Modifier.background(MaterialTheme.colors.surface)) {
            Column(
                Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                when (state.value) {
                    State.Login -> {
                        Text("Cast in the name of God", fontSize = 50.sp)
                        UserButton(state)
                        AdministratorButton(state)
                        Text("Ye not guilty", fontSize = 50.sp)
                    }
                    State.UserLogin -> {
                        UserLoginWindow(state)
                    }
                    State.UserError -> {
                        UserErrorWindow(state)
                    }
                    State.Administrator,
                    State.User -> {
                        MainMenu(state)
                    }
                }
            }
        }
    }
}
