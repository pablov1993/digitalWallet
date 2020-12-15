package view

import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.WindowOwner
import viewmodel.MainActionUserViewModel


class ModificarUsuarioView(owner: WindowOwner, model: MainActionUserViewModel) : MainUserActionWindow(owner, model) {
    override fun createContents(mainPanel: Panel) {
        super.createContents(mainPanel)
        title = "Modificar Usuario - Digital Wallet";
        val myPanel = createMainPanel();
        Panel(myPanel) with {
            asVertical();
            asColumns(2)

            Label(it) with {
                text = "Estado: ";
                width = 200
                height = 20
                align = "left"
            }
            Selector<String>(it) with {
                width = 200;
                bindItemsToProperty("estadosActuales");
                bindTo("estado");
            }

            // CUENTA CVU
            Label(it) with {
                text = "Cuenta CVU: ";
                width = 200
                height = 20
                align = "left"
            }
            Label(it) with {
                width = 200
                height = 20;
                align = "center"
                bindTo("cvu");
            }

            // SALDO
            Label(it) with {
                text = "Saldo: ";
                width = 200
                height = 20
                align = "left"
            }
            Label(it) with {
                width = 200
                height = 20
                align = "center"
                bindTo("saldo");
            }
        }


        // BOTONES "MODIFICAR" / "CANCELAR"
        Button(myPanel) with {
            caption = "Modificar"
            onClick {
                modelObject.modificarUsuario()
                close()
            }
        }
        Button(myPanel) with {
            caption = "Cancelar"
            onClick {
                close()
            }
        }

    }
}




