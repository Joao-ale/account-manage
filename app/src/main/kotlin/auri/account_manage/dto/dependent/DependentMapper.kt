package auri.account_manage.dto.dependent


import auri.account_manage.entity.DependentEntity
import auri.account_manage.model.Dependent


object DependentMapper {
	fun toModel(dto: DependentRequest, id: Long = 0): Dependent =
		Dependent(
			id = id,
			name = dto.name,
			level = dto.level
		)


	fun toResponse(model: Dependent): DependentResponse =
		DependentResponse(
			id = model.id,
			name = model.name,
			level = model.level
		)

	fun Dependent.toEntity() = DependentEntity(
		id = this.id,
		name = this.name,
		level = this.level
	)

	fun DependentEntity.toDomain() = Dependent(
		id = this.id,
		name = this.name,
		level = this.level
	)
}