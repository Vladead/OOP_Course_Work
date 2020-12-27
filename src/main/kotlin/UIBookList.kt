import androidx.compose.animation.AnimatedVisibility
import androidx.compose.desktop.AppWindowAmbient
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.WithConstraints
import androidx.compose.ui.selection.SimpleLayout
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp

@Composable
fun UIBookList(state: MutableState<State>) {
    val books = BooksCatalogue.getMutableInstance()
    val isAdmin = state.value.access

    val vertScroll = rememberScrollState()
    val selected = remember { mutableStateOf<Int?>(null) }
    val viewSummoned = remember { mutableStateOf(false) }
    Box() {
        Box(
            modifier = Modifier.align(Alignment.TopStart).padding(bottom = if (isAdmin) 150.dp else 50.dp)
        ) {
            ScrollableColumn(
                modifier = Modifier.fillMaxWidth().padding(end = 8.dp).border(1.dp, Color(20, 20, 20)),
                scrollState = vertScroll,
                contentPadding = PaddingValues(0.dp, 0.dp, 0.dp, 0.dp),
                horizontalAlignment = Alignment.Start
            ) {
                repeat(50) {
                    Box(
                        modifier = Modifier.fillMaxWidth().preferredSize(40.dp, 40.dp).border(2.dp, Color.DarkGray)
                            .background(if (it == selected.value) Color.DarkGray else MaterialTheme.colors.background)
                            .selectable(
                                selected = (it == selected.value),
                                onClick = { selected.value = it })
                    ) {
                        Text("${it+1}")
                    }
                }
            }
            VerticalScrollbar(modifier = Modifier.align(Alignment.TopEnd), adapter = ScrollbarAdapter(vertScroll))
        }
        Column(
            Modifier.align(Alignment.BottomStart).fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Button(
                modifier = Modifier.fillMaxWidth().preferredSize(300.dp, 50.dp),
                onClick = { viewSummoned.value = true },
                enabled = (selected.value != null)
            ) {
                Text("О сём писании")
            }
            if (isAdmin) {
                Button(
                    modifier = Modifier.fillMaxWidth().preferredSize(300.dp, 50.dp),
                    onClick = {},
                    enabled = (selected.value != null)
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

    if (viewSummoned.value) {
        UIBookView(null, onDismissRequest = { viewSummoned.value = false }, {})
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