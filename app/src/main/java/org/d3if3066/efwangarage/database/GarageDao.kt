package org.d3if3066.efwangarage.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import org.d3if3066.efwangarage.model.Garage

@Dao
interface GarageDao {

    @Insert
    suspend fun insert(garage: Garage)

    @Update
    suspend fun update(garage: Garage)

    @Query("SELECT * FROM garage ORDER BY tahunKeluaran ASC")
    fun getCar(): Flow<List<Garage>>

    @Query("SELECT * FROM garage WHERE id = :id")
    suspend fun getCarById(id: Long): Garage?

    @Query("DELETE FROM garage WHERE id = :id")
    suspend fun deleteById(id: Long)


}