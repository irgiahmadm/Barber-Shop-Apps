package org.d3if4055.barbershop.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [BarberShop::class], version = 1, exportSchema = false)
abstract class BarberShopDatabase : RoomDatabase() {

    abstract val barberShopDAO: BarberShopDAO

    companion object {
        @Volatile
        private var INSTANCE: BarberShopDatabase? = null

        fun getInstance(context: Context) : BarberShopDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                            context.applicationContext,
                            BarberShopDatabase::class.java,
                            "barbershop_database"
                        )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}