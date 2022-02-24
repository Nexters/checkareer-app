package com.nexters.checkareer.domain.vo

enum class SkillLayer(val value: Int){
    PARENT(3),
    CHILD(4);

    companion object {
        fun fromValue(value: Int): SkillLayer {
            return values().find { it.value == value } ?: CHILD
        }
    }
}