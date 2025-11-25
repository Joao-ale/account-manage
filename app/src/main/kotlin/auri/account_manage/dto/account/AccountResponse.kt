package auri.account_manage.dto.account

data class AccountResponse(
	val id: Long,
	val name: String,
	val ownership: String,
	val phone: String,
	val dependentId: Long?,
	val addressId: Long?
)
