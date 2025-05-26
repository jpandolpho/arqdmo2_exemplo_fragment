package br.edu.ifsp.arqdmo2.exemplofragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import br.edu.ifsp.arqdmo2.exemplofragment.databinding.FragmentExerciciosBinding

class ExerciciosFragment : Fragment() {
    private lateinit var binding: FragmentExerciciosBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentExerciciosBinding.inflate(inflater, container,
            false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.buttonSpeak.setOnClickListener {
            Toast.makeText(
                requireContext(),
                "Iniciar reconhecimento",
                Toast.LENGTH_LONG
            ).show()
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
    }
}