package christophe.goncalves_da_fonseca.cms.control

interface UserPresenter {
    fun login(email: String, password: String)

    interface View {
        fun success()
        fun error()
    }
}