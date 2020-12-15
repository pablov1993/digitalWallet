package view

import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.Window
import org.uqbar.arena.windows.WindowOwner
import viewmodel.AgregarUsuarioViewModel
import viewmodel.MainActionUserViewModel


open class  MainUserActionWindow(owner: WindowOwner, model: MainActionUserViewModel) : Window<MainActionUserViewModel>(owner, model) {
    override fun createContents(mainPanel: Panel) {

        var myPanel = mainPanel;
        myPanel.asColumns(2)
        title = "Digital Wallet"

        Label(myPanel) with {
            text = "Nombre: "
            align = "left"
        }
        TextBox(myPanel) with {
            width = 200
            height = 20
            bindTo("nombre")
            withFilter { it.inputText.isNotEmpty() }
        }

        Label(myPanel) with {
            text = "Apellido: "
            align = "left"
        }
        TextBox(myPanel) with {
            width = 200
            height = 20
            bindTo("apellido")
            withFilter { it.inputText.isNotEmpty() }
        }

        Label(myPanel) with {
            text = "DNI: "
            align = "left"
        }
        NumericField(mainPanel) with {
            width = 200
            height = 20
            withDecimals = false
            bindTo("dni")
            withFilter { it.endPosition == 9 }
        }

        Label(myPanel) with {
            text = "Email: "
            align = "left"
        }
        TextBox(myPanel) with {
            width = 200
            height = 20
            bindTo("email")
            withFilter { it.currentText.contentEquals("xxxxx@xxxx.com") }
        }

        Label(myPanel) with {
            text = "Password: "
            align = "left"
        }
        PasswordField(myPanel) with {
            width = 200
            height = 20
            bindTo("password")
            var max = 8
            withFilter { it.inputText.toIntOrNull() === 8 }
        }

        Label(myPanel) with {
            text = "Es Admin?: "
            align = "left"
        }
        CheckBox(myPanel) with {
            bindTo("esAdmin")
        }

    }
}

