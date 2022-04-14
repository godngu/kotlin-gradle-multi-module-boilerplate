package com.godngu.boilerplate.application.common.page

import org.springframework.data.domain.Page
import javax.persistence.Entity

class PageModelMapper {

    companion object {

        inline fun <reified T> map(page: Page<T>): PageModel<T> {
            require(isNotEntity<T>()) {
                "엔티티 객체는 변환할 수 없습니다."
            }

            return PageModel(
                totalPages = page.totalPages,
                totalElements = page.totalElements,
                size = page.size,
                first = page.isFirst,
                last = page.isLast,
                next = page.hasNext(),
                previous = page.hasPrevious(),
                content = page.content,
            )
        }

        inline fun <reified T> isNotEntity(): Boolean {
            return T::class.annotations.firstOrNull() {
                it.annotationClass == Entity::class
            } == null
        }
    }
}
