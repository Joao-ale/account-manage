package auri.account_manage.model

data class Account(
	val id: Long,
	val name: String,
	val ownership: String,
	val phone: String,
	val dependentId: Long?,
	val addressId: Long?
)
