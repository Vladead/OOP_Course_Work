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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import java.io.File

@Composable
fun FileChooserDialog(
    onDismissFun: () -> Unit,
    directory: File,
    windowName: String = "Choose file",
    selectedFile: MutableState<File?>,
) {
    val isFirst = mutableStateOf(true)
    val selection = mutableStateOf<File?>(null)
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
                                if (selection.value != null) {
                                    selectedFile.value = selection.value
                                    onDismissFun()
                                }
                            }) {
                            Text("Выбрать")
                        }
                    }
                    Box(
                        modifier = Modifier.fillMaxHeight()
                            .background(color = Color(120, 120, 120))
                    ) {
                        val stateVertical = rememberScrollState(0f)

                        ScrollableColumn(
                            modifier = Modifier.fillMaxHeight(),
                            scrollState = stateVertical
                        ) {
                            val listOfFiles = directory.listFiles()
                            if (listOfFiles.isNotEmpty()) {
                                for (file in listOfFiles)
                                    Box(
                                        modifier = Modifier.fillMaxWidth().preferredSize(40.dp, 40.dp)
                                            .border(2.dp, MaterialTheme.colors.secondary)
                                            .selectable(
                                                selected = (file == selection.value),
                                                onClick = { selection.value = file })
                                            .background(if (file == selection.value) MaterialTheme.colors.secondary else MaterialTheme.colors.background)
                                            .padding(start = 5.dp),
                                        contentAlignment = Alignment.CenterStart
                                    ) {
                                        Text(file.name)
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
fun FileChooserButton(
    modifier: Modifier = Modifier.preferredSize(300.dp, 50.dp),
    BaseFolder: File,
    selectedFile: MutableState<File?>,
    onFileChosen: () -> Unit,
) {
    val isChoosingFile = remember { mutableStateOf(false) }
    Button(modifier = modifier,
        onClick = {
            isChoosingFile.value = true
        }) {
        Text("Выбрать файл")
    }
    if (isChoosingFile.value) {
        FileChooserDialog(
            onDismissFun = {
                isChoosingFile.value = false;
                onFileChosen()
            },
            BaseFolder,
            selectedFile = selectedFile
        )
    }
}
