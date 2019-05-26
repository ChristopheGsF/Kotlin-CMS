package christophe.goncalves_da_fonseca.cms.control

import java.util.*

interface CommentPresenter {
    fun createComment(articleId: Int, username : String, text : String)

    interface View {
        fun success()
        fun error()
    }
}