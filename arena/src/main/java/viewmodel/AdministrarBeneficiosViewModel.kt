package viewmodel

import org.uqbar.commons.model.annotations.Observable
import wallet.DiscountGiftStrategy
import wallet.FixedGiftStrategy
import wallet.LoyaltyGift
import wallet.LoyaltyGiftStrategy
import java.time.LocalDate

@Observable
class AdministrarBeneficiosViewModel(context : MainViewModel) {

    var digitalWallet =  context
    var nombre: String = ""
    var tipoDeDescuento: String = ""
    var numeroDeOperaciones: Int = 0
    var montoDeCadaOperacion: Int = 0
    var fechaDesde: LocalDate = LocalDate.now()
    var fechaHasta: LocalDate = LocalDate.now().plusDays(10)


    fun agregarBeneficio() {
        digitalWallet.agregarBeneficioVM(nombre,tipoDeDescuento,numeroDeOperaciones,montoDeCadaOperacion,fechaDesde,fechaHasta)
        val loyalty = LoyaltyGift(nombre,if (tipoDeDescuento == "Descuento")   {  DiscountGiftStrategy(20.0) } else  { FixedGiftStrategy(20.0) }
        , numeroDeOperaciones, montoDeCadaOperacion.toDouble(),fechaDesde,fechaHasta)




        digitalWallet.registrarBeneficio(loyalty)

    }
}

