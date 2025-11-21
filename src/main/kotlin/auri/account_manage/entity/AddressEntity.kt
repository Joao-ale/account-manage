package auri.account_manage.entity

import jakarta.persistence.*

@Entity
@Table(name = "address")
data class AddressEntity(

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	val id: Long = 0,

	@Column(nullable = false)
	val street: String,

	@Column(nullable = false)
	val number: String,

	@Column(nullable = false)
	val neighborhood: String,

	@Column(nullable = false)
	val city: String,

	@Column(nullable = false)
	val state: String,

	@Column(nullable = false)
	val country: String
)
