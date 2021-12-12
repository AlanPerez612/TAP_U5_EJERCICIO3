package com.example.tap_u5_ejercicio3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val arregloDinamico = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener{
            arregloDinamico.add(frase.text.toString())
            frase.setText("")
            cargarDatosEnListView()
        }
    }
    fun cargarDatosEnListView(){

        listafrases.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,arregloDinamico)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_ventana1,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.enviarsms ->{val ventana2 = Intent(this,MainActivity2::class.java)

                ventana2.putExtra("Frase sms",frase.text.toString())

            startActivity(ventana2)
            }
            R.id.acercade ->{
                AlertDialog.Builder(this)
                    .setTitle("¡Cuidado!")
                    .setMessage("(C) Alan Covarrubias Pérez")
                    .setPositiveButton("OK",{d,i ->})
                    .show()
            }
            R.id.borrar ->{
                val indiceBorrar = EditText(this)
                indiceBorrar.inputType = InputType.TYPE_CLASS_NUMBER
                indiceBorrar.setText("0")

                AlertDialog.Builder(this)
                    .setTitle("¡Atención!")
                    .setMessage("Implemente el índice a borrar:")
                    .setView(indiceBorrar)
                    .setPositiveButton("Borrar",{d,i ->
                        val indice = indiceBorrar.text.toString().toInt()
                        arregloDinamico.removeAt(indice)
                        Toast.makeText(this,"Se borró el índice ${indice}",Toast.LENGTH_LONG)
                            .show()
                        cargarDatosEnListView()
                    })
                    .setNegativeButton("Cancelar",{d,i ->})
                    .show()
            }
            R.id.salir ->{finish()}
        }
        return true
    }
}