package com.github.mpontoc.agenda.pessoal.angendapesssoal.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import javax.persistence.*

@Entity
@Table(name = "users")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class Users(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val name: String,
    val password: String,

    @OneToMany(targetEntity = Segment::class, cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = "u_fk", referencedColumnName = "id")
    var segments: MutableList<Segment> = mutableListOf()
)
