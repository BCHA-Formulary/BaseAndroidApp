package com.bcha.formulary.model

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase

/**
 * Created by Kelvin Chan on 2019-08-10.
 */
const val DATABASE_NAME = "drugsManager"
const val TABLE_DRUG_ENTRY = "drugEntryTable"

@Entity(tableName = TABLE_DRUG_ENTRY)
data class SqlDrug(
        @PrimaryKey(autoGenerate = true) val uid: Int?,
        @ColumnInfo (name = "drugName") val primaryName: String,
        @ColumnInfo(name = "nameType") val nameType: String,
        @ColumnInfo (name = "status") val status: String,
        @ColumnInfo (name = "drugClass") val drugClass: String
) {
    constructor(primaryName: String, nameType: String, status: String, drugClass: String):
            this(null, primaryName, nameType, status, drugClass)
}

@Dao
interface SqlDrugEntryDao {
    @Query("SELECT * FROM $TABLE_DRUG_ENTRY")
    fun getAllDrugNames():List<SqlDrug>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDrugs(drugs: List<SqlDrug>)

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    fun insertDrug(drug: SqlDrug)
}



@Database(entities = arrayOf(SqlDrug::class), version = 1)
abstract class DrugDatabase: RoomDatabase() {
    abstract fun drugDao(): SqlDrugEntryDao
}