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
fun UIBookList(state: MutableState<State>) {
    val books = BooksCatalogue.getMutableInstance()
    val filteredBooks = remember { mutableStateListOf<BookCopy>() }
    val isAdmin = state.value.access

    val isFirst = remember{ mutableStateOf(true) }
    val vertScroll = rememberScrollState()
    val selection = remember { mutableStateOf<Int?>(null) }
    val nameFilter = remember { mutableStateOf("") }
    val authorFilter = remember { mutableStateOf("") }
    val viewOpened = remember { mutableStateOf(false) }
    val addDialog = remember { mutableStateOf(false) }

    if (isFirst.value) {
        filteredBooks+=books
        isFirst.value = false
    }

    Column() {
        Row(
            Modifier.fillMaxWidth().border(1.dp, Color(20, 20, 20)),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            DataInputRow(
                Modifier.preferredSize(300.dp, 60.dp),
                "Поиск писания",
                curValue = nameFilter.value,
                singleLine = true
            ) { str ->
                nameFilter.value = str
                filteredBooks.clear()
                filteredBooks += books.filter { it.name.contains(nameFilter.value, true) || str.isEmpty() }
                    .filter { it.authors.contains(authorFilter.value, true) || str.isEmpty() }
            }
            DataInputRow(
                Modifier.preferredSize(300.dp, 60.dp),
                "Поиск писца",
                curValue = authorFilter.value,
                singleLine = true
            ) { str ->
                authorFilter.value = str
                filteredBooks.clear()
                filteredBooks += books.filter { it.name.contains(nameFilter.value, true) || str.isEmpty() }
                    .filter { it.authors.contains(authorFilter.value, true) || str.isEmpty() }
            }
        }
        Box() {
            Box(
                modifier = Modifier.align(Alignment.TopStart).padding(bottom = if (isAdmin) 150.dp else 50.dp)
            ) {
                if (filteredBooks.isNotEmpty()) {
                    ScrollableColumn(
                        modifier = Modifier.fillMaxSize().padding(end = 8.dp).border(1.dp, Color(20, 20, 20)),
                        scrollState = vertScroll,
                        contentPadding = PaddingValues(0.dp, 0.dp, 0.dp, 0.dp),
                        horizontalAlignment = Alignment.Start
                    ) {
                        repeat(filteredBooks.size) {
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
                                Text("${filteredBooks[it].name}//${filteredBooks[it].authors}")
                            }
                        }
                    }
                    VerticalScrollbar(
                        modifier = Modifier.align(Alignment.TopEnd),
                        adapter = ScrollbarAdapter(vertScroll)
                    )
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
                    onClick = { viewOpened.value = true },
                    enabled = (selection.value != null)
                ) {
                    Text("О сём писании")
                }
                if (isAdmin) {
                    Button(
                        modifier = Modifier.fillMaxWidth().preferredSize(300.dp, 50.dp),
                        onClick = {},
                        enabled = (selection.value != null)
                    ) {
                        Text("Дополнить легенду")
                    }
                    Button(modifier = Modifier.fillMaxWidth().preferredSize(300.dp, 50.dp),
                        onClick = { addDialog.value = true }) {
                        Text("Внести писание")
                    }
                }
            }
        }
    }

    if (viewOpened.value) {
        UIBookView(null, state, onDismissRequest = { viewOpened.value = false }, onAddTransaction = {}) {}
    }

    if (addDialog.value) {
        AddBookDialog(onDismissRequest = { addDialog.value = false }, onConfirm = {
            BooksCatalogue.addCopy(it)
            addDialog.value = false
        })
    }
}


@Composable
fun UIBookListElement(/*Some kinda user*/) {
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
        Row(horizontalArrangement = Arrangement.Start) {

        }
        Row(horizontalArrangement = Arrangement.End) {

        }
    }
}