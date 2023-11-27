package com.ceduc.comm

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class SQLiteDB(context: Context) : SQLiteOpenHelper(context, "productos.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE productos (id INTEGER PRIMARY KEY AUTOINCREMENT, codigo TEXT, descripcion TEXT, precio REAL)"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    fun agregarProducto(producto: Producto) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put("codigo", producto.codigo)
        values.put("descripcion", producto.descripcion)
        values.put("precio", producto.precio)
        db.insert("productos", null, values)
        db.close()
    }

    fun limpiarTodosLosProductos() {
        val db = this.writableDatabase
        db.execSQL("DELETE FROM productos")
        db.close()
    }

    fun obtenerTodosLosProductos(): List<Producto> {
        val productList = mutableListOf<Producto>()
        val query = "SELECT * FROM productos"
        val db = this.readableDatabase
        val cursor = db.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndex("id"))
                val codigo = cursor.getString(cursor.getColumnIndex("codigo"))
                val descripcion = cursor.getString(cursor.getColumnIndex("descripcion"))
                val precio = cursor.getDouble(cursor.getColumnIndex("precio"))

                val producto = Producto(id, codigo, descripcion, precio)
                productList.add(producto)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()

        return productList
    }
}
