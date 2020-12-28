import androidx.compose.desktop.AppWindowAmbient
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import java.io.File

@Composable
fun FileChooserDiolog(onDismissFun: () -> Unit, directory: File, windowName: String = "Choose file") {
    val isFirst = mutableStateOf(true)
    Dialog(onDismissRequest = onDismissFun) {
        MaterialTheme(
            shapes = Shapes(RoundedCornerShape(0.dp), RoundedCornerShape(0.dp), RoundedCornerShape(0.dp)),
            colors = MaterialTheme.colors.copy(
                primary = Color(80, 50, 50),
                onPrimary = Color.Black
            )
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

                            }) {
                            Text("Выбрать")
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
                            val listOfFiles = directory.listFiles()
                            if (listOfFiles.isNotEmpty()) {
                                for (file in listOfFiles)
                                    ListElement(file.name)
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
fun FileChooserButton(state: MutableState<State>, BaseFolder: File) {
    val isChoosingFile = remember { mutableStateOf(false) }
    Button(modifier = Modifier.width(300.dp),
        onClick = {
            isChoosingFile.value = true
        }) {
        Text("Выбрать файл")
    }
    if (isChoosingFile.value) {
        FileChooserDiolog(
            onDismissFun = { isChoosingFile.value = false },
            BaseFolder
        )
    }
}
