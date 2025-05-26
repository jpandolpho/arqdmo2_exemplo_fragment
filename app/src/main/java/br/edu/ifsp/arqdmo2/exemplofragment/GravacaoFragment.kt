package br.edu.ifsp.arqdmo2.exemplofragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import br.edu.ifsp.arqdmo2.exemplofragment.databinding.FragmentGravacaoBinding

class GravacaoFragment : Fragment() {
    private lateinit var binding: FragmentGravacaoBinding
    private var isGravando = false
    private lateinit var adapter: GravacoesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGravacaoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = GravacoesAdapter(mutableListOf())
        binding.recyclerRecordings.layoutManager =
            LinearLayoutManager(requireContext())
        binding.recyclerRecordings.adapter = adapter
        binding.btnRecord.setOnClickListener {
            if (isGravando) {
                Toast.makeText(
                    requireContext(),
                    "Parar gravação",
                    Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(
                    requireContext(),
                    "Iniciar gravação",
                    Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}