package io.github.fuadreza.pikul_dagger.model

data class UserProfile (
    val uid: String? = "",
    val firstName: String? = "",
    val lastName: String? = "",
    val email: String? = "",
    val urlPic: String? = ""
)