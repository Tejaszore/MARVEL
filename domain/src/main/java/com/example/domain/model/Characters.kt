package com.example.domain.model

import android.media.Image

data class Characters (val id: Long,
                       val name: String,
                       val description: String,
                       val thumbnail: Image
//                       val comics: CharacterComicWrapper,
//                       val series: CharacterComicWrapper,
//                       val stories: CharacterComicWrapper,
//                       val events: CharacterComicWrapper,
//                       val urls: List<Url>
                       )

//data class Characters (val id: Long,
//                               val name: String,
//                               val description: String,
//                               val imageUrl: String)