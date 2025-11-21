package auri.account_manage.entity

import jakarta.persistence.*

@Entity
@Table(name = "dependent")
data class DependentEntity(

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	val id: Long = 0,

	@Column(nullable = false)
	val name: String,

	@Column(nullable = false)
	val level: String
)
