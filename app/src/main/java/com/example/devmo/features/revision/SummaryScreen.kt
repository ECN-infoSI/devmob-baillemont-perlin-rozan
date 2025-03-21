package com.example.devmo.features.revision

import androidx.compose.foundation.layout.*

import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.devmo.features.timer.viewmodel.TimerViewModel

@Composable
fun SummaryScreen(timerViewModel: TimerViewModel) {
    val revisions by timerViewModel.revisions.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = "Récapitulatif des révisions", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(16.dp))

        // Afficher les statistiques dans des Card
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            StatisticCard(label = "Total temps de révision", value = timerViewModel.getTotalHours())
            StatisticCard(label = "Temps cette semaine", value = timerViewModel.getWeeklyHours())
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            StatisticCard(label = "Matière la plus révisée", value = timerViewModel.getMostRevisedSubject())
            StatisticCard(label = "Matière la moins révisée", value = timerViewModel.getLeastRevisedSubject())
        }

        Spacer(modifier = Modifier.height(16.dp))

        Column {
            Text(text = "Historique des révisions",
                fontWeight = FontWeight.Bold
            )
        // Afficher le récapitulatif des révisions
        if (revisions.isEmpty()) {
            Text(text = "Aucune révision enregistrée.")
        } else {
            revisions.forEach { revision ->
                Text(text = "Heure de début: ${revision.startTime}, Durée: ${revision.duration} secondes, Matière: ${revision.subject}")
                Spacer(modifier = Modifier.height(8.dp))
            }
        }    } }
}
fun calculateFontSize(text: String): TextUnit {
    return when {
        text.length > 20 -> 12.sp // Réduire la taille de la police si le texte est long
        text.length > 15 -> 14.sp
        text.length > 10 -> 16.sp
        else -> 18.sp // Taille de police par défaut
    }
}

@Composable
fun StatisticCard(label: String, value: String) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .size(150.dp),

    ) {
        Box(
            modifier = Modifier.padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = label, fontSize = calculateFontSize(label))
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = value, fontSize = calculateFontSize(label))
            }
        }
    }

}