package christophe.goncalves_da_fonseca.cms.tpl

import christophe.goncalves_da_fonseca.cms.model.Article

data class ArticleContext(
    val Article: Article?,
    val Auth : Boolean
)