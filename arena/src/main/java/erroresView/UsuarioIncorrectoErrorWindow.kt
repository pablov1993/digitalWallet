package erroresView

import org.uqbar.arena.kotlin.extensions.asHorizontal
import org.uqbar.arena.kotlin.extensions.caption
import org.uqbar.arena.kotlin.extensions.with
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.windows.ErrorsPanel
import org.uqbar.arena.windows.Window
import org.uqbar.arena.windows.WindowOwner
import viewmodel.MainViewModel


class UsuarioIncorrectoErrorWindow(owner: WindowOwner, model: MainViewModel) : Window<MainViewModel>(owner, model) {
    override fun createContents(mainPanel: Panel?) {
        title = "Error"
        ErrorsPanel(mainPanel,"Usuario o Contraseña Incorrecto") with {

            Panel(mainPanel) with {
                asHorizontal()
                Button(mainPanel) with {
                    caption = "Aceptar"
                    onClick {
                        close()

                    }
                }
            }
        }
    }
}