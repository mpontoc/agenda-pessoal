package com.github.mpontoc.agenda.pessoal.angendapesssoal.dto

import com.github.mpontoc.agenda.pessoal.angendapesssoal.entity.Segment
import com.github.mpontoc.agenda.pessoal.angendapesssoal.entity.Task
import com.github.mpontoc.agenda.pessoal.angendapesssoal.entity.Users

data class UsersRequest(
    var users: Users,
)

data class SegmentRequest(
    var segment: Segment
)

data class TaskRequest(
    var task: Task
)