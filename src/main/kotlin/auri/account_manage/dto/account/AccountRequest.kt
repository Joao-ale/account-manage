package auri.account_manage.dto.account

data class AccountRequest(
	val name: String,
	val ownership: String,
	val phone: String,
	val dependentId: Long?,
	val addressId: Long?
)
