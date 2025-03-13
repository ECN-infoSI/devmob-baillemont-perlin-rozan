package com.example.devmo.ui.slideshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.devmo.databinding.FragmentSlideshowBinding
import com.example.devmo.features.timer.presentation.TimerScreenContent
import com.example.devmo.features.timer.viewmodel.TimerViewModel

class SlideshowFragment : Fragment() {

    private var _binding: FragmentSlideshowBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val timerViewModel: TimerViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSlideshowBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Ajoutez TimerScreenContent à votre layout
        // Assurez-vous que TimerScreenContent est un composant de vue valide
        // Si TimerScreenContent est un Composable, vous devez utiliser un ComposeView
        binding.timerScreenContainer.apply {
            // Si TimerScreenContent est un Composable, utilisez un ComposeView
            setContent {
                TimerScreenContent(timerViewModel)
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}