package io.github.fuadreza.pikul_dagger.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import io.github.fuadreza.pikul_dagger.data.local.dao.UserProgressDao
import io.github.fuadreza.pikul_dagger.data.local.entity.UserProgress

@Database(entities = [UserProgress::class], version = 1, exportSchema = false)
abstract class LocalDatabase : RoomDatabase() {

    abstract fun userProgressDao(): UserProgressDao

    companion object {
        @Volatile
        private var INSTANCE: LocalDatabase? = null

        const val DB_NAME = "db_pikul"
        const val TB_USER_PROGRESS = "tb_user_progress"

        fun getDatabase(
            context: Context
        ): LocalDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LocalDatabase::class.java,
                    DB_NAME
                )
                    .build()

                INSTANCE = instance
                return instance
            }
        }
    }

    private class UserProgressCallback(

    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)

        }
    }
}