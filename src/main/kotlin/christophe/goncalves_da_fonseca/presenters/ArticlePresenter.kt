package christophe.goncalves_da_fonseca.cms

import christophe.goncalves_da_fonseca.cms.model.Article

interface ArticlePresenter {

    fun start(id: Int)

    interface View {
        fun displayArticle(article: Article?)
        fun displayNotFound()
    }
}