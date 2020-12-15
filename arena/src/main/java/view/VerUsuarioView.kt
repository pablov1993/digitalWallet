package view

import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.Window
import org.uqbar.arena.windows.WindowOwner
import viewmodel.VerUsuarioViewModel

class VerUsuarioView(owner: WindowOwner, model: VerUsuarioViewModel) : Window<VerUsuarioViewModel>(owner, model) {
    override fun createContents(mainPanel: Panel) {
        var myPanel2 = mainPanel;
        myPanel2.asColumns(2)

        title = "Ver Usuario - Digital Wallet"
        Label(myPanel2) with {
            text = "Nombre: "
            align = "left"
        }
        Label(myPanel2) with {
            width = 200
            height = 20
            align = "left"
            bindTo("nombreActual")
        }

        Label(myPanel2) with {
            text = "Apellido: "
            align = "left"
        }
        Label(myPanel2) with {
            width = 200
            height = 20
            align = "left"
            bindTo("apellidoActual")
        }

        Label(myPanel2) with {
            text = "DNI: "
            align = "left"
        }

        Label(mainPanel) with {
            width = 200
            height = 20
            bindTo("dniActual")
        }

        Label(myPanel2) with {
            text = "Email: "
            align = "left"
        }
        Label(myPanel2) with {
            width = 200
            height = 20
            align = "left"
            bindTo("emailActual")
        }

        Label(myPanel2) with {
            text = "Estado: "
            align = "left"
        }

        Label(myPanel2) with {
           bindTo("estadoActual")
        }

        Label(myPanel2) with {
            text = "CVU: "
            align = "left"
        }
        Label(myPanel2) with {
            width = 200
            height = 20
            bindTo("cvuActual")
        }

        Label(myPanel2) with {
            text = "Saldo: "
            align = "left"
        }
        Label(myPanel2) with {
            width = 200
            height = 20
            bindTo("saldoActual")
        }

        Label(myPanel2) with {
            text = "Es Admin?: "
            align = "left"
        }
        CheckBox(myPanel2) with {
            bindTo("adminActual")
            bindEnabledTo("adminActual")
        }

        Panel(mainPanel) with {
            asHorizontal()

            Button(it) with {
                caption = "Cerrar"
                onClick { close() }
            }
        }
    }
}