package com.example.leonard.circuitsphere.model

import android.os.Parcel
import android.os.Parcelable
import com.example.leonard.circuitsphere.R

data class Product(
    val uid: String = "",
    val name: String = "",
    val partNumber: String = "",
    val description: String = "",
    val price: Double = 0.0,
    val imageId: Int = R.drawable.baseline_image_not_supported_24,
    val category: String = ""
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readDouble(),
        parcel.readInt(),
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(uid)
        parcel.writeString(name)
        parcel.writeString(partNumber)
        parcel.writeString(description)
        parcel.writeDouble(price)
        parcel.writeInt(imageId)
        parcel.writeString(category)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Product> {
        override fun createFromParcel(parcel: Parcel): Product {
            return Product(parcel)
        }

        override fun newArray(size: Int): Array<Product?> {
            return arrayOfNulls(size)
        }
    }
}