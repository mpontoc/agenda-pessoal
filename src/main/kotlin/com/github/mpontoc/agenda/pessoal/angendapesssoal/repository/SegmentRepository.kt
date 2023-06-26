package com.github.mpontoc.agenda.pessoal.angendapesssoal.repository

import com.github.mpontoc.agenda.pessoal.angendapesssoal.entity.Segment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SegmentRepository : JpaRepository<Segment, Long> {
    fun findSegmentById(id:Long) : Segment
}