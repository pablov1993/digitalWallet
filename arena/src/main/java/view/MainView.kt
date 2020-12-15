package view


import erroresView.UsuarioNoSelecionadoErrorWindow
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.Window
import org.uqbar.arena.windows.WindowOwner
import viewmodel.*


class MainView(owner: WindowOwner, model: MainViewModel) : Window<MainViewModel>(owner, model) {
    fun mainWindow() = modelObject

    override fun createContents(mainPanel: Panel) {
        title = "Digital Wallet - Administracìon"

        /*Panel de Filtro*/
        Panel(mainPanel) with {
            width = 200
            Label(it) with {
                //fontSize = 14
                text = "Listado de Usuarios"
                alignLeft()
            }

            Label(it) with {
                text = "Filtrar: "
                alignLeft()
            }
            TextBox(it) with {
                width = 150
                bindTo("filtroUsuario")
                alignLeft()
            }

        }
        // Panel Tabla de usuarios
        Panel(mainPanel) with {
            setMinWidth(500)
            table<UsuarioViewModel>(it) with {
                bindItemsTo("usuariosFiltro")
                bindSelectionTo("usuarioSeleccionado")
                setNumberVisibleRows(7)
                column {
                    fixedSize = 100
                    title = "Nombre"
                    bindContentsTo("nombre")
                }
                column {
                    fixedSize = 100
                    title = "Apellido"
                    bindContentsTo("apellido")
                }
                column {
                    fixedSize = 150
                    title = "Correo Electronico"
                    bindContentsTo("email")
                }

                column {
                    fixedSize = 100
                    title = "Estado"
                    bindContentsTo("estado")
                }


            }
        }

        /*Botones de la ventana*/


        Panel(mainPanel) with {
            asHorizontal()
            Button(it) with {
                caption = "Ver"
                onClick {
                    if (thisWindow.modelObject.usuarioSeleccionado == null) {
                        UsuarioNoSelecionadoErrorWindow(owner, mainWindow()).open()
                    } else {
                        VerUsuarioView(owner, VerUsuarioViewModel(mainWindow())).open()
                    }
                }
            }
            Button(it) with {
                caption = "Agregar"
                onClick {
                    AgregarUsuarioView(owner, MainActionUserViewModel(mainWindow(),"Create")).open()

                }
            }
            Button(it) with {
                caption = "Modificar"
                onClick {
                    if (thisWindow.modelObject.usuarioSeleccionado == null) {
                        UsuarioNoSelecionadoErrorWindow(owner, mainWindow()).open()
                    } else {
                        ModificarUsuarioView(owner, MainActionUserViewModel(mainWindow(),"Edit")).open();
                    }
                }
            }
            Button(it) with {
                caption = "Borrar"
                onClick {
                    if (thisWindow.modelObject.usuarioSeleccionado == null) {
                        UsuarioNoSelecionadoErrorWindow(owner, mainWindow()).open()
                    } else {
                        EliminarUsuarioView(owner, EliminarUsuarioViewModel(mainWindow())).open()
                    }
                }
            }

        }

        // Panel Tabla de Beneficios
        Panel(mainPanel) with {
            setMinWidth(500)
            table<AdministrarBeneficiosViewModel>(it) with {
                bindItemsTo("beneficios")
                setNumberVisibleRows(7)
                column {
                    fixedSize = 450
                    title = "Nombre del Beneficio"
                    bindContentsTo("nombre")
                }
            }
        }

        Panel(mainPanel) with {
            asHorizontal()

            Button(it) with {
                caption = "Administrar Beneficios"
                onClick {
                    AdministrarBeneficiosView(owner, AdministrarBeneficiosViewModel(mainWindow())).open()
                }
            }

        }

    }
}
