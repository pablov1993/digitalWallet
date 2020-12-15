package view

import erroresView.UsuarioIncorrectoErrorWindow
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.Window
import org.uqbar.arena.windows.WindowOwner
import viewmodel.MainViewModel


class LoginView(owner: WindowOwner, model: MainViewModel) : Window<MainViewModel>(owner, model) {

    fun myWindow() = this

    override fun createContents(mainPanel: Panel) {
        title = "Digital Wallet - Sign In "
        Panel(mainPanel) with {
            asHorizontal()
            Label(it) with {
                text = "Correo Electronico"
                alignLeft()
            }
            TextBox(it) with {
                bindTo("email")
            }
            Label(it) with {
                text = "Contraseña"
                alignLeft()
            }
            PasswordField(it) with {
                bindTo("password")
            }
        }
        Button(mainPanel) with {
            caption = "Ingresar"

            onClick {

                if (modelObject.password == "1234" && modelObject.email == "admin@admin") //
                {
                    MainView(myWindow(), modelObject).open()
                    myWindow().close()
                } else {
                    UsuarioIncorrectoErrorWindow(myWindow(), modelObject).open()
                }
            }
        }
    }
}