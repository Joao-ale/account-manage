package auri.account_manage.dto.address

data class AddressRequest(
	val street: String,
	val number: String,
	val neighborhood: String,
	val city: String,
	val state: String,
	val country: String
)
