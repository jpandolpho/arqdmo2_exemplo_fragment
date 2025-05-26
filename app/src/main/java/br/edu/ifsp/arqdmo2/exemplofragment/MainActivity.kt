package br.edu.ifsp.arqdmo2.exemplofragment

import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import br.edu.ifsp.arqdmo2.exemplofragment.databinding.ActivityMainBinding
import android.Manifest
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val PERMISSOES_REQUERIDAS = arrayOf(
        Manifest.permission.RECORD_AUDIO
    )
    private val REQUEST_PERMISSION_CODE = 101
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction()
            .replace(R.id.container_fragment, ExerciciosFragment())
            .commit()
        if (!temPermissoesNecessarias()) {
            ActivityCompat.requestPermissions(
                this,
                PERMISSOES_REQUERIDAS,
                REQUEST_PERMISSION_CODE)
        }
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_exercicios -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container_fragment, ExerciciosFragment())
                        .commit()
                    true
                }
                R.id.nav_gravacoes -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container_fragment, GravacoesFragment())
                        .commit()
                    true
                }
                else -> false
            }
        }
    }
    private fun temPermissoesNecessarias(): Boolean {
        return PERMISSOES_REQUERIDAS.all {
            ContextCompat.checkSelfPermission(this, it) ==
                    PackageManager.PERMISSION_GRANTED
        }
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions,
            grantResults)
        if (requestCode == REQUEST_PERMISSION_CODE) {
            if (grantResults.any { it != PackageManager.PERMISSION_GRANTED })
            {
                Toast.makeText(
                    this,
                    "Permissões necessárias não concedidas. O app pode não funcionar corretamente.",
                            Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}