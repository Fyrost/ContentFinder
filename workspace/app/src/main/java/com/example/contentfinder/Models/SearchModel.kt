package com.example.contentfinder.Models

object SearchModel {
    data class ResultList(val results : List<Result>)
    data class Result(
        val trackName : String,
        val artworkUrl60 : String,
        val kind : String,
        val trackPrice : String,
        val primaryGenreName : String
    )

    enum class RowType {
        VALUES,
        HEADER
    }

    data class ResultRow(var type: RowType, var res: Result?, var header: String?)
}