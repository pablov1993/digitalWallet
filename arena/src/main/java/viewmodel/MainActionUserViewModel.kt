package viewmodel

import org.uqbar.commons.model.annotations.Observable
import wallet.User

@Observable
class MainActionUserViewModel(context: MainViewModel, type: String) {

    var digitalWallet = context
    val selectedUser = context.usuarioSeleccionado;
    var usuariosActivos = context.usuariosActivos;
    var estadosActuales = mutableListOf<String>("Habilitado", "Bloqueado");

    //cuando creo un usuario su estado es habilitado.
    var estado: String = "Habilitado"
    var nombre: String = "";
    var apellido: String = "";
    var dni: String = "";
    var email: String = "";
    var password: String = "";
    var esAdmin: Boolean = false;

    var cvu : String = "";
    var saldo : Double = 0.0


    val setType = checkForType(type)



    fun agregarUsuario() {
        digitalWallet.registrarUsuario(User(dni, nombre, apellido, email, password, esAdmin))
        digitalWallet.agregarUsuarioVM(nombre, apellido, dni, email, password, esAdmin, estado)
    }

    fun modificarUsuario() {
        selectedUser?.nombre;
        selectedUser?.apellido;
        selectedUser?.dni;
        selectedUser?.email = cambiarEmail(selectedUser!!, email);
        selectedUser?.estado = estado;
        usuariosActivos.set(usuariosActivos.indexOf(selectedUser), selectedUser)
    }

    fun cambiarEmail(user: UsuarioViewModel, correo: String): String {
        if (correo != user.email) {
            return correo;
        } else {
            return user.email;
        }
    }

    fun checkForType(type: String){
        if(type === "Edit"){
            this.apellido = selectedUser?.apellido!!
            this.nombre = selectedUser?.nombre!!
            this.saldo = selectedUser?.account?.balance!!
            this.cvu = selectedUser?.account?.cvu!!
            this.dni = selectedUser?.dni
            this.email = selectedUser?.email
            this.password = selectedUser?.password
            this.esAdmin = selectedUser?.esAdmin
        }
    }

    fun cambiarEstado(user: UsuarioViewModel, estado: String): String {
        var estadoPrevio = user.account?.isBlocked;
        if (traducirEstado(estadoPrevio!!) == "Habilitado" && estado == "Bloqueado") {
            return estado;
        } else {
            return estado;
        }
    }

    fun traducirEstado(estado: Boolean): String {
        if (estado) {
            return "Bloqueado";
        } else {
            return "Habilitado";
        }
    }
}