package auri.account_manage.dto.address

import auri.account_manage.entity.AddressEntity
import auri.account_manage.model.Address

object AddressMapper {

	fun toDomain(dto: AddressRequest, id: Long = 0): Address =
		Address(
			id = id,
			street = dto.street,
			number = dto.number,
			neighborhood = dto.neighborhood,
			city = dto.city,
			state = dto.state,
			country = dto.country
		)

	fun toResponse(model: Address): AddressResponse =
		AddressResponse(
			id = model.id,
			street = model.street,
			number = model.number,
			neighborhood = model.neighborhood,
			city = model.city,
			state = model.state,
			country = model.country
		)

	fun toEntity(model: Address): AddressEntity =
		AddressEntity(
			id = model.id,
			street = model.street,
			number = model.number,
			neighborhood = model.neighborhood,
			city = model.city,
			state = model.state,
			country = model.country
		)

	fun toDomain(entity: AddressEntity): Address =
		Address(
			id = entity.id,
			street = entity.street,
			number = entity.number,
			neighborhood = entity.neighborhood,
			city = entity.city,
			state = entity.state,
			country = entity.country
		)
}
