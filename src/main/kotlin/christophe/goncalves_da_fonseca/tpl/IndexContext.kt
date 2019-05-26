package christophe.goncalves_da_fonseca.cms.tpl

import christophe.goncalves_da_fonseca.cms.model.Article

data class IndexContext(
    val List: List<Article>,
    val Auth : Boolean
)