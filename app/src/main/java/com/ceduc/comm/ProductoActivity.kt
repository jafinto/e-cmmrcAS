package com.ceduc.comm

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ProductoActivity : AppCompatActivity() {

    private lateinit var producto: Producto

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_producto)

        producto = intent.getSerializableExtra("producto") as Producto

        val txtCodigo = findViewById<TextView>(R.id.txtCodigo)
        val txtDescripcion = findViewById<TextView>(R.id.txtDescripcion)
        val txtPrecio = findViewById<TextView>(R.id.txtPrecio)

        txtCodigo.text = "Código: ${producto.codigo}"
        txtDescripcion.text = "Descripción: ${producto.descripcion}"
        txtPrecio.text = "Precio: $${producto.precio}"

        val btnAgregarCarrito: Button = findViewById(R.id.btnAgregarCarrito)
        btnAgregarCarrito.setOnClickListener {
            agregarProductoCarrito(producto)
            Toast.makeText(this, "Producto agregado al carrito", Toast.LENGTH_SHORT).show()

            finish()
        }
    }

    private fun agregarProductoCarrito(producto: Producto) {
        val db = SQLiteDB(this)
        db.agregarProducto(producto)
    }
}
