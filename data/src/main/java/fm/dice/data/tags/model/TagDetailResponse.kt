package fm.dice.data.tags.model

internal data class TagDetailResponse(
    val _embedded: Embedded?,
    val _links: Links?,
    val count: Int?,
    val total: Int?
) {
    data class Embedded(
        val tags: List<Tag>?
    ) {
        data class Tag(
            val _embedded: Embedded?,
            val _links: Links?,
            val appeared_at: String?,
            val created_at: String?,
            val quote_id: String?,
            val tags: List<String>?,
            val updated_at: String?,
            val value: String?
        ) {
            data class Embedded(
                val author: List<Author>?,
                val source: List<Source>?
            ) {
                data class Author(
                    val author_id: String?,
                    val bio: String?,
                    val created_at: String?,
                    val name: String?,
                    val slug: String?,
                    val updated_at: String?
                )

                data class Source(
                    val created_at: String?,
                    val filename: String?,
                    val quote_source_id: String?,
                    val remarks: String?,
                    val updated_at: String?,
                    val url: String?
                )
            }

            data class Links(
                val self: Self?
            ) {
                data class Self(
                    val href: String?
                )
            }
        }
    }

    data class Links(
        val first: First?,
        val last: Last?,
        val next: Next?,
        val prev: Prev?,
        val self: Self?
    ) {
        data class Prev(
            val href: String?
        )

        data class First(
            val href: String?
        )

        data class Last(
            val href: String?
        )

        data class Self(
            val href: String?
        )

        data class Next(
            val href: String?
        )
    }
}