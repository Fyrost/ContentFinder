package com.example.contentfinder.Models

object SearchModel {
    data class ResultList(val results : List<Result>)
    data class Result(
        val trackName : String,
        val artworkUrl60 : String
    )
}