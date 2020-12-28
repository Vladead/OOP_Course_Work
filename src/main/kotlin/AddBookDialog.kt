import androidx.compose.desktop.AppWindowAmbient
import androidx.compose.desktop.Window
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import java.io.File
import org.jetbrains.skija.Image

@Composable
fun AddBookDialog(
    onDismissRequest: () -> Unit,
    onConfirm: (BookCopy) -> Unit,
) {
    val isFirst = mutableStateOf(true)
    val BaseFolder = File(System.getProperty("user.dir") + "\\Images")
    val selectedFile = remember { mutableStateOf<File?>(null) }
    val storedImage = mutableStateOf(ByteArray(0))
    val bookName = mutableStateOf("")
    val authorsName = mutableStateOf("")
    val description = mutableStateOf("")

    Dialog(onDismissRequest = onDismissRequest) {
        MaterialTheme(
            shapes = AppMaterialScheme.Shapes,
            colors = AppMaterialScheme.Colors
        ) {
            if (isFirst.value) {
                AppWindowAmbient.current?.setTitle("Писчий зал")
                AppWindowAmbient.current?.setSize(800, 500)
                isFirst.value = false
            }
            Box(Modifier.background(MaterialTheme.colors.surface)) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(
                        modifier = Modifier.fillMaxHeight().padding(end = 40.dp, start = 40.dp, top = 40.dp)
                    ) {
                        if (storedImage.value.isEmpty())
                            Box(modifier = Modifier) {
                            } else
                            Image(Image.makeFromEncoded(storedImage.value).asImageBitmap(),
                                modifier = Modifier.padding(bottom = 90.dp).align(Alignment.TopStart))
                        FileChooserButton(
                            Modifier.preferredSize(300.dp, 50.dp).align(Alignment.BottomCenter),
                            BaseFolder,
                            selectedFile,
                            onFileChosen = {
                                storedImage.value =
                                    selectedFile.value?.readBytes() ?: ByteArray(0)
                            })
                    }
                    Box(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Column(Modifier.align(Alignment.TopCenter)) {
                            DataInputRow(
                                modifier = Modifier.preferredSize(400.dp, 60.dp),
                                name = "Имя писания",
                                curValue = bookName.value,
                                singleLine = true
                            ) { bookName.value = it }
                            DataInputRow(
                                modifier = Modifier.preferredSize(400.dp, 60.dp),
                                name = "Имена писцов",
                                curValue = authorsName.value,
                                singleLine = false
                            ) { authorsName.value = it }
                            DataInputRow(
                                modifier = Modifier.fillMaxSize()
                                    .padding(bottom = 90.dp),
                                name = "Подробно",
                                curValue = description.value,
                                singleLine = false
                            ) { description.value = it }
                        }
                        Row(modifier = Modifier.align(Alignment.BottomCenter),
                            horizontalArrangement = Arrangement.SpaceAround) {
                            Button(
                                modifier = Modifier.preferredSize(200.dp, 50.dp),
                                onClick = {
                                    onConfirm(BookCopy(bookName.value,
                                        authorsName.value,
                                        selectedFile.value!!.name,
                                        description.value))
                                },
                                enabled = (bookName.value.isNotBlank() &&
                                        authorsName.value.isNotBlank() &&
                                        description.value.isNotBlank() &&
                                        storedImage.value.isNotEmpty())) {
                                Text("Внести писание")
                            }
                            Button(modifier = Modifier.preferredSize(200.dp, 50.dp),
                                onClick = onDismissRequest) {
                                Text("Передумать")
                            }
                        }
                    }
                }
            }
        }
    }
}