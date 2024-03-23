package com.example.basictaskapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.basictaskapplication.databinding.ActivityMainBinding
import androidx.appcompat.app.AlertDialog
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val tasks = mutableListOf<Task>() // Lista para armazenar as tarefas

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        binding.fab.setOnClickListener { view ->
            val popupTitulo = AlertDialog.Builder(this)
            val textTitulo = EditText(this)

            popupTitulo.setTitle("Nova Tarefa")
            popupTitulo.setView(textTitulo)
            popupTitulo.setPositiveButton("Próximo") { _, _ ->
                val titulo = textTitulo.text.toString()

                val popupDescricao = AlertDialog.Builder(this)
                val textDescricao = EditText(this)

                popupDescricao.setTitle("Descrição")
                popupDescricao.setView(textDescricao)
                popupDescricao.setPositiveButton("Próximo") { _, _ ->
                    val descricao = textDescricao.text.toString()

                    val spinnerStatus = Spinner(this)
                    val statusOptions = arrayOf("Pendente", "Concluída")

                    val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, statusOptions)
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spinnerStatus.adapter = adapter

                    val popupStatus = AlertDialog.Builder(this)
                    popupStatus.setTitle("Status")
                    popupStatus.setView(spinnerStatus)
                    popupStatus.setPositiveButton("Ok") { _, _ ->
                        val status = spinnerStatus.selectedItem.toString()

                        val task = Task(titulo, descricao, status)
                        tasks.add(task)

                        Snackbar.make(view, "Tarefa criada: $task", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show()
                    }
                    popupStatus.setNegativeButton("Cancelar") { dialog, _ ->
                        dialog.cancel()
                    }
                    popupStatus.show()
                }
                popupDescricao.setNegativeButton("Cancelar") { dialog, _ ->
                    dialog.cancel()
                }
                popupDescricao.show()
            }
            popupTitulo.setNegativeButton("Cancelar") { dialog, _ ->
                dialog.cancel()
            }

            popupTitulo.show()
        }
    }
}
