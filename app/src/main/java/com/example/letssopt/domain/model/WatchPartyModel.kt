package com.example.letssopt.domain.model

import java.util.UUID

data class WatchPartyModel (
    val thumbnailUrl: String,
    val startInfo: String,
    val hashtag: String,
    val id: String = UUID.randomUUID().toString(),
) {
    companion object {
        val parties = listOf(
            WatchPartyModel(
                thumbnailUrl = "https://i.pinimg.com/1200x/de/47/95/de4795793887882862ea4f2d0f176650.jpg",
                startInfo = "오늘 21:13에 시작",
                hashtag = "# 왕과사는 남자",
            ),
            WatchPartyModel(
                thumbnailUrl = "https://i.pinimg.com/736x/7c/63/3e/7c633e43a13cfb491c3a8424e7a8c427.jpg",
                startInfo = "오늘 22:22에 시작",
                hashtag = "# 파묘",
            )
        )
    }
}
