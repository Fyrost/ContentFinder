package com.example.contentfinder.Entity

import androidx.room.Dao
import androidx.room.Query

@Dao
interface ResultDao {
    @Query("SELECT * FROM results")
    fun getAll(): List<ResultEntity>

    @Query("SELECT * FROM results WHERE kind = :kind")
    fun findByKind(kind: String): List<ResultEntity>

    @Query("SELECT * FROM results WHERE kind = :kind AND trackName LIKE :trackName")
    fun findByKindAndTerm(trackName: String, kind: String): List<ResultEntity>

    @Query("SELECT CAST(CASE WHEN COUNT(*) > 0 THEN 1 ELSE 0 END AS BIT) FROM results WHERE trackId = :trackId")
    fun resultExists(trackId: Int): Int

    @Query("INSERT INTO results(trackId, trackName, artworkUrl100, trackPrice, primaryGenreName, longDescription, artistName, kind, releaseDate) VALUES(:trackId, :trackName, :artworkUrl100, :trackPrice, :primaryGenreName, :longDescription, :artistName, :kind, :releaseDate)")
    fun insertResult(trackId: Int, trackName: String, artworkUrl100: String, trackPrice: String, primaryGenreName: String, longDescription: String, artistName: String, kind: String?, releaseDate: String)

    @Query ("DELETE FROM results WHERE trackId = :trackId")
    fun deleteResult(trackId: Int)

    @Query ("DELETE FROM results")
    fun deleteAll()
}