package com.github.mpontoc.agenda.pessoal.angendapesssoal.repository

import com.github.mpontoc.agenda.pessoal.angendapesssoal.entity.Task
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TaskRepository : JpaRepository<Task, Long> {
}