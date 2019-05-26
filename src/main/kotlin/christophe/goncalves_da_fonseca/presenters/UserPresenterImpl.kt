package christophe.goncalves_da_fonseca.cms.control

import christophe.goncalves_da_fonseca.cms.Model

class UserPresenterImpl(val model: Model, val view: UserPresenter.View):
    UserPresenter {
    override fun login(email: String, password: String) {
        if (email != "") {
            if (model.login(email, password)) {
                view.success()
            } else {
                view.error()
            }
        } else {
            view.error()
        }
    }

}