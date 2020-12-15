package view

import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.WindowOwner
import viewmodel.MainActionUserViewModel


class AgregarUsuarioView(owner: WindowOwner, model: MainActionUserViewModel) : MainUserActionWindow(owner, model) {
    override fun createContents(mainPanel: Panel) {
        super.createContents(mainPanel)
        title = "Agregar Usuario - Digital Wallet"
        Button(mainPanel) with {
            caption = "Agregar"
            onClick {
                modelObject.agregarUsuario()
                close()
            }

            Button(mainPanel) with {
                caption = "Cancelar"
                onClick {
                    close()
                }
            }
        }
    }
}
