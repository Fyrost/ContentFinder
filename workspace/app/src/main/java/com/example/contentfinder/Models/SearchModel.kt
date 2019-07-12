package com.example.contentfinder.Models

import org.jetbrains.annotations.Nullable

object SearchModel {
    data class ResultList(val results : List<Result>)
    data class Result(
        val trackName : String,
        val artworkUrl60 : String,
        val trackPrice : String,
        val primaryGenreName : String
    )
}