package com.acv.manfred.curriculum.data.schema

import arrow.core.NonEmptyList


fun createObject(
    description: Description = Description("An open CV format"),
    properties: Properties = Properties(mapOf()),
    required: Required = NonEmptyList.just("")
): Structure = Structure(description, ObjectKeyword, properties, required)

fun createString(
    description: Description = Description("An open CV format"),
    max: Lenght = Lenght(100),
    min: Lenght = Lenght(1),
    pattern: Pattern = Pattern(""),
    format: Format = JsonPointer
): Structure =
    Structure(description = description, type = TextKeyword, max = max, min = min, pattern = pattern, format = format)

fun createArray(
    description: Description = Description("An open CV format"),
    properties: Properties = Properties(mapOf()),
    required: Required = NonEmptyList.just(""),
    items: List<Structure> = listOf(),
    uniqueItems: Boolean = true,
    minItems: Lenght = Lenght(1),
    maxItems: Lenght = Lenght(100)
): Structure = Structure(description, ArrayKeyword, properties, required, items, uniqueItems, minItems, maxItems)


data class Structure(
    var description: Description = Description(""),
    val type: TypeKeyword = NullKeyword,
    val properties: Properties = Properties(),
    var required: Required = Required.just(""),
    val items: List<Structure>? = null,
    val uniqueItems: Boolean? = null,
    val minItems: Lenght? = null,
    val maxItems: Lenght? = null,
    val max: Lenght? = null,
    val min: Lenght? = null,
    val pattern: Pattern? = null,
    val format: Format? = null
)

data class CvSchema(
    val schema: Schema = Schema("http://json-schema.org/draft-07/schema#"),
    val id: Id = Id("http://github.com/manfred/mac/schema.json"),
    val title: Title = Title("Manfred Awesomic CV"),
    val description: Description = Description("An open CV format"),
    val type: TypeKeyword = ObjectKeyword,
    val properties: Properties = Properties(mapOf()),
    val required: Required = NonEmptyList.just("")
)

fun properties(block: Properties.() -> Unit): Properties =
    Properties().apply(block)

fun Structure.properties(block: Properties.() -> Unit): Properties =
    Properties().apply(block)

fun `object`(block: Structure.() -> Unit): Structure =
    createObject().apply(block)

fun string(block: Structure.() -> Unit): Structure =
    createString().apply(block)

fun Structure.description(block: String) {
    description = Description(block)
}

fun Structure.required(vararg block: String) {
    required = NonEmptyList.fromListUnsafe(block.toList())
}

fun String.desc(): Description = Description(this)

fun Description.valu(v: String) {
    value = v
}


fun createProperties(): Properties =
    properties {
        "author" to `object` {
            description("Main data of the CV author")
            required("profile", "intro")
            properties {
                "profile" to `object` {
                    description("Personal data of the CV author")
                    required("name", "birthday", "roles", "yearsOfExperience")
                    properties {
                        string { description("Complete name of the CV author") }
                    }
                }
            }
        }
    }

//Properties(
//mapOf(
//"author" to createObject(),
//"experience" to createArray(items = listOf(createObject(properties = Properties(mapOf("company" to createArray())))))
//)
//)

sealed class Annotations(var value: String)
data class Title(val title: String) : Annotations("title")
data class Description(val description: String = "description") : Annotations("description")
object Default : Annotations("default")
object Examples : Annotations("examples")


data class Schema(val version: String)
data class Id(val uri: String)

sealed class TypeKeyword(val keyword: kotlin.String)
object TextKeyword : TypeKeyword("string")
object ObjectKeyword : TypeKeyword("object")
object IntegerKeyword : TypeKeyword("integer")
object NumberKeyword : TypeKeyword("number")
object BooleanKeyword : TypeKeyword("boolean")
object ArrayKeyword : TypeKeyword("array")
object NullKeyword : TypeKeyword("null")

sealed class Type(val type: TypeKeyword) {
    data class Text(
        val value: String,
        val max: Lenght,
        val min: Lenght,
        val pattern: Pattern,
        val format: Format
    ) : Type(TextKeyword)

    data class Integer(
        val value: Int
    ) : Type(IntegerKeyword)

    data class Number(
        val value: Float,
        val multipleOf: MultipleOf,
        val range: Range
    ) : Type(NumberKeyword)

    data class Boolean(
        val value: Boolean
    ) : Type(BooleanKeyword)

    data class Object(
        val value: Map<String, *>,
        val properties: Properties,
        val additionalProperties: Boolean,
//        val additionalProperties : Type,
        val required: Required,
        val propertyNames: PropertyNames,
        val minProperties: Lenght,
        val maxProperties: Lenght,
        val patternProperties: PatternProperties
    ) : Type(ObjectKeyword)

    data class Array(
        val value: List<Any>,
        val items: List<Item>,
        val uniqueItems: Boolean,
        val minItems: Lenght,
        val maxItems: Lenght,
        val additionalItems: Type,
        val contains: Contains
    ) : Type(ArrayKeyword)

    object Null : Type(NullKeyword)
}


data class Properties(
    val value: Map<String, Structure> = mapOf()
)

data class Pattern(val pattern: String)
data class Lenght(val int: PositiveNumber)

typealias PositiveNumber = Int

typealias Required = NonEmptyList<String>


sealed class Format(val value: String)
object DateTime : Format("date-time") //2018-11-13T20:20:39+00:00
object Time : Format("time") //20:20:39+00:00
object Date : Format("date") //2018-11-13
object Email : Format("email")
object IdnEmail : Format("idn-email")
object Hostname : Format("hostname")
object IdnHostname : Format("idn-hostname")
object Ipv4 : Format("ipv4")
object Ipv6 : Format("ipv6")
object Uri : Format("uri")
object UriReference : Format("uri-refence")
object Iri : Format("iri")
object IriReference : Format("iri-refence")
object UriTemplate : Format("uri-template")
object JsonPointer : Format("json-pointer")
object RelativeJsonPointer : Format("relative-json-pointer")
object Regex : Format("regex")

data class MultipleOf(val multiple: PositiveNumber)

sealed class Range(val value: String)
data class Minimun(val minimun: Int) : Range("minimun")
data class Maximun(val maximun: Int) : Range("minimun")
data class ExclusiveMaximun(val exclusive: Int) : Range("exclusiveMaximum")
data class ExclusiveMinimun(val exclusive: Int) : Range("exclusiveMinimun")
data class InclusiveMinimun(val inclusive: Int) : Range("exclusiveMinimun")
data class InclusiveMaximun(val inclusive: Int) : Range("exclusiveMinimun")

data class Item(
    val type: String,
    val enum: List<String>
)

data class Contains(val type: String)

data class PropertyNames(
    val pattern: String // "^[A-Za-z_][A-Za-z0-9_]*$"
)

data class PatternProperties(
    val patters: Map<String, Type>
)

