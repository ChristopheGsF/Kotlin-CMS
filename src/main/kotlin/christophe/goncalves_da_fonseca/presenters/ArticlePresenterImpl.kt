package christophe.goncalves_da_fonseca.cms.control

import christophe.goncalves_da_fonseca.cms.ArticlePresenter
import christophe.goncalves_da_fonseca.cms.Model


class ArticlePresenterImpl(val model: Model, val view: ArticlePresenter.View): ArticlePresenter {

    override fun start(id: Int) {
        if (model.getArticle(id) != null) {
            view.displayArticle(model.getArticle(id))
        } else {
            view.displayNotFound()
        }

    }
}