package com.godngu.boilerplate.application.common.page

import org.springframework.data.domain.Sort

class SortModel private constructor(var orders: List<Order>) {

    companion object {
        private val UNSORTED: SortModel = SortModel.by(emptyList())

        fun by(orders: List<Order>): SortModel {
            return SortModel(orders)
        }

        fun by(direction: Direction, property: String): SortModel {
            return SortModel(
                listOf(Order(direction, property))
            )
        }

        fun unsorted(): SortModel {
            return UNSORTED
        }
    }

    fun convert(): Sort {
        return Sort.by(orders.map { it.convert() })
    }

    enum class Direction {
        ASC, DESC;

        fun isAscending() = this == ASC

        fun isDescending() = this == DESC

        fun convert(): Sort.Direction {
            return when (this) {
                ASC -> Sort.Direction.ASC
                DESC -> Sort.Direction.DESC
            }
        }

        companion object {
            fun fromString(value: String): Direction {
                return try {
                    valueOf(value.uppercase())
                } catch (e: Exception) {
                    throw IllegalArgumentException(
                        "Invalid value $value for orders given! Has to be either 'desc' or 'asc' (case insensitive).", e
                    )
                }
            }
        }
    }

    data class Order(
        val direction: Direction,
        val property: String
    ) {
        fun convert(): Sort.Order {
            return Sort.Order(direction.convert(), property)
        }
    }
}
