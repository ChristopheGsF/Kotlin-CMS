package christophe.goncalves_da_fonseca.cms.control

import christophe.goncalves_da_fonseca.cms.Model

class CommentPresenterImpl(val model: Model, val view: CommentPresenter.View):
    CommentPresenter {
    override fun createComment(articleId: Int, username: String, text: String) {
        if (text != "") {
            if (model.createComment(articleId, username, text) && text != "") {
                view.success()
            } else {
                view.error()
            }
        } else {
            view.error()
        }
    }
}