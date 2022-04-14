package com.godngu.boilerplate.application.common.page

import com.godngu.boilerplate.application.common.page.SortModel.Direction
import org.springframework.data.domain.PageRequest

class PageRequestModel private constructor(
    page: Int = 0,
    size: Int = 10,
    val sort: SortModel
) : AbstractPageRequestModel(page, size) {

    companion object {
        fun of(page: Int, size: Int): PageRequestModel {
            return PageRequestModel(page, size, SortModel.unsorted())
        }

        fun of(page: Int, size: Int, sort: SortModel): PageRequestModel {
            return PageRequestModel(page, size, sort)
        }

        fun of(page: Int, size: Int, direction: Direction, property: String): PageRequestModel {
            return PageRequestModel(
                page, size,
                SortModel.by(
                    listOf(SortModel.Order(direction, property))
                )
            )
        }
    }

    fun transform(): PageRequest {
        return PageRequest.of(
            this.page,
            this.size,
            this.sort.transform()
        )
    }
}
