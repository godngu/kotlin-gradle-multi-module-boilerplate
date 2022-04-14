package com.godngu.boilerplate.application.common.page

data class PageModel<T>(
    val totalPages: Int,
    val totalElements: Long,
    val page: Int,
    val size: Int,
    val first: Boolean,
    val last: Boolean,
    val next: Boolean,
    val previous: Boolean,
    val content: List<T>,
)
