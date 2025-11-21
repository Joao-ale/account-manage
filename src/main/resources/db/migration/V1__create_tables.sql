-- src/main/resources/db/migration/V1__create_tables.sql
-- Cria tabelas address, dependent e account com relacionamentos (PostgreSQL)

-- ========================================
-- Tabela: address
-- ========================================
CREATE TABLE IF NOT EXISTS address (
    id BIGSERIAL PRIMARY KEY,
    street VARCHAR(255) NOT NULL,
    number VARCHAR(50) NOT NULL,
    neighborhood VARCHAR(255) NOT NULL,
    city VARCHAR(255) NOT NULL,
    state VARCHAR(255) NOT NULL,
    country VARCHAR(255) NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE DEFAULT now() NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE DEFAULT now() NOT NULL
);

-- ========================================
-- Tabela: dependent
-- ========================================
CREATE TABLE IF NOT EXISTS dependent (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    level VARCHAR(50) NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE DEFAULT now() NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE DEFAULT now() NOT NULL
);

-- ========================================
-- Tabela: account
-- ========================================
CREATE TABLE IF NOT EXISTS account (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    ownership VARCHAR(255) NOT NULL,
    phone VARCHAR(50) NOT NULL,
    dependent_id BIGINT,
    address_id BIGINT,
    created_at TIMESTAMP WITHOUT TIME ZONE DEFAULT now() NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE DEFAULT now() NOT NULL
);

-- ========================================
-- Índices e constraints (FKs) para account
-- ========================================
ALTER TABLE account
    ADD CONSTRAINT fk_account_dependent
    FOREIGN KEY (dependent_id)
    REFERENCES dependent (id)
    ON DELETE SET NULL
    ON UPDATE CASCADE;

ALTER TABLE account
    ADD CONSTRAINT fk_account_address
    FOREIGN KEY (address_id)
    REFERENCES address (id)
    ON DELETE SET NULL
    ON UPDATE CASCADE;