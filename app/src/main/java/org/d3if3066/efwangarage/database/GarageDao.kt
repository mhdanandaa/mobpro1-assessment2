package org.d3if3066.efwangarage.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Update
import org.d3if3066.efwangarage.model.Garage

@Dao
interface GarageDao {

    @Insert
    suspend fun insert(garage: Garage)

    @Update
    suspend fun update(garage: Garage)
}