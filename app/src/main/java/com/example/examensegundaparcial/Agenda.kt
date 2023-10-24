package com.example.examensegundaparcial

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.examensegundaparcial.databinding.ActivityAgendaBinding


class Agenda : AppCompatActivity() {
    private lateinit var binding: ActivityAgendaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgendaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = binding.toolbar2
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Detalles"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val buscar = intent.getStringExtra("buscar").toString()
        val contactos = intent.getParcelableArrayListExtra<Contacto>("contactos")?.toMutableList()

        binding.agenda.text = "Registro no encontrado"

        if (buscar.isEmpty()) {
            binding.agenda.text = ""
            contactos?.forEach { Contacto ->
                binding.agenda.append("Nombre: ${Contacto.nombre}\nControl: ${Contacto.control}\n\n")
            }
        }
        else {
            for (Contacto in contactos!!){
                if (buscar == Contacto.control){
                    binding.agenda.text = ("Nombre: ${Contacto.nombre}\nControl: ${Contacto.control}\n")
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}