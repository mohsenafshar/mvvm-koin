package ir.mohsenafshar.testfullkotlin.data.model

data class Feed(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)