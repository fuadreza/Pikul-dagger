package io.github.fuadreza.pikul_dagger.model

import com.google.gson.annotations.SerializedName

data class UserProfile (
    @SerializedName("uid")
    val uid: String? = "",
    @SerializedName("firstName")
    val firstName: String? = "",
    @SerializedName("lastName")
    val lastName: String? = "",
    @SerializedName("email")
    val email: String? = "",
    @SerializedName("urlPic")
    val urlPic: String? = ""
)