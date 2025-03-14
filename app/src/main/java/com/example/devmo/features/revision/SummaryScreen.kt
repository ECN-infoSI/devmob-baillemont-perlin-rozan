package com.example.devmo.features.revision
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.devmo.features.timer.viewmodel.TimerViewModel

@Composable
fun SummaryScreen(timerViewModel: TimerViewModel = viewModel()) {
    val revisions by timerViewModel.revisions.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = "Récapitulatif des révisions", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(16.dp))

        if (revisions.isEmpty()) {
            Text(text = "Aucune révision enregistrée.")
        } else {
            revisions.forEach { revision ->
                Text(text = "Heure de début: ${revision.startTime}, Durée: ${revision.duration} secondes")
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}