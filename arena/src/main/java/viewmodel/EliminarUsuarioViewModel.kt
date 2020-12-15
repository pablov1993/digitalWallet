package viewmodel
import org.uqbar.commons.model.annotations.Observable

@Observable
class EliminarUsuarioViewModel(context : MainViewModel){

    var digitalWallet =  context
    val usuarioSeleccionado = digitalWallet.usuarioSeleccionado
    val nombre = usuarioSeleccionado?.nombre
    var apellido = usuarioSeleccionado?.apellido
    var dni: String = ""
    var email: String = ""
    var password: String = ""
    var esAdmin: Boolean = false
    var estado: String = ""


    fun borrarUsuario(){
        //digitalWallet.eliminarUsuario(usuarioSeleccionado)
        digitalWallet.usuariosActivos.remove(usuarioSeleccionado)
    }
}