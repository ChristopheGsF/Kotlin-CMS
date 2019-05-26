package christophe.goncalves_da_fonseca.cms

import christophe.goncalves_da_fonseca.cms.model.Article
import christophe.goncalves_da_fonseca.cms.model.Comment

interface Model {

    fun getArticleList(): List<Article>

    fun getArticle(id: Int): Article?

    fun createComment(articleId: Int, username: String, text: String): Boolean

    fun getCommentArticleById(articleId: Int): List<Comment>

    fun login(email: String, password: String): Boolean

}