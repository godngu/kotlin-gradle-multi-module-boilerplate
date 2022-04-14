package com.godngu.boilerplate.application.common.page

class PageModel<T>(
    val totalPages: Int,
    val totalElements: Long,
    val page: Int,
    val size: Int,
    val first: Boolean,
    val last: Boolean,
    val next: Boolean,
    val previous: Boolean,
    val content: List<T>,
) {

    companion object {
        fun <T, U> changeContent(pageModel: PageModel<T>, content: List<U>): PageModel<U> {
            return PageModel(
                totalPages = pageModel.totalPages,
                totalElements = pageModel.totalElements,
                page = pageModel.page,
                size = pageModel.size,
                first = pageModel.first,
                last = pageModel.last,
                next = pageModel.next,
                previous = pageModel.previous,
                content = content
            )
        }
    }

//    override fun <U> map(converter: Function<in T, out U>): PageModel<U> {
//        return PageModel(
//            totalPages = this.totalPages,
//            totalElements = this.totalElements,
//            page = this.page,
//            size = this.size,
//            first = this.first,
//            last = this.last,
//            next = this.next,
//            previous = this.previous,
//            content = getConvertedContent(converter),
//        )
//    }
//
//    private fun <U> getConvertedContent(converter: Function<in T, out U>): List<U> {
//        return this.stream().map(converter::apply).collect(Collectors.toList())
//    }
//
//    override fun iterator(): MutableIterator<T> {
//        return content.toMutableList().listIterator()
//    }
}
