package uta.edu.ec

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uta.edu.ec.ui.theme.DeviceItemView
import uta.edu.ec.ui.theme.DevicesTheme
import uta.edu.ec.ui.theme.Typography

@Composable
fun MainView(modifier: Modifier, devices: List<Device>) {
    // Envolvemos todo en un Column para que se apilen verticalmente
    Column(modifier = modifier) {
        Text(
            text = "Comprar",
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            style = Typography.displayMedium,
            textAlign = TextAlign.Center
        )

        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(devices.size) { index ->
                DeviceItemView(device = devices[index])
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainPreview() {
    DevicesTheme {
        MainView(
            Modifier.padding(top = 30.dp), listOf(
                Device(id = 1, name = "Nexus", data = Specs(color = "Black", capacity = "64 GB")),
                Device(id = 2, name = "Galaxy", data = null)
            )
        )
    }
}
