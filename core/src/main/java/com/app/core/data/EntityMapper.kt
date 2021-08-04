package com.app.core.data

interface EntityMapper<ENTITY, DOMAIN> {

    fun convertFromEntityToDomain(entity: ENTITY): DOMAIN
    fun convertFromDomainToEntity(domain: DOMAIN): ENTITY
}