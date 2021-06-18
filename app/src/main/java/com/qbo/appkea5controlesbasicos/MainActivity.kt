package com.qbo.appkea5controlesbasicos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import com.qbo.appkea5controlesbasicos.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    //1. findViewById: etnombre = findViewById(R.id.etnombre)
    //private lateinit var etnombre: EditText! Kotlin-Java
    //2. Syntetyc: Se añade plugin en el Gradle y la referencia es directa - Kotlin
    //3. ButterKnife: Libreria externa. Kotlin-Java
    //4. ViewBinding: Kotlin-Java
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Agregando información al spinner
        ArrayAdapter.createFromResource(this,
        R.array.estado_civil_array,
        android.R.layout.simple_spinner_item).also {
            adapter->
            adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item)
            binding.spestadocivil.adapter = adapter
        }
        //Elementos con evento clic
        binding.btnregistrar.setOnClickListener(this)
        binding.btnverpersonas.setOnClickListener(this)
        binding.cbdeporte.setOnClickListener(this)
        binding.cbdibujo.setOnClickListener(this)
        binding.cbotros.setOnClickListener(this)
    }

    fun validarGenero() : Boolean{
        var respuesta = true
        if(binding.rggenero.checkedRadioButtonId == -1){
            respuesta = false
        }
        return respuesta
    }

    fun validarNombreApellido():Boolean{
        var respuesta = true
        if(binding.etnombre.text.toString().isEmpty()){
            binding.etnombre.isFocusableInTouchMode = true
            binding.etnombre.requestFocus()
            respuesta = false
        } else if(binding.etapellido.text.toString().isEmpty()){
            binding.etapellido.isFocusableInTouchMode = true
            binding.etapellido.requestFocus()
            respuesta = false
        }
        return respuesta
    }

    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
    }
}