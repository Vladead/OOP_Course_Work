import androidx.compose.desktop.AppWindowAmbient
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import java.lang.Exception
import java.util.*

@Composable
fun TransactionViewDialog(
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
fun TransactionAddDialog(
    onDismissFun: () -> Unit,
    bookCopy: BookCopy,
    windowName: String) {
    val isFirst = mutableStateOf(true)
    val bookStatus = remember { mutableStateOf(BookStatus.AVAILABLE) }
    val year = remember { mutableStateOf("") }
    val month = remember { mutableStateOf("") }
    val day = remember { mutableStateOf("") }
    val userName = remember { mutableStateOf("") }
    val comment = remember { mutableStateOf("") }

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
                        Button(
                            modifier = Modifier.preferredWidth(200.dp),
                            onClick = {
                                val untilDate = Calendar.getInstance(Locale.ENGLISH)
                                if (bookStatus.value == BookStatus.TAKEN) {
                                    untilDate.set(year.value.toInt(), month.value.toInt()-1, day.value.toInt())
                                }
                                bookCopy.addNewTransaction(
                                    comment = comment.value,
                                    date = Calendar.getInstance(Locale.ENGLISH),
                                    status = bookStatus.value,
                                    userData = if (bookStatus.value == BookStatus.TAKEN) UserTransactionData(
                                        untilDate,
                                        userName.value
                                    ) else null
                                )
                                onDismissFun()
                            },
                            enabled = (checkTransactionData(
                                day = day.value,
                                month = month.value,
                                year = year.value,
                                userName = userName.value
                            ) || bookStatus.value != BookStatus.TAKEN) && comment.value.isNotBlank()
                        ) {
                            Text("Добавить")
                        }
                    }

                    Column(modifier = Modifier.fillMaxHeight()) {
                        val menuExpanded = remember { mutableStateOf(false) }
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

                        DataInputRow(Modifier.fillMaxWidth(), "Комментарий", comment.value, singleLine = true) { str ->
                            run {
                                comment.value = str
                            }
                        }

                        if (bookStatus.value == BookStatus.TAKEN) {
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
                            DataInputRow(
                                Modifier.fillMaxWidth(),
                                "День",
                                day.value,
                                singleLine = true,
                                keyboardType = KeyboardType.Number
                            ) { str ->
                                run {
                                    day.value = str
                                }
                            }
                            DataInputRow(
                                Modifier.fillMaxWidth(),
                                "Месяц",
                                month.value,
                                singleLine = true,
                                keyboardType = KeyboardType.Number
                            ) { str ->
                                run {
                                    month.value = str
                                }
                            }
                            DataInputRow(
                                Modifier.fillMaxWidth(),
                                "Год",
                                year.value,
                                singleLine = true,
                                keyboardType = KeyboardType.Number
                            ) { str ->
                                run {
                                    year.value = str
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

fun checkTransactionData(day: String, month: String, year: String, userName: String): Boolean {
    val tempDay = day.toIntOrNull()
    val tempMonth = month.toIntOrNull()
    val tempYear = year.toIntOrNull()

    try {
        val testCalendar = Calendar.getInstance(Locale.ENGLISH)
        testCalendar.set(tempYear!!, tempMonth!!-1, tempDay!!)
    } catch (e: Exception) {
        return false
    }

    if (!Users.getImmutableInstance().contains(userName)) {
        return false
    }

    return true
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