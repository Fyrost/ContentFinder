package com.example.contentfinder.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "results")
data class ResultEntity (
    @PrimaryKey(autoGenerate = false)
    var trackId: Int,

    @ColumnInfo(name = "trackName")
    var trackName : String,

    @ColumnInfo(name = "artworkUrl100")
    var artworkUrl100 : String,

    @ColumnInfo(name = "trackPrice")
    var trackPrice : String,

    @ColumnInfo(name = "primaryGenreName")
    var primaryGenreName : String,

    @ColumnInfo(name = "longDescription")
    var longDescription : String,

    @ColumnInfo(name = "artistName")
    var artistName : String,

    @ColumnInfo(name = "kind")
    var kind : String,

    @ColumnInfo(name = "releaseDate")
    var releaseDate: String
)