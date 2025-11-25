package auri.account_manage.controller

import auri.account_manage.dto.address.AddressRequest
import auri.account_manage.dto.address.AddressResponse
import auri.account_manage.service.AddressService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/addresses")
class AddressController(
	private val service: AddressService
) {

	@GetMapping
	fun getAll(): ResponseEntity<List<AddressResponse>> =
		ResponseEntity.ok(service.getAllAddress())

	@GetMapping("/{id}")
	fun getById(@PathVariable id: Long): ResponseEntity<Any> {
		val response = service.getAddress(id)
		return if (response != null)
			ResponseEntity.ok(response)
		else
			ResponseEntity.status(HttpStatus.NOT_FOUND).body(mapOf("error" to "Address not found"))
	}

	@PostMapping
	fun create(@RequestBody request: AddressRequest): ResponseEntity<AddressResponse> {
		val created = service.create(request)
		return ResponseEntity.status(HttpStatus.CREATED).body(created)
	}

	@PutMapping("/{id}")
	fun update(
		@PathVariable id: Long,
		@RequestBody request: AddressRequest
	): ResponseEntity<Any> {

		val result = service.update(id, request)

		return if (result.isSuccess) {
			ResponseEntity.ok(result.getOrThrow())
		} else {
			val ex = result.exceptionOrNull()
			when (ex) {
				is NoSuchElementException ->
					ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(mapOf("error" to "Address not found"))
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
			ResponseEntity.status(HttpStatus.NOT_FOUND).body(mapOf("error" to "Address not found"))
	}
}
