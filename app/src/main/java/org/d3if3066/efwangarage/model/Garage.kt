package org.d3if3066.efwangarage.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "garage")
data class Garage(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val merkMobil: String,
    val jenisMobil: String,
    val warnaMobil: String,
    val tahunKeluaran: String,
    val status: String,
)
