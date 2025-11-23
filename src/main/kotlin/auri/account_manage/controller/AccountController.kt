package auri.account_manage.controller

import auri.account_manage.dto.account.AccountRequest
import auri.account_manage.dto.account.AccountResponse
import auri.account_manage.service.AccountService
import com.example.service.AccountService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/accounts")
class AccountController(
	private val service: AccountService
) {

	@GetMapping
	fun getAll(): ResponseEntity<List<AccountResponse>> =
		ResponseEntity.ok(service.getAll())

	@GetMapping("/{id}")
	fun get(@PathVariable id: Long): ResponseEntity<AccountResponse> {
		val found = service.get(id) ?: return ResponseEntity.notFound().build()
		return ResponseEntity.ok(found)
	}

	@PostMapping
	fun create(@RequestBody body: AccountRequest): ResponseEntity<AccountResponse> =
		ResponseEntity.ok(service.create(body))

	@PutMapping("/{id}")
	fun update(@PathVariable id: Long, @RequestBody body: AccountRequest): ResponseEntity<AccountResponse> =
		ResponseEntity.ok(service.update(id, body))

	@DeleteMapping("/{id}")
	fun delete(@PathVariable id: Long): ResponseEntity<Void> =
		if (service.delete(id)) ResponseEntity.noContent().build()
		else ResponseEntity.notFound().build()
}
