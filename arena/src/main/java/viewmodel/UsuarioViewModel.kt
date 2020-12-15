package viewmodel

import org.uqbar.commons.model.annotations.Observable

@Observable
class UsuarioViewModel() {

    var nombre: String = ""
    var apellido: String = ""
    var dni: String = ""
    var email: String = ""
    var password: String = ""
    var esAdmin: Boolean = false
    var account: AccountViewModel? =  null
    var estado: String = "Habilitado"

    constructor(nombre: String, apellido: String, dni: String, email: String,
                password: String, esAdmin: Boolean, estado: String) : this() {
        this.nombre = nombre
        this.apellido = apellido
        this.dni = dni
        this.email = email
        this.password = password
        this.esAdmin = esAdmin
    }

    fun inicializarAccount(userAccountViewModel: AccountViewModel) {
        this.account = userAccountViewModel;
    }

    fun traducirEstado(estado : Boolean) : String{
        if (estado){
            return "Bloqueado";
        } else {
            return "Habilitado";
        }
    }
}