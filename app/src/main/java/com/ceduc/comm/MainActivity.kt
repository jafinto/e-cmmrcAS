package com.ceduc.comm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var db: SQLiteDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = SQLiteDB(this)

        val btnListar: Button = findViewById(R.id.btnListar)
        btnListar.setOnClickListener {
            val productList = db.obtenerTodosLosProductos()
            val texto = productList.joinToString("\n") { it.descripcion }
            val txtProd: TextView = findViewById(R.id.txtProd)
            txtProd.text = texto
        }

        val imgDron: ImageButton = findViewById(R.id.imgDron)
        val imgMacbook: ImageButton = findViewById(R.id.imgMacbook)
        val imgAudifonos: ImageButton = findViewById(R.id.imgAudifonos)
        val imgvisorVR: ImageButton = findViewById(R.id.imgvisorVR)

        val db = SQLiteDB(this)
        db.limpiarTodosLosProductos()

        imgDron.setOnClickListener {
            val producto = Producto(codigo = "DRON01", descripcion = "Dron", precio = 30990.0)
            abrirProductoActivity(producto)
        }

        imgMacbook.setOnClickListener{
            val producto = Producto(codigo = "MACBOOK01", descripcion = "Apple Macbook", precio = 975000.0)
            abrirProductoActivity(producto)
        }

        imgAudifonos.setOnClickListener{
            val producto = Producto(codigo = "AUDIFON01", descripcion = "Audífonos Gamer", precio = 25990.0)
            abrirProductoActivity(producto)
        }

        imgvisorVR.setOnClickListener{
            val producto = Producto(codigo = "VRSET01", descripcion = "Visor VR", precio = 300000.0)
            abrirProductoActivity(producto)
        }

        val btnVerCarrito: Button = findViewById(R.id.btnVerCarrito)
        btnVerCarrito.setOnClickListener {
            abrirCarritoActivity()
        }
    }

    private fun abrirProductoActivity(producto: Producto) {
        val intent = Intent(this, ProductoActivity::class.java)
        intent.putExtra("producto", producto)
        startActivity(intent)
    }
    private fun abrirCarritoActivity() {
        val intent = Intent(this, Carrito::class.java)
        startActivity(intent)
    }
}

