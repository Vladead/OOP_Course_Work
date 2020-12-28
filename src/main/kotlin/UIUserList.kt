import androidx.compose.desktop.AppManager
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun UIUserList(state: MutableState<State>) {
    val users = Users.getMutableInstance()
//    val usersList = remember { Users.getMutableInstance().toMutableStateList() }

    val vertScroll = rememberScrollState()
    val selection = remember { mutableStateOf<Int?>(null) }

    val deleteDialog = remember { mutableStateOf(false) }
    val addDialog = remember { mutableStateOf(false) }

    Box() {
        Box(
            modifier = Modifier.align(Alignment.TopStart)
                .padding(bottom = 100.dp)
                .border(1.dp, Color(20, 20, 20))
        ) {
            if (users.isNotEmpty()) {
                ScrollableColumn(
                    modifier = Modifier.fillMaxWidth().padding(end = 8.dp),
                    scrollState = vertScroll,
                    contentPadding = PaddingValues(0.dp, 0.dp, 0.dp, 0.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    repeat(users.size) {
                        Box(
                            modifier = Modifier.fillMaxWidth().preferredSize(40.dp, 40.dp)
                                .border(2.dp, MaterialTheme.colors.secondary)
                                .selectable(
                                    selected = (it == selection.value),
                                    onClick = { selection.value = it })
                                .background(if (it == selection.value) MaterialTheme.colors.secondary else MaterialTheme.colors.background)
                                .padding(start = 5.dp),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            Text(users[it])
                        }
                    }
                }
                VerticalScrollbar(modifier = Modifier.align(Alignment.TopEnd), adapter = ScrollbarAdapter(vertScroll))
            } else {
                Box(modifier = Modifier.fillMaxSize().border(1.dp, Color(20, 20, 20)))
            }
        }
        Column(
            Modifier.align(Alignment.BottomStart).fillMaxWidth().border(1.dp, Color(20, 20, 20)),
            horizontalAlignment = Alignment.Start
        ) {
            Button(
                modifier = Modifier.fillMaxWidth().preferredSize(300.dp, 50.dp),
                onClick = {deleteDialog.value = true},
                enabled = (selection.value != null)
            ) {
                Text("Изгнать школяра")
            }
            Button(modifier = Modifier.fillMaxWidth().preferredSize(300.dp, 50.dp),
                onClick = { addDialog.value = true }) {
                Text("Пригласить школяра")
            }

        }
    }

    if (deleteDialog.value) {
        ConfirmationDialog(
            "Действительно изгнать ${users[selection.value!!]}?",
            confirmText = "Погнать",
            cancelText = "Передумать",
            windowName = "Точно?",
            onDismissRequest = { deleteDialog.value = false },
            onConfirm = {
                Users.removeUserAt(selection.value!!)
                selection.value = null
                deleteDialog.value = false
            })
    }

    if (addDialog.value) {
        AddUserDialog(
            onDismissRequest = { addDialog.value = false },
            onConfirm = {
                if ((users.find { str -> str == it }) == null) {
                    Users.addUser(it)
//                    users.add(it)
                }
                addDialog.value = false
                selection.value = users.size - 1
            })
    }
}