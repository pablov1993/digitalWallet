package viewmodel

import org.uqbar.commons.model.annotations.Observable
import wallet.LoyaltyGift

@Observable
class AccountViewModel() {

    var user: UsuarioViewModel? = null;
    var balance: Double = 0.0;
    var cvu: String = ""
    var isBlocked : Boolean = true;
    // var appliedLoyalties : mutableListOf<LoyaltyGift>();
    //var transactions : mutableListOf<Transaction>();

    constructor(user: UsuarioViewModel, balance: Double, cvu: String, isBlocked: Boolean) : this (){// appliedLoyalties: mutableListOf<LoyaltyGift>()
        // , transactions: mutableListOf<Transaction>(),
        this.user = user;
        this.balance = balance;
        this.cvu = cvu;
        this.isBlocked = isBlocked;
        //this.appliedLoyalties = appliedLoyalties;
        //this.transactions = transactions
    }
}