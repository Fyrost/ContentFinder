package com.example.contentfinder.Models

import org.jetbrains.annotations.Nullable
import java.util.*

object SearchModel {
    data class ResultList(val results : List<Result>)
    data class Result(
        val trackId : Int,
        val trackName : String,
        val artworkUrl100 : String,
        val trackPrice : String,
        val primaryGenreName : String,
        val longDescription : String,
        val artistName : String,
        var kind : String,
        val releaseDate: String
    )
}