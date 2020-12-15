package viewmodel


import org.eclipse.xtend.lib.annotations.Accessors
import org.uqbar.commons.model.annotations.Observable
import org.uqbar.commons.model.exceptions.UserException
import wallet.User

@Observable
class AgregarUsuarioViewModel(context: MainViewModel) {

    var digitalWallet = context
    val estado: String = "Habilitado"
    var nombre: String = "";
    var apellido: String = "";
    var dni: String = "";
    var email: String = "";
    var password: String = "";
    var esAdmin: Boolean = false;


    fun agregarUsuario() {
        digitalWallet.registrarUsuario(User(dni, nombre, apellido, email, password, esAdmin))
        digitalWallet.agregarUsuarioVM(nombre, apellido, dni, email, password, esAdmin, estado)
    }

}