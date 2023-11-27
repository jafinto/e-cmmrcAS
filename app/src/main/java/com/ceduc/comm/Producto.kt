package com.ceduc.comm
import java.io.Serializable

data class Producto(
    var id: Int = -1,
    var codigo: String,
    var descripcion: String,
    var precio: Double
) : Serializable
