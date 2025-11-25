package auri.account_manage.model

data class Address(
	val id: Long,
	val street: String,
	val number: String,
	val neighborhood: String,
	val city: String,
	val state: String,
	val country: String
) 