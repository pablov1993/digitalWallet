package api

class UsernameExist() : Exception("Username is used") {}

class UserWrongPasswordExist() : Exception("Usuario o Password Incorrecta") {}

class UserCVUNotFound() : Exception("CVU no encontrado") {}

class AccountDoesntExist() : Exception("La Cuenta No Existe") {};

class InsufficientBalance() : Exception("No tenes plata") {}