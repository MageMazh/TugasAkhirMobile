package com.D121211069.catpedia.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Cat(
    val adaptability: Int,
    @SerialName("affection_level")
    val affectionLevel: Int,
    @SerialName("alt_names")
    val altNames: String? = null,
    val bidability: Int? = null,
    @SerialName("cat_friendly")
    val catFriendly: Int? = null,
    @SerialName("cfa_url")
    val cfaUrl: String? = null,
    @SerialName("child_friendly")
    val childFriendly: Int,
    @SerialName("country_code")
    val countryCode: String,
    @SerialName("country_codes")
    val countryCodes: String,
    val description: String,
    @SerialName("dog_friendly")
    val dogFriendly: Int,
    @SerialName("energy_level")
    val energyLevel: Int,
    val experimental: Int,
    val grooming: Int,
    val hairless: Int,
    @SerialName("health_issues")
    val healthIssues: Int,
    val hypoallergenic: Int,
    val id: String,
    val indoor: Int,
    val intelligence: Int,
    val lap: Int? = null,
    @SerialName("life_span")
    val lifeSpan: String,
    val name: String,
    val natural: Int,
    val origin: String,
    val rare: Int,
    @SerialName("reference_image_id")
    val referenceImageId: String? = null,
    val rex: Int,
    @SerialName("shedding_level")
    val sheddingLevel: Int,
    @SerialName("short_legs")
    val shortLegs: Int,
    @SerialName("social_needs")
    val socialNeeds: Int,
    @SerialName("stranger_friendly")
    val strangerFriendly: Int,
    @SerialName("suppressed_tail")
    val suppressedTail: Int,
    val temperament: String,
    @SerialName("vcahospitals_url")
    val vcahospitalsUrl: String? = null,
    @SerialName("vetstreet_url")
    val vetstreetUrl: String? = null,
    val vocalisation: Int,
    val weight: Weight,
    @SerialName("wikipedia_url")
    val wikipediaUrl: String? = null
)
