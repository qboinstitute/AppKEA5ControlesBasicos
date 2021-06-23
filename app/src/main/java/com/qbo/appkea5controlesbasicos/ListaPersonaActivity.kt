package com.qbo.appkea5controlesbasicos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.R
import com.qbo.appkea5controlesbasicos.databinding.ActivityListaPersonaBinding

class ListaPersonaActivity : AppCompatActivity() {
    private lateinit var binding : ActivityListaPersonaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListaPersonaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val listaPersonas = intent.getSerializableExtra("listapersonas")
                as ArrayList<String>
        val adapter = ArrayAdapter(
            this,
            R.layout.simple_list_item_1,
            listaPersonas
        )
        binding.lvpersonas.adapter = adapter


    }
}