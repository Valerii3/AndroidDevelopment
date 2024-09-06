package com.example.state

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.state.ui.theme.StateTheme

@Composable
fun StatelessWaterCounter(count: Int, onIcrement: () -> Unit, modifier: Modifier = Modifier) {
    if (count > 0) {
        Text("You've had $count glasses.")
    }
    Button(onClick = onIcrement, Modifier.padding(top = 8.dp), enabled = count < 10) {
        Text("Add one")
    }
}

@Composable
fun StatefullWaterCounter(modifier: Modifier = Modifier) {
    var count by rememberSaveable { mutableStateOf(0) }
    Column(modifier = modifier.padding(16.dp)) {
        StatelessWaterCounter(count = count, onIcrement = { count++ }, modifier)
    }
    

}

@Preview(showBackground = true, widthDp = 420, heightDp = 320)
@Composable
fun WaterCounterPreview() {
    StateTheme {
        StatefullWaterCounter()
    }
}