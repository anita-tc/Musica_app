package com.example.musica

import android.R
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

// CREAR CLASE DATABASEHELPER QUE HEREDA DE LA CLASE SQLIYTE
class DataBaseHelper(context: Context) : SQLiteOpenHelper (
    context,
    DATABASE_NAME,
    null,
    DATABASE_VERSION
){

    // DATOS DE LA BASE DE DATOS

    companion object{
        //INFORMACIÓN DE LA BD
        private const val DATABASE_NAME = "musica.db"
        private const val DATABASE_VERSION = 2

        // IMPORTACION DE LAS TABLAS EN LA BD

        private const val TABLE_USUARIOS = "usuarios"
        private const val COL_ID = "id"
        private const val COL_NOMBRE = "nombre"
        private const val COL_CORREO = "correo"
        private const val COL_PASSWORD = "password"

    }

    // METODO ONCREATE -- para crear la base de datos
    override fun onCreate(db: SQLiteDatabase?) {
        // CREAR LA TABLA
        val  crearTablaUsuarios = """
            CREATE TABLE $TABLE_USUARIOS(
            $COL_ID INTEGER PRIMARY KEY AUTOINCREMENT,
            $COL_NOMBRE TEXT NOT NULL,
            $COL_CORREO TEXT NOT NULL,
            $COL_PASSWORD TEXT NOT NULL
            )
          """.trimIndent()

        // CREAR TABLA DE USUARIO
        db?.execSQL(crearTablaUsuarios)
    }

    // METODO ONUPGRADE
    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?. execSQL("DROP TABLE IF EXISTS $TABLE_USUARIOS")
        onCreate(db)
    }

    // METODO PARA REGISTRAR UN USUARIO
    fun registrarUsuario(nombre : String, corre : String, password : String) : Boolean {

        // CREAR VARIABLE PARA ESCRIBIR EN LA BD
        val db = writableDatabase

        // INSERTAR LOS DATOS
        val datos = ContentValues().apply {
            put(COL_NOMBRE, nombre)
            put(COL_CORREO, corre)
            put(COL_PASSWORD, password)
        }

        // EJECUTAR INSERCION DE DATOS
        val resultado = db.insert(TABLE_USUARIOS, null, datos)

        // CERRAR CONEXION DE LA DB
        db.close()

        // REGRESAR EL VALOR OBTENIDO DE LA INSERCION ( verdadero o falso )
        return resultado != -1L

    }
}