package com.acv.manfred.curriculum.domain.model

import com.acv.manfred.curriculum.presentation.form.component.common.ComponentType
import com.acv.manfred.curriculum.presentation.form.component.common.New
import com.acv.manfred.curriculum.presentation.form.component.common.NotModified
import com.acv.manfred.curriculum.presentation.form.component.common.Persisted
import java.util.*

data class WithId(val withId: String) : GenerateId() {
    override val id: String = withId
}

object Id : GenerateId() {
    override val id: String get() = generate()
}

object NoId : GenerateId() {
    override val id: String = NO_ID
}

sealed class GenerateId {
    abstract val id: String

    companion object {
        const val NO_ID = "-1"

        operator fun invoke(id: String): GenerateId =
            when (id) {
                "-1" -> NoId
                else -> WithId(id)
            }
    }

    fun isEmpty(): Boolean = id == NO_ID

    fun generate(): String =
        UUID.randomUUID().toString()

    fun type(): ComponentType =
        when (this) {
            is WithId, Id -> Persisted(NotModified)
            NoId -> New(NotModified)
        }
}

