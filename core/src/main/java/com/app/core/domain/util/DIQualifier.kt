package com.app.core.domain.util

import javax.inject.Qualifier

object DIQualifier {

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class QualifierBreakingNewsRemoteDataSource

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class QualifierEntityDomainMapper
}