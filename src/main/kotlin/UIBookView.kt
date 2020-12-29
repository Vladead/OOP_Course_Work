import androidx.compose.desktop.AppWindowAmbient
import androidx.compose.desktop.Window
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.DropdownMenu
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.skija.Image
import java.io.File

@Composable
fun UIBookView(
    book: BookCopy,
    state: MutableState<State>,
    onDismissRequest: () -> Unit
) {
    val isFirst = mutableStateOf(true)
    val showingTransactionHistory = remember { mutableStateOf(false) }
    val path = File(System.getProperty("user.dir") + "\\Images" + "\\${book.imagePath}")
    val isAdmin = state.value.access
    Window(onDismissRequest = onDismissRequest) {
        MaterialTheme(
            shapes = AppMaterialScheme.Shapes,
            colors = AppMaterialScheme.Colors
        ) {
            if (isFirst.value) {
                AppWindowAmbient.current?.setTitle("Скрижаль - ${book.name}")
                AppWindowAmbient.current?.setSize(800, 400)
                isFirst.value = false
            }
            Box(Modifier.background(MaterialTheme.colors.surface).fillMaxSize()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        Image.makeFromEncoded(path.readBytes()).asImageBitmap(),
                        modifier = Modifier.padding(20.dp).border(2.dp, Color.Black).sizeIn(maxWidth = 500.dp)
                    )
                    Box(
                        modifier = Modifier.fillMaxHeight().preferredSize(800.dp, 800.dp)
                    ) {
                        Column(
                            modifier = Modifier.align(Alignment.TopStart)
                                .padding(
                                    top = 10.dp,
                                    end = 10.dp,
                                    start = 10.dp,
                                    bottom = if (isAdmin) 90.dp else 20.dp
                                )
                        ) {
                            Text(book.name, fontSize = 20.sp)
                            Text(book.authors, fontSize = 20.sp)
                            Box(Modifier.border(1.dp, MaterialTheme.colors.secondary)) {
                                val scrollState = rememberScrollState()
                                ScrollableColumn(scrollState = scrollState) {
                                    Text(book.description, modifier = Modifier.padding(start = 3.dp).fillMaxSize())
                                }
                                VerticalScrollbar(
                                    modifier = Modifier.align(Alignment.TopEnd),
                                    adapter = ScrollbarAdapter(scrollState)
                                )
                            }
                        }
                        if (isAdmin)
                            Button(
                                onClick = { showingTransactionHistory.value = true },
                                modifier = Modifier.preferredSize(300.dp, 70.dp).padding(bottom = 20.dp)
                                    .align(Alignment.BottomCenter)
                            ) {
                                Text("Изучить легенды")
                            }
                        if (showingTransactionHistory.value) {
                            TransactionViewDiolog(
                                onDismissFun = { showingTransactionHistory.value = false },
                                book.getAllTransaction(), windowName = "Легенды"
                            )
                        }
                    }
                }
            }
        }
    }
}