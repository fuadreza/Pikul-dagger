package io.github.fuadreza.pikul_dagger.utils

import io.github.fuadreza.pikul_dagger.model.KategoriSkor

fun getKategoriCode(listScore: ArrayList<Int>): String? {
    val kategoriCode = arrayOf("R", "I", "A", "S", "E", "C")

    val userKategoriSkor = arrayListOf<KategoriSkor>()

    var result = ""

    listScore.forEachIndexed { index, score ->
        userKategoriSkor.add(KategoriSkor(score, kategoriCode[index]))
    }

    userKategoriSkor.sortByDescending { it.userScore }

    for (i in 0..2) {
        result += userKategoriSkor[i].kategori
    }

    return result
}

fun getImagesByCategory(kategori: String): String? {
    val images = arrayOf(
        "k1.png",
        "k2.png",
        "k3.png",
        "k4.png",
        "k5.png",
        "k6.png",
        "k7.png",
        "k8.png",
        "k9.png",
        "k10.png"
    )

    // TODO UPDATE KATEGORI
    val kategoriA = arrayListOf("RIA", "RAI", "IAR", "IRA", "AIR", "ARI")
    val kategoriB = arrayListOf("RIS", "RSI", "SRI", "SIR", "IRS", "ISR",
        "RCS", "RSC", "CRS", "CSR", "SRC", "SCR",
        "ISE", "IES", "SIE", "SEI", "EIS", "ESI")
    val kategoriC = arrayListOf("RIE", "REI", "IRE", "IER", "EIR", "ERI",
        "REC", "RCE", "ERC", "ECR", "CRE", "CER")
    val kategoriD = arrayListOf("RIC", "RCI", "ICR", "IRC", "CRI", "CIR")
    val kategoriE = arrayListOf("IAS", "ISA", "ASI", "AIS", "SIA", "SAI",
        "RSE", "RES", "SER", "SRE", "ERS", "ESR",
        "ISC", "ICS", "SIC", "SCI", "CIS", "CSI")
    val kategoriF = arrayListOf("IAE", "IEA", "AEI", "AIE", "EIA", "EAI")
    val kategoriG = arrayListOf("IAC", "ICA", "ACI", "AIC", "CIA", "CAI")
    val kategoriH = arrayListOf("ASE", "AES", "SEA", "SAE", "EAS", "ESA",
        "RAC", "RCA", "ACR", "ARC", "CRA", "CAR")
    val kategoriI = arrayListOf("ASC", "ACS", "SCA", "SAC", "CAS", "CSA",
        "AEC", "ACE", "EAC", "ECA", "CAE", "CEA")
    val kategoriJ = arrayListOf("SEC", "SCE", "ECS", "ESC", "CSE", "CES",
        "RAE", "REA", "AER", "ARE", "EAR", "ERA",
        "IEC", "ICE", "EIC", "ECI", "CIE", "CEI")

    var result = ""

    result = when {
        containWords(kategori, kategoriA) -> images[0]
        containWords(kategori, kategoriB) -> images[1]
        containWords(kategori, kategoriC) -> images[2]
        containWords(kategori, kategoriD) -> images[3]
        containWords(kategori, kategoriE) -> images[4]
        containWords(kategori, kategoriF) -> images[5]
        containWords(kategori, kategoriG) -> images[6]
        containWords(kategori, kategoriH) -> images[7]
        containWords(kategori, kategoriI) -> images[8]
        containWords(kategori, kategoriJ) -> images[9]
        else -> images[9]
    }

    return result
}

fun containWords(inputString: String, items: ArrayList<String>): Boolean {

    items.forEach {
        if (inputString == it) {
            return true
        }
    }

    return false
}