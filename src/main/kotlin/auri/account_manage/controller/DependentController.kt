package auri.account_manage.controller

import auri.account_manage.dto.dependent.DependentRequest
import auri.account_manage.dto.dependent.DependentResponse
import auri.account_manage.service.DependentService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/dependents")
class DependentController(
	private val service: DependentService
) {

	@GetMapping
	fun getAll(): ResponseEntity<List<DependentResponse>> =
		ResponseEntity.ok(service.getAll())

	@GetMapping("/{id}")
	fun getById(@PathVariable id: Long): ResponseEntity<Any> {
		val response = service.getById(id)
		return if (response != null)
			ResponseEntity.ok(response)
		else
			ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(mapOf("error" to "Dependent not found"))
	}

	@PostMapping
	fun create(@RequestBody request: DependentRequest): ResponseEntity<DependentResponse> {
		val created = service.create(request)
		return ResponseEntity.status(HttpStatus.CREATED).body(created)
	}

	@PutMapping("/{id}")
	fun update(
		@PathVariable id: Long,
		@RequestBody request: DependentRequest
	): ResponseEntity<Any> {

		val result = service.update(id, request)

		return if (result.isSuccess)
			ResponseEntity.ok(result.getOrThrow())
		else {
			when (val ex = result.exceptionOrNull()) {
				is NoSuchElementException ->
					ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(mapOf("error" to "Dependent not found"))
				else ->
					ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body(mapOf("error" to (ex?.message ?: "Invalid data")))
			}
		}
	}

	@DeleteMapping("/{id}")
	fun delete(@PathVariable id: Long): ResponseEntity<Any> {
		val removed = service.delete(id)
		return if (removed)
			ResponseEntity.noContent().build()
		else
			ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(mapOf("error" to "Dependent not found"))
	}
}
