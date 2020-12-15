package api

import wallet.*
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


open class Handler(val code: Int, open val message: String)

data class UserRegister(val idCard: String, val firstName: String, val lastName: String, val email: String,
                        val password: String)

data class UserCVU(val cvu: String = "")


data class UserView(val idCard: String, val firstName: String, val lastName: String, val email: String) {
    constructor(user: User) : this(user.idCard, user.firstName, user.lastName, user.email)
}

data class AccountView(val cvu: String, val balance: Double, val benefits: MutableList<LoyaltyGift>, val isBlocked: Boolean) {
    constructor(account: Account) : this(account.cvu, account.balance, account.appliedLoyalties, account.isBlocked)
}

data class DataTransfer(val fromCVU: String, val toCVU: String, val amount: Double) {
}

data class UserLogin(val email: String, val password: String) {}

data class UserTrasactions(val amount: Double, val dateTime: LocalDateTime, val description: String, val fullDescription: String,
                           val isCashOut: Boolean) {
    constructor(tran: Transactional) : this(tran.amount, tran.dateTime, tran.description(), tran.fullDescription(), tran.isCashOut())
}


data class UserBalance(val balance: Double) {
}

data class UserProfile(val firstName: String, val lastName: String, val email: String, val balance: Double, val cvu: String) {
    constructor(user: User) : this(user.firstName, user.lastName, user.email, user.account?.balance!!, user.account?.cvu!!)
}

data class CashIn(val fromCVU: String, val amount: Double, val cardNumber: String, val fullName: String,
                  val endDate: String, val securityCode: String) {
}

