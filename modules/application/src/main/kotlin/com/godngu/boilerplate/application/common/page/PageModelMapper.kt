package com.godngu.boilerplate.application.common.page

import org.springframework.data.domain.Page
import java.util.function.Function
import javax.persistence.Entity

class PageModelMapper {

    companion object {

        /**
         * 변환: PageModel<T> -> PageModel<R>
         */
        inline fun <reified T, reified R> map(pageModel: PageModel<T>, transform: (T) -> R): PageModel<R> {
            validateNoEntity<T>()
            validateNoEntity<R>()
            return PageModel.changeContent(
                pageModel,
                pageModel.content.map(transform)
            )
        }

        /**
         * 변환: Page<T> -> PageModel<U>
         */
        inline fun <reified T, reified U> map(page: Page<T>, converter: Function<in T, out U>): PageModel<U> {
            return map(page.map(converter))
        }

        /**
         * 변환: Page<T> -> PageModel<T>
         */
        inline fun <reified T> map(page: Page<T>): PageModel<T> {
            validateNoEntity<T>()

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

        inline fun <reified T> validateNoEntity() {
            require(isNotEntity<T>()) {
                "엔티티 객체는 변환할 수 없습니다."
            }
        }

        inline fun <reified T> isNotEntity(): Boolean {
            return T::class.annotations.firstOrNull() {
                it.annotationClass == Entity::class
            } == null
        }
    }
}
