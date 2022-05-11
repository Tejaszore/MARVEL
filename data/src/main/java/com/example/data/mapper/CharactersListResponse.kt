package com.example.data.mapper

import com.example.domain.model.Characters
import com.initishbhatt.marvelsuperheros.api.model.AllCharactersModel

class CharactersListResponse(val data: List<AllCharactersModel>, val count: Int, val error: String?)