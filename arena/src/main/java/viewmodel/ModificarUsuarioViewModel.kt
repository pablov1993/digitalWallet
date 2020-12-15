package viewmodel

import org.uqbar.commons.model.annotations.Observable
import wallet.User
import java.util.*

@Observable
class ModificarUsuarioViewModel (context : MainViewModel) {

    var digitalWallet =  context.digitalWallet;
    var user = context.usuarioSeleccionado;
    var usuariosActivos = context.usuariosActivos;
    var estadosActuales = mutableListOf<String>("Habilitado", "Bloqueado");

    var nombre : String = user!!.nombre;
    var apellido: String = user!!.apellido;
    var nroDocumento: String = user!!.dni;
    var email: String = user!!.email;
    var estado: String = user!!.estado;
    var cvu: String = user!!.account!!.cvu;
    var saldo: Double = user!!.account!!.balance;


    fun modificarUsuario(){
        user!!.email = cambiarEmail(user!!, email);
        user!!.estado = estado;
        usuariosActivos.set(usuariosActivos.indexOf(user!!), user!!)
    }

    fun cambiarEmail(user : UsuarioViewModel , correo : String) : String{
        if (correo != user.email){
            return correo;
        } else {
            return user.email;
        }
    }

    fun cambiarEstado(user : UsuarioViewModel , estado : String) : String{
        var estadoPrevio = user.account?.isBlocked ;
        if (traducirEstado(estadoPrevio!!) == "Habilitado" && estado == "Bloqueado"){
            //digitalWallet.blockAccount(user.account!!);
            return estado;
        } else {
            //digitalWallet.unblockAccount(user.account);
            return estado;
        }
    }

    fun traducirEstado(estado : Boolean) : String{
        if (estado){
            return "Bloqueado";
        } else {
            return "Habilitado";
        }
    }

}

