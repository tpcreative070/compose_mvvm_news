package co.tpcreative.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import co.tpcreative.data.database.entity.SourceEntity

@Dao
interface SourceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSources(sources: List<SourceEntity>)

    @Query("SELECT * FROM sources")
    suspend fun getSources(): List<SourceEntity>

    @Query("DELETE FROM sources")
    suspend fun deleteAllSources()
}
