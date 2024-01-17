package com.bitcodetech.machine2.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Product (
    val id : String,
    @SerializedName("thumbnail")
    val image : String,
    val title : String,
    val description : String,
    val price : Int,
    val rating : String,
    val category : String,
    val stock : String,
    val brand : String,
):Serializable