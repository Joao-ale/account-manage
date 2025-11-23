package auri.account_manage.dto.address

data class AddressResponse(
	val id: Long,
	val street: String,
	val number: String,
	val neighborhood: String,
	val city: String,
	val state: String,
	val country: String
)
