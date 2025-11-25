package auri.account_manage.entity

import jakarta.persistence.*

@Entity
@Table(name = "account")
data class AccountEntity(

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	val id: Long? = 0,

	@Column(nullable = false)
	var name: String,

	@Column(nullable = false)
	var ownership: String,

	@Column(nullable = false)
	var phone: String,

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dependent_id")
	var dependent: DependentEntity? = null,

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "address_id")
	var address: AddressEntity? = null
)
