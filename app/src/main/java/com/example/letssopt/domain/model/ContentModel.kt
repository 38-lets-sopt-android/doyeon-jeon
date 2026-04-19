package com.example.letssopt.domain.model

import java.util.UUID

data class ContentModel(
    val thumbnailUrl: String,
    val id: String = UUID.randomUUID().toString(),
    ) {
    companion object {
        val newContents = listOf(
            ContentModel("https://i.pinimg.com/736x/f7/be/30/f7be30287de05b59ac5dabee9fcf0cc6.jpg"),
            ContentModel("https://i.pinimg.com/1200x/33/b0/4e/33b04e6c1326f38c6a02ace3e4a26d83.jpg"),
            ContentModel("https://i.pinimg.com/736x/e2/d3/ae/e2d3ae424cc290663d5cab0e46995f94.jpg")
        )

        val whatgorithmContents = listOf(
            ContentModel("https://i.pinimg.com/736x/c4/32/35/c432354f2e214d2d8605740974e79dae.jpg"),
            ContentModel("https://i.pinimg.com/736x/cb/c1/31/cbc131be7637b27d0a46c0b121d99cfa.jpg"),
            ContentModel("https://i.pinimg.com/736x/63/86/42/6386429cb9b0b23d028b2cb646c06afd.jpg"),
            ContentModel("https://i.pinimg.com/736x/ec/90/31/ec90316c0ecfd2e9440b9ddf5e6116f5.jpg")
        )

        val upcomingContents = listOf(
            ContentModel("https://i.pinimg.com/736x/6b/b4/b6/6bb4b6ca07aabd92cf6a4baeb5260b7c.jpg"),
            ContentModel("https://i.pinimg.com/1200x/40/d2/e4/40d2e40f3fc376e47c53a17897e0ba31.jpg"),
            ContentModel("https://i.pinimg.com/736x/88/b3/d2/88b3d281a36afdbee741122f2a0ffc60.jpg"),
            ContentModel("https://i.pinimg.com/1200x/56/5e/e2/565ee2e795b7b88a53464b901ab46c79.jpg"),
        )
    }
}
