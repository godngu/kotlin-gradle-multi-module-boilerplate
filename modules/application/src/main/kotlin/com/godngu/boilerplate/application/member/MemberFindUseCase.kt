package com.godngu.boilerplate.application.member

import com.godngu.boilerplate.application.common.page.PageModel
import com.godngu.boilerplate.application.common.page.PageRequestModel

interface MemberFindUseCase {

    fun findMembers(query: PageRequestModel): PageModel<MemberDto>
}
