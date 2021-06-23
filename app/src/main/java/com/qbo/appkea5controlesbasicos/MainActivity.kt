package com.qbo.appkea5controlesbasicos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.qbo.appkea5controlesbasicos.commom.AppMensaje
import com.qbo.appkea5controlesbasicos.commom.TipoMensaje
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
    private fun validarEstadoCivil():Boolean{
        var respuesta = true
        if(estadoCivil == ""){
            respuesta = false
        }
        return respuesta
    }
    fun validarFormulario(): Boolean{
        var respuesta = false
        if(!validarNombreApellido()){
            AppMensaje.enviarMensaje(binding.root,
                getString(R.string.errorNombreApellido),
                TipoMensaje.ERROR)
        }else if(!validarGenero()){
            AppMensaje.enviarMensaje(binding.root,
                getString(R.string.errorGenero),
                TipoMensaje.ERROR)
        }else if(!validarEstadoCivil()){
            AppMensaje.enviarMensaje(binding.root,
                getString(R.string.errorEstadoCivil),
                TipoMensaje.ERROR)
        }else if(!validarPreferencias()){
            AppMensaje.enviarMensaje(binding.root,
                getString(R.string.errorPreferencias),
                TipoMensaje.ERROR)
        }else{
            respuesta = true
        }
        return respuesta
    }

    override fun onClick(vista: View) {
        if(vista is CheckBox){
            agregarQuitarPreferencia(vista)
        } else {
            when (vista.id){
                R.id.btnregistrar -> registrarPersona()
                R.id.btnverpersonas -> listarPersonas()
            }
        }
    }

    private fun listarPersonas() {
        TODO("Not yet implemented")
    }

    private fun registrarPersona() {
        if(validarFormulario()){
            var infoPersona = binding.etnombre.text.toString() + " " +
                    binding.etapellido.text.toString() + " " +
                    obtenerGeneroSeleccionado() + " " +
                    obtenerPreferenciasSeleccionadas() + " " +
                    estadoCivil + " " +
                    binding.swnotificacion.isChecked
            listaPersonas.add(infoPersona)
            AppMensaje.enviarMensaje(binding.root,
            getString(R.string.exitoRegistroPersona),
            TipoMensaje.EXITO)
            setearControles()
        }
    }
    private fun obtenerPreferenciasSeleccionadas(): String {
        var preferencias = ""
        for(pref in listaPreferencias){
            preferencias += "$pref -"
        }
        return preferencias
    }

    private fun obtenerGeneroSeleccionado(): String {
        var genero = ""
        when(binding.rggenero.checkedRadioButtonId){
            R.id.rbfemenino -> genero = binding.rbfemenino.text.toString()
            R.id.rbmasculino -> genero = binding.rbmasculino.text.toString()
        }
        return genero
    }

    private fun setearControles(){
        listaPreferencias.clear()
        binding.etnombre.setText("")
        binding.etapellido.setText("")
        binding.swnotificacion.isChecked = false
        binding.cbdeporte.isChecked = false
        binding.cbdibujo.isChecked = false
        binding.cbotros.isChecked = false
        binding.rggenero.clearCheck()
        binding.spestadocivil.setSelection(0)
        binding.etnombre.isFocusableInTouchMode = true
        binding.etnombre.requestFocus()
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