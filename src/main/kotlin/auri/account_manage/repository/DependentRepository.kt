package auri.account_manage.repository

import auri.account_manage.entity.DependentEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DependentRepository : JpaRepository<DependentEntity, Long>
