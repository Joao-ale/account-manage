package auri.account_manage.dto.account

import auri.account_manage.entity.AccountEntity
import auri.account_manage.entity.AddressEntity
import auri.account_manage.entity.DependentEntity

object AccountMapper {

	fun toEntity(
		dto: AccountRequest,
		dependent: DependentEntity?,
		address: AddressEntity?
	): AccountEntity =
		AccountEntity(
			id = null,
			name = dto.name,
			ownership = dto.ownership,
			phone = dto.phone,
			dependent = dependent,
			address = address
		)

	fun toDTO(entity: AccountEntity): AccountResponse =
		AccountResponse(
			id = entity.id!!,
			name = entity.name,
			ownership = entity.ownership,
			phone = entity.phone,
			dependentId = entity.dependent?.id,
			addressId = entity.address?.id
		)

	fun updateEntity(
		entity: AccountEntity,
		dto: AccountRequest,
		dependent: DependentEntity?,
		address: AddressEntity?
	): AccountEntity {
		entity.name = dto.name
		entity.ownership = dto.ownership
		entity.phone = dto.phone
		entity.dependent = dependent
		entity.address = address
		return entity
	}
}
