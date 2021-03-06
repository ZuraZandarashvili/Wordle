import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.SnackbarData
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.collect

// need a better name
@Composable
fun SnackbarContainer() {
  val snackbarHostState = remember { SnackbarHostState() }

  LaunchedEffect(Unit) {
    vm.snackbarSharedFlow.collect() {
      snackbarHostState.showSnackbar(it)
    }
  }

  SnackbarHost(
    hostState = snackbarHostState,
    snackbar = { snackbarData: SnackbarData ->
      Box(Modifier.fillMaxSize()) {
        Card(
          Modifier
            .padding(200.dp)
            .align(Alignment.TopCenter)
            .background(Color.Black)
            .padding(16.dp)
        ) {
          Text(
            modifier = Modifier.background(Color.Black),
            text = snackbarData.message,
            color = Color.White
          )
        }
      }
    }
  )
}