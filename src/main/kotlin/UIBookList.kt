import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

@Composable
fun UIBookList(/*Some kinda user*/) {
    //TODO("Make user do this instead of direct call.")
    val books = BooksCatalogue.getMutableInstance()// for now


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