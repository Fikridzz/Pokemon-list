package co.id.fikridzakwan.common

sealed class ReqStatus<out T> {
    data class Success<out T>(val data: T): ReqStatus<T>()
    data class Error(val message: String): ReqStatus<Nothing>()
    object Empty : ReqStatus<Nothing>()
}