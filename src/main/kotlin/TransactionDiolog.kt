import androidx.compose.desktop.AppWindowAmbient
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun TransactionViewDiolog(
    onDismissFun: () -> Unit,
    transactionList: List<TransactionHistory>,
    windowName: String = "Choose file"
) {
    val isFirst = mutableStateOf(true)
    Dialog(onDismissRequest = onDismissFun) {
        MaterialTheme(
            shapes = AppMaterialScheme.Shapes,
            colors = AppMaterialScheme.Colors
        ) {
            if (isFirst.value) {
                AppWindowAmbient.current?.setTitle(windowName)
                AppWindowAmbient.current?.setSize(600, 400)
                isFirst.value = false
            }
            Box(Modifier.background(Color(80, 80, 80))) {
                Row {
                    Column(modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.SpaceBetween) {
                        Button(modifier = Modifier.preferredWidth(200.dp).padding(top = 5.dp, bottom = 5.dp),
                            onClick = {
                                onDismissFun()
                            }) {
                            Text("Назад")
                        }
                    }
                    Box(
                        modifier = Modifier.fillMaxHeight()
                            .background(color = Color(120, 120, 120))
                            .padding(10.dp)
                    ) {
                        val stateVertical = rememberScrollState(0f)

                        ScrollableColumn(
                            modifier = Modifier.fillMaxHeight(),
                            scrollState = stateVertical
                        ) {
                            if (transactionList.isNotEmpty()) {
                                for (transaction in transactionList)
                                    Box(
                                        modifier = Modifier.fillMaxWidth().preferredSize(40.dp, 40.dp)
                                            .border(2.dp, MaterialTheme.colors.secondary)
                                            .padding(start = 5.dp),
                                        contentAlignment = Alignment.CenterStart
                                    ) {
                                        Text(transaction.toString())
                                    }
                            } else
                                Box(Modifier.fillMaxWidth())
                        }
                        VerticalScrollbar(
                            modifier = Modifier.align(Alignment.CenterEnd)
                                .fillMaxHeight(),
                            adapter = rememberScrollbarAdapter(stateVertical)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TransactionAddDiolog(
    onDismissFun: () -> Unit,
    transactionHistory: MutableList<TransactionHistory>,
    windowName: String = "Choose file"
) {
    val isFirst = mutableStateOf(true)
    Dialog(onDismissRequest = onDismissFun) {
        MaterialTheme(
            shapes = AppMaterialScheme.Shapes,
            colors = AppMaterialScheme.Colors
        ) {
            if (isFirst.value) {
                AppWindowAmbient.current?.setTitle(windowName)
                AppWindowAmbient.current?.setSize(600, 400)
                isFirst.value = false
            }
            Box(Modifier.background(Color(80, 80, 80))) {
                Row(horizontalArrangement = Arrangement.Start) {
                    Column(modifier = Modifier.fillMaxHeight()) {
                        Button(modifier = Modifier.preferredWidth(200.dp),
                            onClick = {
                                onDismissFun()
                            }) {
                            Text("Назад")
                        }
                        Button(modifier = Modifier.preferredWidth(200.dp),
                        onClick = {

                        }
                        ) {
                            Text("Добавить")
                        }
                    }
                    Column(modifier = Modifier.fillMaxHeight()) {
                        val menuExpanded = remember { mutableStateOf(false) }
                        val bookStatus = remember { mutableStateOf(BookStatus.AVAILABLE) }
                        val year = remember { mutableStateOf("") }
                        val month = remember { mutableStateOf("") }
                        val day = remember { mutableStateOf("") }
                        val comment = remember { mutableStateOf("") }
                        val userName = remember { mutableStateOf("") }
                        DropdownMenu(
                            toggle = {
                                Button(
                                    { menuExpanded.value = !menuExpanded.value },
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text("${bookStatus.value}")
                                }
                            },
                            expanded = menuExpanded.value,
                            onDismissRequest = { menuExpanded.value = false }
                        ) {
                            DropdownMenuItem(onClick = {
                                bookStatus.value = BookStatus.AVAILABLE
                                menuExpanded.value = false
                            }) {
                                Text("${BookStatus.AVAILABLE}")
                            }
                            DropdownMenuItem(onClick = {
                                bookStatus.value = BookStatus.TAKEN
                                menuExpanded.value = false
                            }) {
                                Text("${BookStatus.TAKEN}")
                            }
                            DropdownMenuItem(onClick = {
                                bookStatus.value = BookStatus.UTILIZED
                                menuExpanded.value = false
                            }) {
                                Text("${BookStatus.UTILIZED}")
                            }
                            DropdownMenuItem(onClick = {
                                bookStatus.value = BookStatus.COMING_SOON
                                menuExpanded.value = false
                            }) {
                                Text("${BookStatus.COMING_SOON}")
                            }
                        }
                        DataInputRow(Modifier.fillMaxWidth(), "День", day.value, singleLine = true) { str ->
                            run {
                                day.value = str
                            }
                        }
                        DataInputRow(Modifier.fillMaxWidth(), "Месяц", month.value, singleLine = true) { str ->
                            run {
                                month.value = str
                            }
                        }
                        DataInputRow(Modifier.fillMaxWidth(), "Год", year.value, singleLine = true) { str ->
                            run {
                                year.value = str
                            }
                        }
                        DataInputRow(
                            Modifier.fillMaxWidth(),
                            "Имя пользователя",
                            userName.value,
                            singleLine = true
                        ) { str ->
                            run {
                                userName.value = str
                            }
                        }
                        DataInputRow(Modifier.fillMaxWidth(), "Комментарий", comment.value, singleLine = true) { str ->
                            run {
                                comment.value = str
                            }
                        }
                    }
                }
            }
        }
    }
}

//@Composable
//fun TransactionListButton(transactionList: List<TransactionHistory>) {
//    val isChoosingFile = remember { mutableStateOf(false) }
//    Button(modifier = Modifier.fillMaxWidth(),
//        onClick = {
//            isChoosingFile.value = true
//        }) {
//        Text("Выбрать файл")
//    }
//    if (isChoosingFile.value) {
//        TransactionDiolog(
//            onDismissFun = { isChoosingFile.value = false },
//            transactionList
//        )
//    }
//}