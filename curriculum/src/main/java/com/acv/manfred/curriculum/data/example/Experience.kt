package com.acv.manfred.curriculum.data.example

import com.acv.manfred.curriculum.data.example.Company

data class Experience(

    /**
     * Company in which you worked
     * (Required)
     *
     */
    var company: Set<Company>
)