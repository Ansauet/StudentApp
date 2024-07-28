package com.example.studentapp

import android.os.Parcel
import android.os.Parcelable

data class ApiUser(
    val id: Int = 0,  // Default value for new users
    val name: String,
    val email: String,
    val phone: String,
    val website: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(email)
        parcel.writeString(phone)
        parcel.writeString(website)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ApiUser> {
        override fun createFromParcel(parcel: Parcel): ApiUser {
            return ApiUser(parcel)
        }

        override fun newArray(size: Int): Array<ApiUser?> {
            return arrayOfNulls(size)
        }
    }
}
