package com.godngu.boilerplate.application.common.page

import org.springframework.data.domain.Page
import java.util.function.Function
import javax.persistence.Entity

class PageModelMapper {

    companion object {

        inline fun <reified T, reified U> map(page: Page<T>, converter: Function<in T, out U>): PageModel<U> {
            val convertedPage: Page<U> = page.map(converter)
            return map(convertedPage)
        }

        inline fun <reified T> map(page: Page<T>): PageModel<T> {
            require(isNotEntity<T>()) {
                "엔티티 객체는 변환할 수 없습니다."
            }

            return PageModel(
                totalPages = page.totalPages,
                totalElements = page.totalElements,
                page = page.pageable.pageNumber,
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
