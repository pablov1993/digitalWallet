package view

import org.uqbar.arena.bindings.ValueTransformer
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import viewmodel.AdministrarBeneficiosViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class AdministrarBeneficiosView(owner: WindowOwner, model: AdministrarBeneficiosViewModel) : SimpleWindow<AdministrarBeneficiosViewModel>(owner,model) {
    override fun addActions(actionsPanel : Panel) {}

    override fun createFormPanel(mainPanel: Panel) {
        title = "DigitalWallet - Administracion de Beneficios"
        Panel(mainPanel) with{
            asHorizontal()
            Label (it) with {
                text = "Nombre"
                width = 200
                alignLeft()
            }
            TextBox (it) with {
                width = 200
                bindTo("nombre")
            }
        }

        Panel(mainPanel) with {
            asHorizontal()
            Label (it) with {
                text = "Fecha Desde"
                width = 200
                alignLeft()
            }
            TextBox(it) with {
                width = 180
                bindTo("fechaDesde").setTransformer(LocalDateTransformer())
            }
        }

        Panel(mainPanel) with {
            asHorizontal()
            Label (it) with {
                text = "Fecha Hasta"
                width = 200
                alignLeft()
            }
            TextBox(it) with {
                width = 180
                bindTo("fechaHasta").setTransformer(LocalDateTransformer())
            }
        }


        Panel(mainPanel) with{
            asHorizontal()
            Label (it) with {
                text = "Tipo De Descuento"
                width = 200
                alignLeft()
            }
            Selector<String> (it) with {
                width = 200
                bindTo("tipoDeDescuento")
            }
        }

        Panel(mainPanel) with{
            asHorizontal()
            Label (it) with {
                text = "Numero De Operaciones"
                width = 200
                alignLeft()
            }
            NumericField (it) with {
                width = 200
                bindTo("numeroDeOperaciones")
            }
        }

        Panel(mainPanel) with{
            asHorizontal()
            Label (it) with {
                text = "Monto De Cada Operacion"
                width = 200
                alignLeft()
            }
            NumericField (it) with {
                width = 200
                bindTo("montoDeCadaOperacion")
            }
        }

        Button(mainPanel) with {
            caption = "Agregar"
            onClick {
                modelObject.agregarBeneficio()
                close()
            }
        }
    }

    class LocalDateTransformer : ValueTransformer<LocalDate, String> {
        private var pattern = "dd/MM/yyyy"

        override fun getModelType() = LocalDate::class.java
        override fun getViewType() = String::class.java

        override fun modelToView(valueFromModel: LocalDate): String {
            return valueFromModel.format(DateTimeFormatter.ofPattern(pattern))
        }

        override fun viewToModel(valueFromView: String): LocalDate {
            return LocalDate.parse(valueFromView, DateTimeFormatter.ofPattern(pattern))
        }

    }
}