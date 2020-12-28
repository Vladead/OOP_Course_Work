import androidx.compose.desktop.AppWindowAmbient
import androidx.compose.desktop.Window
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.imageFromResource
import androidx.compose.ui.unit.dp

@Composable
fun UIBookView(
    book: BookCopy?,
    state: MutableState<State>,
    onDismissRequest: () -> Unit,
    onAddTransaction: () -> Unit,
    onViewTransactions: () -> Unit
) {
    val isFirst = mutableStateOf(true)
    val isAdmin = state.value.access
    Window(onDismissRequest = onDismissRequest) {
        MaterialTheme(
            shapes = AppMaterialScheme.Shapes,
            colors = AppMaterialScheme.Colors
        ) {
            if (isFirst.value) {
                AppWindowAmbient.current?.setTitle("Скрижали - ${book?.name}")
                AppWindowAmbient.current?.setSize(600, 400)
                isFirst.value = false
            }
            Box(Modifier.background(MaterialTheme.colors.surface).fillMaxSize()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        imageFromResource("zone_archive.png"),
                        modifier = Modifier.border(2.dp, Color.DarkGray).padding(20.dp)
                    )
                    Column(
                        modifier = Modifier.fillMaxHeight().preferredSize(800.dp, 800.dp),
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Column(modifier = Modifier.padding(top = 10.dp)) {
                            Text("${book?.name}")
                            Text("${book?.authors}")
                        }
                        Text("${book?.description}")
                        Spacer(Modifier.padding(bottom = 10.dp))
                        if (isAdmin)
                            Column(
                                modifier = Modifier.padding(bottom = 10.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Button(onClick = onViewTransactions, modifier = Modifier.preferredSize(300.dp, 50.dp)) {
                                    Text("Изучить легенды")
                                }
                                Button(onClick = onAddTransaction, modifier = Modifier.preferredSize(300.dp, 50.dp)) {
                                    Text("Дополнить легенду")
                                }
                            }
                    }
                }
            }
        }
    }
}