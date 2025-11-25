package auri.account_manage.service

import auri.account_manage.dto.address.AddressMapper
import auri.account_manage.dto.address.AddressRequest
import auri.account_manage.dto.address.AddressResponse
import auri.account_manage.repository.AddressRepository
import org.springframework.stereotype.Service

@Service
class AddressService(
	private val repository: AddressRepository
) {

	fun getAllAddress(): List<AddressResponse> =
		repository.findAll()
			.map { AddressMapper.toDomain(it) }
			.map { AddressMapper.toResponse(it) }

	fun getAddress(id: Long): AddressResponse? =
		repository.findById(id).orElse(null)
			?.let { AddressMapper.toDomain(it) }
			?.let { AddressMapper.toResponse(it) }

	fun create(request: AddressRequest): AddressResponse {
		val domain = AddressMapper.toDomain(request)
		val savedEntity = repository.save(AddressMapper.toEntity(domain))
		return AddressMapper.toResponse(AddressMapper.toDomain(savedEntity))
	}

	fun update(id: Long, request: AddressRequest): Result<AddressResponse> {
		val existing = repository.findById(id).orElse(null)
			?: return Result.failure(NoSuchElementException("Address not found"))

		val updatedDomain = AddressMapper.toDomain(request, id)

		val savedEntity = repository.save(AddressMapper.toEntity(updatedDomain))

		return Result.success(
			AddressMapper.toResponse(AddressMapper.toDomain(savedEntity))
		)
	}

	fun delete(id: Long): Boolean {
		if (!repository.existsById(id)) return false
		repository.deleteById(id)
		return true
	}
}
