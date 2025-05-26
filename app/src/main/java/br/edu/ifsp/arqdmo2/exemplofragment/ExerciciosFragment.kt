package br.edu.ifsp.arqdmo2.exemplofragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.edu.ifsp.arqdmo2.exemplofragment.databinding.FragmentExerciciosBinding
import br.edu.ifsp.arqdmo2.exemplofragment.helpers.ReconhecimentoHelper

class ExerciciosFragment : Fragment(), ReconhecimentoHelper.Callback {
    private lateinit var binding: FragmentExerciciosBinding
    private lateinit var reconhecimentoHelper: ReconhecimentoHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentExerciciosBinding.inflate(
            inflater, container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        reconhecimentoHelper = ReconhecimentoHelper(requireContext(), this)
        binding.buttonSpeak.setOnClickListener {
            reconhecimentoHelper.iniciarReconhecimento("en-US")
        }
    }

    override fun onReconhecimentoFinalizado(texto: String?) {
        binding.textInput.text = texto
        compararTranscricao(texto ?: "")
    }

    override fun onErroReconhecimento(mensagem: String) {
        binding.textFeedback.text = mensagem
    }

    private fun compararTranscricao(transcricao: String) {
        val original =
            binding.exerciseText.text.toString().trim().lowercase()
        val falado = transcricao.trim().lowercase()
        val feedback = when {
            falado == original -> "Ótimo! Transcrição correta 🎉"
            original in falado || falado in original -> "Quase lá! Sua pronúncia está próxima."
            else -> "Tente novamente. Preste atenção na pronúncia."
        }
        binding.textFeedback.text = feedback
    }

    override fun onDestroyView() {
        super.onDestroyView()
        reconhecimentoHelper.liberar()
    }
}