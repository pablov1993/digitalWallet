package viewmodel

import org.uqbar.commons.model.annotations.Observable

@Observable
class VerUsuarioViewModel(context: MainViewModel) {

    val usuarioActual = context.usuarioSeleccionado;

    val nombreActual = usuarioActual?.nombre

    val apellidoActual = usuarioActual?.apellido

    val dniActual = usuarioActual?.dni

    val emailActual = usuarioActual?.email

    val estadoActual = usuarioActual?.estado

    val cvuActual = usuarioActual?.account!!.cvu

    val saldoActual = usuarioActual?.account!!.balance

    val adminActual = usuarioActual?.esAdmin
}