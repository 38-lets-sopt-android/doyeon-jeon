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
            ContentModel("https://i.pinimg.com/736x/c9/48/2f/c9482f3b40060bf6b95a509978656166.jpg"),
            ContentModel("https://i.pinimg.com/1200x/56/5e/e2/565ee2e795b7b88a53464b901ab46c79.jpg"),
            ContentModel("https://i.pinimg.com/1200x/c9/5c/9b/c95c9be15cb506538625cfdd83f6868e.jpg"),
            ContentModel("https://i.pinimg.com/736x/88/b3/d2/88b3d281a36afdbee741122f2a0ffc60.jpg"),
        )

        val favoriteContents = listOf(
            ContentModel("https://i.pinimg.com/1200x/be/a8/22/bea822677ebd5438e819ae9c3b3b9fe3.jpg"),
            ContentModel("https://i.pinimg.com/736x/56/42/f4/5642f417910af4d566a9250f4155fe50.jpg"),
            ContentModel("https://i.pinimg.com/736x/29/ae/64/29ae649a52a766bdc00beff61f34cee9.jpg"),
            ContentModel("https://i.pinimg.com/736x/14/74/8a/14748a7f2b5e788d5f0b3b2341faa4b3.jpg"),
            ContentModel("https://i.pinimg.com/736x/7f/46/3b/7f463bf7ef983693be1da88937d7b7dc.jpg"),
            ContentModel("https://i.pinimg.com/736x/93/c2/bf/93c2bf416aefb4bcb64f5eb2275f4006.jpg"),
            ContentModel("https://i.pinimg.com/736x/cf/5e/64/cf5e64ffa1293cbba6e46e2e08adebf6.jpg")
        )
    }
}
