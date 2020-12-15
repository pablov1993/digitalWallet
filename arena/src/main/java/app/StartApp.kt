package app

import org.uqbar.arena.Application
import org.uqbar.arena.windows.Window
import view.LoginView
import viewmodel.MainViewModel
import wallet.DigitalWallet
import wallet.User


fun main() {
    var digitalWallet = DigitalWallet();
    // Application start
    DigitalWalletApplication(digitalWallet).start();

}

class DigitalWalletApplication(digitalWallet: DigitalWallet) : Application() {
    var app = MainViewModel(digitalWallet);
    var setUp = setUp(app);
    override fun createMainWindow(): Window<*> {
        return LoginView(this, app)
    }
}

fun setUp(app: MainViewModel) {
    val usuarioAdmin = User("0000", "Admin", "1234", "admin@admin.com", "admin", true);
    val usuarioTester = User("0001", "Peter", "Perez", "pperez@hynterfases.org", "1234", false);
    app.registrarUsuario(usuarioAdmin);
    app.registrarUsuario(usuarioTester);
    app.agregarUsuarioVM("Admin", "Admin", "0000", "admin@admin", "1234", true, "Habilitado");
    app.agregarUsuarioVM("Peter", "Perez", "0001", "pperez@hynterfases.org", "1234", false, "Habilitado");

}