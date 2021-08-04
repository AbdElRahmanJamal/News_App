package com.app.core.domain.util

import java.io.IOException

sealed class AppExceptions : IOException() {
    object NoConnectivityException : AppExceptions()
    object EmptyResponseException : AppExceptions()
    object NullResponseException : AppExceptions()
    object GenericErrorException : AppExceptions()
}
