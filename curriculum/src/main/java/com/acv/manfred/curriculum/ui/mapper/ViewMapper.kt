package com.acv.manfred.curriculum.ui.mapper

import arrow.typeclasses.MonadError

interface ViewMapper<F> : MonadError<F, Throwable>