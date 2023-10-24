package com.example.examensegundaparcial

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.widget.Toast
import com.example.examensegundaparcial.databinding.ActivityMainBinding
import java.util.Objects

class Contacto(var nombre: String, var control: String) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nombre)
        parcel.writeString(control)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Contacto> {
        override fun createFromParcel(parcel: Parcel): Contacto {
            return Contacto(parcel)
        }

        override fun newArray(size: Int): Array<Contacto?> {
            return arrayOfNulls(size)
        }
    }
}

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Examen Segunda Parcial"

        val contactos = mutableListOf<Contacto>()

        binding.botonAgregar.setOnClickListener {
            val nombreCompleto = binding.txtNombreCompleto.text.toString()
            val noControl = binding.txtNoControl.text.toString()
            if(nombreCompleto.isNotEmpty() && noControl.isNotEmpty()){
                contactos.add(Contacto(nombreCompleto, noControl))
                binding.contadorContactos.text = "Contactos: ${contactos.size}"
                val toast = Toast.makeText(this, "Contacto agregado", Toast.LENGTH_SHORT)
                toast.show()
            }
            else {
                val toast = Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT)
                toast.show()
            }
        }

        binding.BotonVerAgenda.setOnClickListener{
            if (contactos.isNotEmpty()){
                val buscar = binding.txtBuscar.text.toString()
                val intent = Intent(this, Agenda::class.java)
                intent.putExtra("buscar", buscar)
                intent.putParcelableArrayListExtra("contactos", ArrayList(contactos))
                startActivity(intent)
            } else {
                val toast = Toast.makeText(this, "No hay contactos aún", Toast.LENGTH_SHORT)
                toast.show()
            }
        }
    }
}