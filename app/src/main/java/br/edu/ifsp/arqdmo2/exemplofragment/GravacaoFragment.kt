package br.edu.ifsp.arqdmo2.exemplofragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import br.edu.ifsp.arqdmo2.exemplofragment.databinding.FragmentGravacaoBinding
import br.edu.ifsp.arqdmo2.exemplofragment.helpers.GravacaoHelper
import java.io.File

class GravacaoFragment : Fragment(), GravacaoHelper.Callback {
    private lateinit var binding: FragmentGravacaoBinding
    private lateinit var gravacaoHelper: GravacaoHelper
    private val gravacoes = mutableListOf<File>()
    private var isGravando = false
    private lateinit var adapter: GravacoesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentGravacaoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        gravacaoHelper = GravacaoHelper(requireContext(), this)
        adapter = GravacoesAdapter(gravacoes)
        binding.recyclerRecordings.layoutManager =
            LinearLayoutManager(requireContext())
        binding.recyclerRecordings.adapter = adapter
        binding.btnRecord.setOnClickListener {
            if (!isGravando) {
                gravacaoHelper.iniciarGravacao(binding.etTituloGravacao.text.toString())
            } else {
                gravacaoHelper.pararGravacao()
            }
        }
        carregarGravacoes()
    }

    private fun carregarGravacoes() {
        gravacoes.clear()
        val dir = requireContext().getExternalFilesDir(null)
        dir?.listFiles()?.filter { it.extension == "3gp" }?.let {
            gravacoes.addAll(it)
            adapter.notifyDataSetChanged()
        }
    }

    override fun onGravacaoIniciada() {
        binding.textStatus.text = "Gravando..."
        binding.btnRecord.text = "Parar Gravação"
        isGravando = true
    }

    override fun onGravacaoFinalizada(arquivo: File) {
        binding.textStatus.text = "Gravação salva: ${arquivo.name}"
        binding.btnRecord.text = "Iniciar Gravação"
        carregarGravacoes()
        isGravando = false
    }

    override fun onErroGravacao(mensagem: String) {
        Toast.makeText(
            requireContext(), mensagem,
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        gravacaoHelper.pararGravacao()
    }
}