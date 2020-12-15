package viewmodel

import org.uqbar.commons.model.annotations.Observable
import wallet.DiscountGiftStrategy
import wallet.FixedGiftStrategy
import wallet.LoyaltyGiftStrategy
import java.time.LocalDate

@Observable
class BeneficiosViewModel() {
    var nombre: String = ""
    var tipoDeDescuento: LoyaltyGiftStrategy = DiscountGiftStrategy(20.0)
    var numeroDeOperaciones: Int = 0
    var montoDeCadaOperacion: Int = 0
    var fechaDesde: LocalDate = LocalDate.now()
    var fechaHasta: LocalDate = LocalDate.now().plusDays(10)

    constructor(nombre: String, estrategia: String, transacciones: Int, monto: Int,
                fechaDesde: LocalDate, fechaHasta: LocalDate) : this() {
        this.nombre = nombre
        this.tipoDeDescuento =  if (estrategia == "Descuento")   {  DiscountGiftStrategy(20.0) } else  { FixedGiftStrategy(20.0) }
        this.numeroDeOperaciones = transacciones
        this.montoDeCadaOperacion = monto
        this.fechaDesde= fechaDesde
        this.fechaHasta = fechaHasta
    }


}