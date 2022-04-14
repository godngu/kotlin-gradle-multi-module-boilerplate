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

    fun transform(): Sort {
        return Sort.by(orders.map { it.transform() })
    }

    enum class Direction {
        ASC, DESC;

        fun isAscending() = this == ASC

        fun isDescending() = this == DESC

        fun transform(): Sort.Direction {
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
                        String.format(
                            "Invalid value '%s' for orders given! Has to be either 'desc' or 'asc' (case insensitive).",
                            value
                        ),
                        e
                    )
                }
            }
        }
    }

    data class Order(
        val direction: Direction,
        val property: String
    ) {
        fun transform(): Sort.Order {
            return Sort.Order(direction.transform(), property)
        }
    }
}
