import androidx.compose.desktop.AppWindowAmbient
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import java.util.*

@Composable
fun UIViewTakenDialog(
    onDismissFun: () -> Unit,
    booksList: List<BookCopy>,
    windowName: String,
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
                            if (booksList.isNotEmpty()) {
                                for (book in booksList)
                                    Box(
                                        modifier = Modifier.fillMaxWidth()
                                            .border(2.dp, MaterialTheme.colors.secondary)
                                            .padding(start = 5.dp),
                                        contentAlignment = Alignment.CenterStart
                                    ) {
                                        Text(book.name + "//" + book.authors + "; until " + "${
                                            book.getLastTransaction()!!.user!!.until.get(Calendar.YEAR)
                                        }/${
                                            book.getLastTransaction()!!.user!!.until.get(
                                                Calendar.MONTH) + 1
                                        }/${
                                            book.getLastTransaction()!!.user!!.until.get(Calendar.DAY_OF_MONTH)
                                        }")
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