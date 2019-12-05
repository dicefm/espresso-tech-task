package fm.dice.data.tags.model

internal data class TagsResponse(
    val count: Int?,
    val total: Int?,
    val _embedded: List<String>?,
    val _links: Links?
) {

    data class Links(
        val self: Self?
    ) {

        data class Self(
            val href: String?
        )
    }
}