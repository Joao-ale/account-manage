package com.example.service

import auri.account_manage.dto.account.AccountRequest
import auri.account_manage.dto.account.AccountResponse
import auri.account_manage.repository.AccountRepository
import auri.account_manage.repository.AddressRepository
import auri.account_manage.repository.DependentRepository
import auri.account_manage.dto.account.AccountMapper
import org.springframework.stereotype.Service

@Service
class AccountService(
	private val accountRepository: AccountRepository,
	private val dependentRepository: DependentRepository,
	private val addressRepository: AddressRepository
) {

	fun create(dto: AccountRequest): AccountResponse {
		val dependent = dto.dependentId?.let { dependentRepository.findById(it).orElse(null) }
		val address = dto.addressId?.let { addressRepository.findById(it).orElse(null) }

		val account = AccountMapper.toEntity(dto, dependent, address)
		return AccountMapper.toDTO(accountRepository.save(account))
	}

	fun update(id: Long, dto: AccountRequest): AccountResponse {
		val account = accountRepository.findById(id).orElseThrow {
			IllegalArgumentException("Account not found with id $id")
		}

		val dependent = dto.dependentId?.let { dependentRepository.findById(it).orElse(null) }
		val address = dto.addressId?.let { addressRepository.findById(it).orElse(null) }

		val updated = AccountMapper.updateEntity(account, dto, dependent, address)
		return AccountMapper.toDTO(accountRepository.save(updated))
	}

	fun get(id: Long): AccountResponse {
		val account = accountRepository.findById(id).orElseThrow {
			IllegalArgumentException("Account not found with id $id")
		}
		return AccountMapper.toDTO(account)
	}

	fun getAll(): List<AccountResponse> =
		accountRepository.findAll().map(AccountMapper::toDTO)

	fun delete(id: Long): Result<Boolean> {
		val account = accountRepository.findById(id).orElse(null)
			?: return Result.failure(NoSuchElementException("Account not found"))

		accountRepository.deleteById(id)
		return Result.success(true)
	}

}
