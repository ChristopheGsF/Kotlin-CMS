package christophe.goncalves_da_fonseca.cms

import freemarker.cache.ClassTemplateLoader
import christophe.goncalves_da_fonseca.cms.model.Article
import christophe.goncalves_da_fonseca.cms.tpl.IndexContext
import christophe.goncalves_da_fonseca.cms.tpl.LoginContext
import christophe.goncalves_da_fonseca.cms.tpl.ArticleContext
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.freemarker.FreeMarker
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.http.HttpStatusCode
import io.ktor.http.content.resources
import io.ktor.http.content.static
import io.ktor.request.receiveParameters
import io.ktor.response.respond
import io.ktor.response.respondRedirect
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.sessions.*
import kotlinx.coroutines.launch
import christophe.goncalves_da_fonseca.cms.control.CommentPresenter
import christophe.goncalves_da_fonseca.cms.control.UserPresenter
import christophe.goncalves_da_fonseca.cms.model.AuthSession

class App

fun main() {

    val appComponents = AppComponents("jdbc:mysql://localhost:3306/cms-iim?serverTimezone=UTC", "root", "")

    embeddedServer(Netty, 8080) {

        install(FreeMarker) {
            templateLoader = ClassTemplateLoader(App::class.java.classLoader, "templates")
        }

        install(Sessions) {
            cookie<AuthSession>("AUTH_COOKIE")
        }

        routing {
            static("static") {
                resources("static")
            }

            get("/") {
                val controller = appComponents.getArticleListPresenter(object: ArticleListPresenter.View {
                    override fun displayArticleList( list: List<Article>) {
                        val authUser = call.sessions.get<AuthSession>()
                        var auth = false
                        if (authUser != null) {
                            auth = true
                        }

                        val context = IndexContext(list, auth)
                        launch {
                            call.respond(FreeMarkerContent("index.ftl", context, "e"))
                        }
                    }
                })
                controller.start()
            }

            get("article/{id}") {
                val controller = appComponents.getArticlePresenter(object: ArticlePresenter.View {
                    override fun displayNotFound() {
                        launch {
                            call.respond(HttpStatusCode.NotFound)
                        }
                    }

                    override fun displayArticle(article: Article?) {
                        launch {
                            val authUser = call.sessions.get<AuthSession>()
                            var auth = false
                            if (authUser != null) {
                                auth = true
                            }
                            val context = ArticleContext(article, auth)
                            call.respond(FreeMarkerContent("article.ftl", context, "e"))
                        }
                    }
                })

                val id = call.parameters["id"]!!.toInt()
                controller.start(id)
            }

            post("/comment/add") {
                val requestBody = call.receiveParameters()
                val id = requestBody["article_id"]!!.toInt()
                val username = requestBody["username"]!!.toString()
                val text = requestBody["text"]!!.toString()

                val controller = appComponents.createComment(object: CommentPresenter.View {
                    override fun error() {
                        launch {
                            call.respondText("Something wrong happened")
                        }
                    }

                    override fun success() {
                        launch {
                            call.respondRedirect("/article/$id")
                        }
                    }
                })

                controller.createComment(id, username, text)
            }

            get("/admin") {
                val authUser = call.sessions.get<AuthSession>()
                var auth = false
                if (authUser != null) {
                    if (authUser.connected){
                        call.respondRedirect("/")
                        auth = true
                    }else{
                        auth = false
                        val context = LoginContext(auth)
                        call.respond(FreeMarkerContent("login.ftl", context, "e"))
                    }
                }
                val context = LoginContext(auth)
                call.respond(FreeMarkerContent("login.ftl", context, "e"))

            }

            get("/logout") {
                        call.sessions.clear<AuthSession>()
                        call.respondRedirect("/")

                }

            post("/admin/auth") {
                val requestBody = call.receiveParameters()
                val email = requestBody["email"]!!.toString()
                val password = requestBody["password"]!!.toString()

                val controller = appComponents.login(object: UserPresenter.View {
                    override fun error() {
                        launch {
                            call.sessions.clear<AuthSession>()
                            print("pas OK")
                        }
                    }

                    override fun success() {
                        launch {
                            call.sessions.clear<AuthSession>()
                            call.sessions.set(AuthSession(true, email))
                            call.respondRedirect("/")
                        }
                    }
                })

                controller.login(email, password)
            }
        }
    }.start(wait = true)
}