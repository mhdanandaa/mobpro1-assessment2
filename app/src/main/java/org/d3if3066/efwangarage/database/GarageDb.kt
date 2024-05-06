package org.d3if3066.efwangarage.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import org.d3if3066.efwangarage.model.Garage
import javax.xml.validation.Schema

@Database(entities = [Garage::class], version = 1, exportSchema = false)
abstract class GarageDb: RoomDatabase() {

    abstract val dao: GarageDao

    companion object{

        @Volatile
        private var INSTANCE: GarageDb? = null

        fun getInstance(context: Context): GarageDb {
            synchronized(this) {
                var instance = INSTANCE

                if(instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        GarageDb::class.java,
                        "garage.db"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}