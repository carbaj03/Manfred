package com.acv.manfred.curriculum.data.gateway.datasource

import arrow.core.left
import arrow.core.right
import arrow.core.toOption
import com.acv.manfred.curriculum.data.example.Example
import com.acv.manfred.curriculum.domain.GetCvDto
import com.acv.manfred.curriculum.domain.Result
import com.acv.manfred.curriculum.domain.model.ApiError
import com.google.gson.Gson
import com.google.gson.JsonParseException
import java.io.FileReader


class ApiModule()  {

    fun requestGet(
        getBookingDto: GetCvDto,
        error: (Throwable) -> Unit,
        success: (Result<Example>) -> Unit
    ): Unit =
        try {
            val gson = Gson()
            val staff = gson.fromJson(FileReader("D:\\file.json"), Example::class.java)
            success(staff.right())
        } catch (retrofitError: JsonParseException) {
            success(ApiError(retrofitError.message.toOption()).left())
        } catch (t: Throwable) {
            error(t)
        }
}