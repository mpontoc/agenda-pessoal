package com.github.mpontoc.agenda.pessoal.angendapesssoal.dto

import com.github.mpontoc.agenda.pessoal.angendapesssoal.entity.Segment
import com.github.mpontoc.agenda.pessoal.angendapesssoal.entity.Users

data class UsersDTO(
    val id: Long,
    val name: String,
    val segments: MutableList<Segment>
) {
    companion object {
        fun toDTO(users: Users) = UsersDTO(
            users.id,
            users.name,
            users.segments
        )
    }
}
