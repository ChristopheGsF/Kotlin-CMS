package christophe.goncalves_da_fonseca.cms.control

import christophe.goncalves_da_fonseca.cms.ArticleListPresenter
import christophe.goncalves_da_fonseca.cms.Model

class ArticleListPresenterImpl(val model: Model, val view: ArticleListPresenter.View): ArticleListPresenter {

    override fun start() {
        view.displayArticleList(model.getArticleList())
    }
}