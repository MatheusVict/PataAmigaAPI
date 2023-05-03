package com.example.kotlindemo.model

import javax.persistence.*

@Entity
class PostPets (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column
    val name: String = "",

    @Column
    val race: String = "",

    @Column
    val sex: String = "",

    @Column
    val age: String = "",

    @Column
    val size: String = "",

    @Column
    val weight: String = "",

    @Column
    val about: String = "",

    @Column(name = "is_adpoted")
    val isAdpoted: Boolean,

    @Column(name = "is_castrated")
    val isCastrated: Boolean,

    @Column(name = "is_vaccinated")
    val isVaccinated: Boolean,

    @Column(name = "is_pedigree")
    val isPedigree: Boolean,

    @Column(name = "is_dewormed")
    val isDewormed: Boolean,

    @Column(name = "is_especial_needs")
    val isEspecialNeeds: Boolean,

    @ManyToOne(fetch = FetchType.LAZY)
    val user: User
)