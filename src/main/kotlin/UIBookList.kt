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
    val books =
        if (state.value.access) BooksCatalogue.getMutableInstance()
        else BooksCatalogue.getImmutableInstance()
    val filteredBooks = mutableStateListOf<BookCopy>()
    val isAdmin = state.value.access

    val vertScroll = rememberScrollState()
    val selection = remember { mutableStateOf<Int?>(null) }
    val viewOpened = remember { mutableStateOf(false) }
    val nameFilter = remember { mutableStateOf("") }
    val authorFilter = remember { mutableStateOf("") }

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
                onValueChange = {
                    nameFilter.value = it
                    filteredBooks.clear()
                    filteredBooks += books.filter { it.name.contains(nameFilter.value, true) }
                        .filter { it.name.contains(authorFilter.value, true) }
                })
            DataInputRow(
                Modifier.preferredSize(300.dp, 60.dp),
                "Поиск писца",
                curValue = authorFilter.value,
                onValueChange = {
                    authorFilter.value = it
                    filteredBooks.clear()
                    filteredBooks += books.filter { it.name.contains(nameFilter.value, true) }
                        .filter { it.name.contains(authorFilter.value, true) }
                })
        }
        Box() {
            Box(
                modifier = Modifier.align(Alignment.TopStart).padding(bottom = if (isAdmin) 150.dp else 50.dp)
            ) {
                if (books.isEmpty()) {
                    ScrollableColumn(
                        modifier = Modifier.fillMaxWidth().padding(end = 8.dp).border(1.dp, Color(20, 20, 20)),
                        scrollState = vertScroll,
                        contentPadding = PaddingValues(0.dp, 0.dp, 0.dp, 0.dp),
                        horizontalAlignment = Alignment.Start
                    ) {
                        repeat(50) {
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
                                Text("${it + 1}")
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
                        onClick = {}) {
                        Text("Внести писание")
                    }
                }
            }
        }
    }

    if (viewOpened.value) {
        UIBookView(null, state, onDismissRequest = { viewOpened.value = false }, onAddTransaction = {}) {}
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