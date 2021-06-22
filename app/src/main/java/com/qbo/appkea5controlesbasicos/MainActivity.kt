package com.qbo.appkea5controlesbasicos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.qbo.appkea5controlesbasicos.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener, AdapterView.OnItemSelectedListener {
    //1. findViewById: etnombre = findViewById(R.id.etnombre)
    //private lateinit var etnombre: EditText! Kotlin-Java
    //2. Syntetyc: Se añade plugin en el Gradle y la referencia es directa - Kotlin
    //3. ButterKnife: Libreria externa. Kotlin-Java
    //4. ViewBinding: Kotlin-Java
    private lateinit var binding : ActivityMainBinding
    private val listaPreferencias = ArrayList<String>()
    private val listaPersonas = ArrayList<String>()
    private var estadoCivil = ""


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
        binding.spestadocivil.onItemSelectedListener = this
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

    private fun validarPreferencias(): Boolean{
        var respuesta = false
        if(binding.cbdeporte.isChecked || binding.cbdibujo.isChecked ||
                binding.cbotros.isChecked){
            respuesta = true
        }
        return respuesta

    }

    override fun onClick(vista: View) {
        if(vista is CheckBox){
            agregarQuitarPreferencia(vista)
        } else {
            when (vista.id){
                R.id.btnregistrar -> registrarPresona()
                R.id.btnverpersonas -> listarPersonas()
            }
        }
    }

    private fun listarPersonas() {
        TODO("Not yet implemented")
    }

    private fun registrarPresona() {
        TODO("Not yet implemented")
    }

    private fun agregarQuitarPreferencia(vista: View) {
        val checkbox = vista as CheckBox
        if(checkbox.isChecked){
            when(checkbox.id){
                R.id.cbdeporte -> listaPreferencias.add(checkbox.text.toString())
                R.id.cbdibujo -> listaPreferencias.add(checkbox.text.toString())
                R.id.cbotros -> listaPreferencias.add(checkbox.text.toString())
            }
        }else{
            when(checkbox.id){
                R.id.cbdeporte -> listaPreferencias.remove(checkbox.text.toString())
                R.id.cbdibujo -> listaPreferencias.remove(checkbox.text.toString())
                R.id.cbotros -> listaPreferencias.remove(checkbox.text.toString())
            }
        }

    }

    override fun onItemSelected(parent: AdapterView<*>?, p1: View?, posicion: Int, p3: Long) {
        estadoCivil = if(posicion > 0){
            parent!!.getItemAtPosition(posicion).toString()
        }else ""
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }
}