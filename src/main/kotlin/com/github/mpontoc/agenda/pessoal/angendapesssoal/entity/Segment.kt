package com.github.mpontoc.agenda.pessoal.angendapesssoal.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import javax.persistence.*

@Entity
@Table(name = "segment")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class Segment(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    var name: String,

    @OneToMany(targetEntity = Task::class, cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = "s_fk", referencedColumnName = "id")
    var tasks: MutableList<Task> = mutableListOf()
)
