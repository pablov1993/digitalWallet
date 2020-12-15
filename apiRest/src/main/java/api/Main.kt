package api

import data.DigitalWalletData
import io.javalin.Javalin
import io.javalin.core.util.RouteOverviewPlugin
import wallet.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter


fun main(args: Array<String>) {
    val app = Javalin.create {
        it.defaultContentType = "application/json"
        it.registerPlugin(RouteOverviewPlugin("/routes"))
        it.enableCorsForAllOrigins()
    }
            .start(7000)


    val digitalWallet = DigitalWalletData.build();

    setUp(digitalWallet);

    app.get("/") { ctx -> ctx.result("Hello World") }

    app.get("/users") {
        it.json(digitalWallet.users.map { UserView(it) })
    }

    app.get("/accounts") {
        it.json(digitalWallet.accounts.map { AccountView(it) })
    }

    app.post("login") {
        val userLoged = it.bodyAsClass(UserLogin::class.java)
        try {
            digitalWallet.login(userLoged.email, userLoged.password)
            it.json("Logueado")
        } catch (exception: Exception) {
            it.status(400)
            it.json(Handler(400, exception.message!!))
        }
    }

    app.post("register") {
        val newUser = it.bodyAsClass(UserRegister::class.java)
        try {
            val user = User(newUser.idCard, newUser.firstName, newUser.lastName, newUser.email, newUser.password,
                    false)
            val cvu: String = DigitalWallet.generateNewCVU();
            val cuenta = Account(user, cvu);
            digitalWallet.register(user)
            digitalWallet.assignAccount(user, cuenta)
            it.json("Usuario Registrado")
            it.status(201)
        } catch (exception: Exception) {
            it.status(400)
            it.json(Handler(400, exception.message!!))
        }
    }

    app.post("transfer") {
        val newTransferBody = it.bodyAsClass(DataTransfer::class.java);
        try {
            val newTransfer = digitalWallet.transfer(newTransferBody.fromCVU, newTransferBody.toCVU, newTransferBody.amount);
            it.status(200);
            it.json("transferencia realizada con exito");
        } catch (exception: Exception) {
            it.status(400);
            it.json(Handler(400, exception.message!!))
        }
    }

    app.get("transaccions/:cvu") {
        val cvu = it.pathParam("cvu")
        try {
            it.json(digitalWallet.accountByCVU(cvu).transactions.map { UserTrasactions(it) })
        } catch (exception: Exception) {
            it.status(400)
            it.json(Handler(400, exception.message!!))
        }
    }

    app.get("cvu/:mail") {
        val mail = it.pathParam("mail")
        try {
            for (usuario in digitalWallet.users) {
                if (usuario.email == mail) {
                    it.json(usuario.account?.cvu!!)
                }
            }
        } catch (exception: Exception) {
            it.status(400)
            it.json(Handler(400, exception.message!!))
        }
    }

    app.delete("users/:cvu") {
        val cvu = it.pathParam("cvu")
        try {
            val userToDelete = digitalWallet.accountByCVU(cvu).user
            digitalWallet.deleteUser(userToDelete)
            it.json("Usuario Borrado")
        } catch (exception: Exception) {
            it.status(400)
            it.json(Handler(400, exception.message!!))
        }
    }

    app.post("cashIn") {
        val newCashInBody = it.bodyAsClass(CashIn::class.java);
        try {
            var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
            val date = LocalDate.parse(newCashInBody.endDate.toString(), formatter)

            val card = CreditCard(newCashInBody.cardNumber, newCashInBody.fullName,
                    date, newCashInBody.securityCode)
            digitalWallet.transferMoneyFromCard(newCashInBody.fromCVU, card, newCashInBody.amount)
            it.status(200);
            it.json("Cash In realizada con exito");
        } catch (exception: Exception) {
            it.status(400);
            it.json(Handler(400, exception.message!!))
        }
    }

    app.get("account/:cvu") {
        val cvu = it.pathParam("cvu")
        try {
            it.json(digitalWallet.accountByCVU(cvu).balance)
        } catch (exception: Exception) {
            it.status(400)
            it.json(Handler(400, exception.message!!))
        }
    }

    app.get("user/:email") {
        val email = it.pathParam("email")
        try {
            for (user in digitalWallet.users) {
                if (user.email == email) {
                    var user = user
                    var userProfile = UserProfile(
                            user.firstName,
                            user.lastName,
                            user.email,
                            user.account?.balance!!,
                            user.account?.cvu!!
                    )
                    it.json(userProfile)
                }
            }
        } catch (exception: Exception) {
            it.status(400)
            it.json(Handler(400, exception.message!!))
        }
    }

    app.get("/alladmin") {
        it.json(digitalWallet.getAllAdmins().map { UserView(it) })
    }
}


fun setUp(digitalWallet: DigitalWallet) {
    val admin = User("01234", "Admin", "Admin", "admin@admin", "1234", true);
    val adminAcount = Account(admin, "0123-4567");
    val pperez = User("12345", "Piter", "Perez", "pperez@hynterfases.org", "1111", false);
    val perezAcount = Account(pperez, "0000-1111");
    digitalWallet.register(admin);
    digitalWallet.assignAccount(admin, adminAcount);
    digitalWallet.register(pperez);
    digitalWallet.assignAccount(pperez, perezAcount);
}