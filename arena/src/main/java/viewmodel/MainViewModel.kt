package viewmodel

import org.uqbar.commons.model.annotations.Dependencies
import org.uqbar.commons.model.annotations.Observable
import wallet.*
import java.time.LocalDate


@Observable
class MainViewModel(digitalWallet: DigitalWallet) {
    val digitalWallet = digitalWallet;
    var filtroUsuario: String = ""
    var email: String = ""
    var password: String = ""
    var usuarioSeleccionado: UsuarioViewModel? = null
    var usuariosActivos = mutableListOf<UsuarioViewModel>()
    var cuentas = mutableListOf<AccountViewModel>();
    var beneficios = mutableListOf<BeneficiosViewModel>()


    fun getDigitalWalletUsers() = digitalWallet.users

    fun validateUser() = digitalWallet.login(email, password)

    fun registrarUsuario(user: User) {
        digitalWallet.register(user)
        val cvu: String = DigitalWallet.generateNewCVU();
        val account = Account(user, cvu);
        digitalWallet.assignAccount(user, account)
    }

    private fun mapUsers() = usuariosActivos


    fun getUsuarios(): List<UsuarioViewModel> = mapUsers()

    fun agregarUsuarioVM(firstName: String, lastName: String, idCard: String, email: String, password: String, isAdmin: Boolean, estado: String) {
        val activeAccounts = digitalWallet.accounts;
        for(account in activeAccounts){
            if(account.user.idCard === idCard){
                val user = UsuarioViewModel(firstName, lastName, idCard, email, password, isAdmin, estado);
                usuariosActivos.add(user);
                val userAccount = AccountViewModel(user, account.balance, account.cvu,account.isBlocked);
                user.inicializarAccount(userAccount);
                cuentas.add(user.account!!);
            }
        }
    }

    @Dependencies("filtroUsuario")
    fun getusuariosFiltro(): List<UsuarioViewModel> = if (filtroUsuario == "") mapUsers() else
        mapUsers().filter { user ->
            user.nombre.toLowerCase().contains(filtroUsuario.toLowerCase())
                    || user.apellido == filtroUsuario
        }

    fun agregarBeneficioVM(nombre: String, estrategia: String, minTrans: Int, minAmount: Int,
                           fechaDesde: LocalDate, fechaHasta: LocalDate) {
        var beneficio = BeneficiosViewModel(nombre, estrategia, minTrans, minAmount, fechaDesde, fechaHasta)
        beneficios.add(beneficio)

    }

    fun registrarBeneficio(loyalty: LoyaltyGift) {
        digitalWallet.addLoyalty(loyalty)
    }
}