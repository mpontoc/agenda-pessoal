package com.github.mpontoc.agenda.pessoal.angendapesssoal.repository

import com.github.mpontoc.agenda.pessoal.angendapesssoal.entity.Users
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UsersRepository : JpaRepository<Users, Long> {

    fun findUsersById(id:Long) : Users
}