package com.example.musica

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class RegisterActivity : AppCompatActivity() {

    // AQUI SE DECLARAN LOS ELEMENTOS
    private lateinit var etNombre : EditText
    private lateinit var etCorreo : EditText
    private lateinit var etPassword : EditText
    private lateinit var btnRegistrar: Button

    // CREAR INSTANCIA DEL DATABASEHELPER
    private lateinit var dbHelper: DataBaseHelper


    // METODO PRINCIPAL
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)

        // INICIALIZAR LOS OBJETOS DE LOS COMPONENTES
        etNombre = findViewById(R.id.etNombre)
        etCorreo = findViewById(R.id.etCorreo)
        etPassword = findViewById(R.id.etPass)
        btnRegistrar = findViewById(R.id.btnRegistrar)

        // INICIALIZAR LA BASE DE DATOS EN UN OBJETO
        dbHelper = DataBaseHelper(this)

        // EVENTO PARA REGISTRAR AL USUARIO
        btnRegistrar.setOnClickListener {
            // REGISTRAR AL USUARIO
            registrarUsuario()

        }

    }

    // METODOS POR AQUI

    private fun registrarUsuario(){
        // VARIABLES PARA GUARDAR LOS TEXTOS
        val nombre = etNombre.text.toString().trim()
        val correo  = etCorreo.text.toString().trim()
        val password = etPassword.text.toString().trim()

        if(nombre.isEmpty() || correo.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

        // VARIABLE QUE GUARDA EL RESULTADO DEL REGISTRO Y LLAMA A LA BD
        val registro = dbHelper.registrarUsuario(nombre, correo, password)

        // VALIDAR QUE EL RESULTADO DE LA INSERCION SEA VERDADERO O FALSO
        if (registro){
            // SI EL REGISTRO ES CORRECTO
            Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
            // CIERRA LA PANTALLA ACTUAL Y REGRESA A LA PANTALLA ANTERIOR
            finish()
        }else{
            Toast.makeText(this, "Error al registrar", Toast.LENGTH_SHORT).show()
        }



    }

}