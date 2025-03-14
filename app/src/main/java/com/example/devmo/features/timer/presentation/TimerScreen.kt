package com.example.devmo.features.timer.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.devmo.features.timer.viewmodel.TimerViewModel
import com.example.devmo.utils.formatTime
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.devmo.utils.formatTime
import androidx.compose.foundation.layout.Box

@OptIn(ExperimentalMaterial3Api::class) // Ajout de l'annotation pour ExposedDropdownMenuBox
@Composable
fun TimerScreenContent(timerViewModel: TimerViewModel) {
    val timerValue by timerViewModel.timer.collectAsState()

    TimerScreen(
        timerValue = timerValue,
        onStartClick = { timerViewModel.startTimer() },
        onPauseClick = { timerViewModel.pauseTimer() },
        onStopClick = { timerViewModel.stopTimer() }
    )
}

@OptIn(ExperimentalMaterial3Api::class) // Ajout de l'annotation pour ExposedDropdownMenuBox
@Composable
fun TimerScreen(
    timerValue: Long,
    onStartClick: () -> Unit,
    onPauseClick: () -> Unit,
    onStopClick: () -> Unit
) {
    val totalTime = 2700L
    val progress = if (totalTime > 0) (timerValue.toFloat() / totalTime.toFloat()) else 0f

    var expanded by remember { mutableStateOf(false) } // État pour contrôler l'expansion du menu
    var selectedMatiere by remember { mutableStateOf("") } // Matière sélectionnée
    val matieres = listOf("Mathématiques", "Physique", "Chimie", "Biologie", "Histoire", "Géographie", "Informatique") // Liste des matières

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                progress = { progress },
                modifier = Modifier.size(150.dp),
                strokeWidth = 10.dp
            )
            Text(
                text = timerValue.formatTime(),
                fontSize = 24.sp
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = onStartClick) {
                Text("Start")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = onPauseClick) {
                Text("Pause")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = onStopClick) {
                Text("Stop")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        ExposedDropdownMenuBox( // Remplacement du bouton par un ExposedDropdownMenuBox
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .align(Alignment.CenterHorizontally)
        ) {
            OutlinedTextField(
                readOnly = true,
                value = selectedMatiere,
                onValueChange = { },
                label = { Text("Matière en cours de révision") },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                },
                modifier = Modifier.menuAnchor()
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                matieres.forEach { matiere ->
                    DropdownMenuItem(
                        text = { Text(matiere) },
                        onClick = {
                            selectedMatiere = matiere
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}