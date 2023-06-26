package com.github.mpontoc.agenda.pessoal.angendapesssoal.controller

import com.github.mpontoc.agenda.pessoal.angendapesssoal.dto.SegmentRequest
import com.github.mpontoc.agenda.pessoal.angendapesssoal.dto.UsersRequest
import com.github.mpontoc.agenda.pessoal.angendapesssoal.dto.UsersDTO
import com.github.mpontoc.agenda.pessoal.angendapesssoal.entity.Segment
import com.github.mpontoc.agenda.pessoal.angendapesssoal.entity.Task
import com.github.mpontoc.agenda.pessoal.angendapesssoal.entity.Users
import com.github.mpontoc.agenda.pessoal.angendapesssoal.repository.SegmentRepository
import com.github.mpontoc.agenda.pessoal.angendapesssoal.repository.UsersRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/users")
class AgendaPessoalController {

    @Autowired
    lateinit var usersRepository: UsersRepository

    @Autowired
    lateinit var segmentRepository: SegmentRepository

    @PostMapping
    fun addUser(
        @RequestBody usersRequest: UsersRequest,
        uriComponentsBuilder: UriComponentsBuilder,
        request: HttpServletRequest
    ): ResponseEntity<*> {
        initSegments(usersRequest.users)
        val response = usersRepository.save(usersRequest.users)
        val uri = uriComponentsBuilder.path("users/${usersRequest.users.id}/").build().toUri()
        return ResponseEntity.created(uri).body(UsersDTO.toDTO(response))
    }

    @GetMapping("/{id}")
    fun getUsersById(
        @PathVariable id: Long
    ): ResponseEntity<*> {
        return ResponseEntity.ok(UsersDTO.toDTO(usersRepository.findUsersById(id)))
    }

    @PostMapping("/{id}/segments")
    fun newSegment(
        @PathVariable id: Long,
        @RequestBody segmentRequest: SegmentRequest
    ): ResponseEntity<*> {
        val users = usersRepository.findUsersById(id)
        users.segments.add(segmentRequest.segment)
        users.segments.last().tasks.add(Task())
        return ResponseEntity.ok(UsersDTO.toDTO(usersRepository.save(users)))
    }

    @PutMapping("/{id}/segments")
    fun editSegment(
        @PathVariable id: Long,
        @RequestBody segmentRequest: SegmentRequest
    ): ResponseEntity<*> {
        val segmentToEdit = segmentRepository.findSegmentById(segmentRequest.segment.id)
        segmentToEdit.name = segmentRequest.segment.name
        segmentRepository.save(segmentToEdit)
        val users = usersRepository.findUsersById(id)
        return ResponseEntity.ok(UsersDTO.toDTO(usersRepository.save(users)))
    }

    @DeleteMapping("/{id}/segments")
    fun deleteSegment(
        @PathVariable id: Long,
    ): ResponseEntity<Void> {
        segmentRepository.deleteById(id)
        return ResponseEntity.noContent().build()
    }


    private fun initSegments(users: Users): Users {
        val segments = mutableListOf(
            Segment(name = "Academia"),
            Segment(name = "Alimentação"),
            Segment(name = "Educação"),
            Segment(name = "Esporte"),
            Segment(name = "Familiar"),
            Segment(name = "Saúde")
        )
        users.segments.addAll(segments)
        users.segments.forEach {
            it.tasks.add(Task())
        }
        return users
    }

}