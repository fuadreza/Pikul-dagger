package io.github.fuadreza.pikul_dagger.views.tes.model

import java.io.Serializable

data class Tes(
    val id: Int?,
    val name: String?,
    val type: String?,
    val questions: ArrayList<String>?
): Serializable{
    companion object {
        const val ID = "id"
        const val NAME = "name"
        const val TYPE = "type"
        const val QUESTIONS = "questions"
    }
}