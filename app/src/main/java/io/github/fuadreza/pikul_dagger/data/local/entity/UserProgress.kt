package io.github.fuadreza.pikul_dagger.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import io.github.fuadreza.pikul_dagger.data.local.LocalDatabase
import java.io.Serializable

@Entity(
    tableName = LocalDatabase.TB_USER_PROGRESS,
    indices = [Index(value = [UserProgress.USERNAME], unique = true)]
    )
data class UserProgress(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID)
    val id: Int,

    @ColumnInfo(name = USERNAME)
    val username: String? = "",

    @ColumnInfo(name = PROGRESS)
    val progress: Int? = 1,

    @ColumnInfo(name = REKOMENDASI_1)
    val rekomendasi_1: String? = "",

    @ColumnInfo(name = REKOMENDASI_2)
    val rekomendasi_2: String? = "",

    @ColumnInfo(name = REKOMENDASI_3)
    val rekomendasi_3: String? = ""

) : Serializable {
    companion object {
        const val ID = "id"
        const val USERNAME = "username"
        const val PROGRESS = "progress"
        const val REKOMENDASI_1 = "rekomendasi_1"
        const val REKOMENDASI_2 = "rekomendasi_2"
        const val REKOMENDASI_3 = "rekomendasi_3"
    }
}