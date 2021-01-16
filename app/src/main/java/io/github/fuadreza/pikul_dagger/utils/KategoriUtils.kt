package io.github.fuadreza.pikul_dagger.utils

import io.github.fuadreza.pikul_dagger.model.KategoriSkor

fun getKategoriCode(listScore: ArrayList<Int>): String?{
    val kategoriCode = arrayOf("R", "I", "A", "S", "E", "C")

    val userKategoriSkor = arrayListOf<KategoriSkor>()

    var result = ""

    listScore.forEachIndexed{index, score ->
        userKategoriSkor.add(KategoriSkor(score, kategoriCode[index]))
    }

    userKategoriSkor.sortByDescending { it.userScore }

    for(i in 0..2){
        result += userKategoriSkor[i].kategori
    }

    return result
}