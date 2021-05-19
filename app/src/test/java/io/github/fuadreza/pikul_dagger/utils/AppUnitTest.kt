package io.github.fuadreza.pikul_dagger.utils

import junit.framework.Assert.assertEquals
import org.junit.Test

class AppUnitTest {

    @Test
    fun testGetKategoriByScore(){
        val score = arrayListOf(40, 35, 25, 35, 35, 20)

        assertEquals(
            getKategoriCode(score), "RIS"
        )
    }

    @Test
    fun testKategoriKelompok(){
        val words: ArrayList<String> = arrayListOf("hello", "well", "ops", "kim", "pool", "like")

        val text = "kim"

        assertEquals(
            containWords(text, words), true
        )
    }

    @Test
    fun testGetImageByCategory(){
        val kategori = "CAR"

        assertEquals(
            getImagesByCategory(kategori), "k8.png"
        )
    }
}