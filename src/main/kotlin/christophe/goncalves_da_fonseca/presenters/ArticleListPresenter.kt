package christophe.goncalves_da_fonseca.cms

import christophe.goncalves_da_fonseca.cms.model.Article

interface ArticleListPresenter {

    fun start()

    interface View {
        fun displayArticleList(list: List<Article>)
    }

}