package view

import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.Window
import org.uqbar.arena.windows.WindowOwner
import viewmodel.EliminarUsuarioViewModel

class EliminarUsuarioView(owner: WindowOwner, model: EliminarUsuarioViewModel) : Window<EliminarUsuarioViewModel>(owner, model) {

    fun eliminarModel() = modelObject;
    override fun createContents(mainPanel: Panel) {
        var myPanel2 = mainPanel;
        myPanel2.asColumns(2)

        title = "Seguro que deseas eliminar al Usuario?"
        Label(myPanel2) with {
            text = "Nombre: "
            align = "left"
        }
        Label(myPanel2) with {
            width = 200
            height = 20
            bindTo("nombre")
        }

        Label(myPanel2) with {
            text = "Apellido: "
            align = "left"
        }
        Label(myPanel2) with {
            width = 200
            height = 20
            bindTo("apellido")
        }



        Panel(mainPanel) with {
            asHorizontal()

            Button(it) with {
                caption = "Eliminar"
                //bindCaptionTo("buttonName")
                onClick {
                    eliminarModel().borrarUsuario()
                    close()
                }
            }
        }
    }
}