package io.github.fuadreza.pikul_dagger.utils

import junit.framework.Assert.assertEquals
import org.junit.Test

class KategoriUtilsKtTest {

    @Test
    fun testKategoriUtils(){
        val score = arrayListOf(40, 35, 25, 35, 35, 20)

        assertEquals(
            getKategoriCode(score), "RIS"
        )
    }
}