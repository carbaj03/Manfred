package com.acv.manfred.curriculum.ui.operations

import arrow.Kind
import arrow.core.Either
import arrow.core.flatMap
import com.acv.manfred.curriculum.data.gateway.request.RequestLanguageOperations
import com.acv.manfred.curriculum.data.gateway.request.RequestProficiencyOperations
import com.acv.manfred.curriculum.domain.gategay.ResultK
import com.acv.manfred.curriculum.domain.dto.*
import com.acv.manfred.curriculum.domain.model.BaseError
import com.acv.manfred.curriculum.domain.model.Language
import com.acv.manfred.curriculum.presentation.form.component.language.LanguageModel
import com.acv.manfred.curriculum.presentation.form.component.language.proficiency.ProficiencyModel
import com.acv.manfred.curriculum.presentation.form.component.language.toView
import com.acv.manfred.curriculum.presentation.operation.LanguageUseCase
import com.acv.manfred.curriculum.presentation.operation.ProficiencyUseCase
import com.acv.manfred.curriculum.presentation.operation.Return
import com.acv.manfred.curriculum.ui.mapper.LanguageViewMapper
import com.acv.manfred.curriculum.ui.mapper.ProficiencyViewMapper
import kotlin.coroutines.CoroutineContext

interface LanguageView<F, N> : RequestLanguageOperations<F, N>, LanguageViewMapper<F>, LanguageUseCase<F>
interface ProficiencyView<F, N> : RequestProficiencyOperations<F, N>, ProficiencyViewMapper<F>, ProficiencyUseCase<F>

interface LanguageViewOperations<F, N> : LanguageView<F, N>, ProficiencyView<F, N> {
    val main: CoroutineContext

    override fun LanguageDto.saveView(): Return<F, List<LanguageModel>> {
        val a: Kind<F, ResultK<List<ProficiencyModel>>> = GetProficiencyDto.all().toViewP()
        val b: Kind<F, ResultK<List<Language>>> = save()
        val r: Kind<F, ResultK<List<LanguageModel>>> = a.map2(b) { t ->
            t.a.flatMap { proficienciesModel ->
                t.b.map { languages ->
                    languages.map { language ->
                        language.toView(proficienciesModel)
                    }
                }
            }
        }
        return r.continueOn(main)
    }

    override fun GetLanguageDto.allView(): Kind<F, Either<BaseError, List<LanguageModel>>> {
        val a: Kind<F, ResultK<List<ProficiencyModel>>> = GetProficiencyDto.all().toViewP()
        val b: Kind<F, ResultK<List<Language>>> = all()
        val r: Kind<F, ResultK<List<LanguageModel>>> = a.map2(b) { t ->
            t.a.flatMap { proficienciesModel ->
                t.b.map { languages ->
                    languages.map { language ->
                        language.toView(proficienciesModel)
                    }
                }
            }
        }
        return r.continueOn(main)
    }
    override fun AddLanguageDto.addView(): Return<F, List<LanguageModel>> {
        val a: Kind<F, ResultK<List<ProficiencyModel>>> = GetProficiencyDto.all().toViewP()
        val b: Kind<F, ResultK<List<Language>>> = add()
        val r: Kind<F, ResultK<List<LanguageModel>>> = a.map2(b) { t ->
            t.a.flatMap { proficienciesModel ->
                t.b.map { languages ->
                    languages.map { language ->
                        language.toView(proficienciesModel)
                    }
                }
            }
        }
        return r.continueOn(main)
    }

    override fun RemoveLanguageDto.removeView(): Return<F, List<LanguageModel>> {
        val a: Kind<F, ResultK<List<ProficiencyModel>>> = GetProficiencyDto.all().toViewP()
        val b: Kind<F, ResultK<List<Language>>> = remove()
        val r: Kind<F, ResultK<List<LanguageModel>>> = a.map2(b) { t ->
            t.a.flatMap { proficienciesModel ->
                t.b.map { languages ->
                    languages.map { language ->
                        language.toView(proficienciesModel)
                    }
                }
            }
        }
        return r.continueOn(main)
    }

    override fun GetProficiencyDto.allView(): Return<F, List<ProficiencyModel>> =
        all().toViewP().continueOn(main)
}

//interface ProficiencyViewOperations<F, N> : ProficiencyView<F, N> {
//    val main: CoroutineContext
//
//    override fun GetProficiencyDto.allView(): Return<F, List<ProficiencyModel>> =
//        all().toView().continueOn(main)
//}