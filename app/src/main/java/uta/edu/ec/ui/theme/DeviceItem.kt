package uta.edu.ec.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.test.espresso.base.Default
import uta.edu.ec.Device
import uta.edu.ec.Specs

@Composable
fun DeviceItemView(device: Device) {
    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(end =16.dp, top = 8.dp, bottom = 8.dp)) {
        Icon(imageVector = Icons.Default.Phone,
            contentDescription = null,
            modifier = Modifier.padding(horizontal = 16.dp))
        Column {
            Text(text = device.name,
            style = Typography.headlineMedium
            )
            if (device.data?.color != null) {
                Text(text = device.data?.color ?: "-",
                    style = Typography.bodySmall)
            }

            if (device.data?.color != null) {
                Text(text = device.data?.capacity ?: "-",
                    style = Typography.bodySmall)
            }


//            Text(text = device.data?.color ?: "-",
//                style = Typography.bodySmall)
//            Text(text = device.data?.capacity ?: "-",
//                style = Typography.bodySmall)

            HorizontalDivider()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DeviceItemPreview() {
    DevicesTheme {
        DeviceItemView(
            device = Device(id = 1, name = "Nexus", data = Specs(color = "Black", capacity = "64 GB"))
        )
    }
}