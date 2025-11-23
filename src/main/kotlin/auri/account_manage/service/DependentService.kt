package auri.account_manage.service

import auri.account_manage.dto.dependent.DependentMapper
import auri.account_manage.dto.dependent.DependentMapper.toDomain
import auri.account_manage.dto.dependent.DependentMapper.toEntity
import auri.account_manage.dto.dependent.DependentRequest
import auri.account_manage.dto.dependent.DependentResponse
import auri.account_manage.entity.DependentEntity
import auri.account_manage.repository.DependentRepository
import org.springframework.stereotype.Service

@Service
class DependentService(
	private val repository: DependentRepository
) {

	fun getAll(): List<DependentResponse> =
		repository.findAll().map { entity ->
			DependentMapper.toResponse(entity.toDomain())
		}

	fun getById(id: Long): DependentResponse? =
		repository.findById(id).orElse(null)
			?.toDomain()
			?.let { DependentMapper.toResponse(it) }

	fun create(request: DependentRequest): DependentResponse {
		val model = DependentMapper.toModel(request)
		val saved = repository.save(model.toEntity())
		return DependentMapper.toResponse(saved.toDomain())
	}

	fun update(id: Long, request: DependentRequest): Result<DependentResponse> {
		val existing = repository.findById(id).orElse(null)
			?: return Result.failure(NoSuchElementException("Dependent not found"))

		val updatedModel = existing.toDomain().copy(
			name = request.name,
			level = request.level
		)

		val saved = repository.save(updatedModel.toEntity())

		return Result.success(
			DependentMapper.toResponse(saved.toDomain())
		)
	}

	fun delete(id: Long): Boolean {
		if (!repository.existsById(id)) return false
		repository.deleteById(id)
		return true
	}
}
