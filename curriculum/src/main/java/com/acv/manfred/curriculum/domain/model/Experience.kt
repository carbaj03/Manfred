package com.acv.manfred.curriculum.domain.model

data class Experience(

    /**
     * Company in which you worked
     * (Required)
     *
     */
    var company: Set<Company>
)