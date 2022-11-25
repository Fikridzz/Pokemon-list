package co.id.fikridzakwan.domain.model

import android.provider.ContactsContract.CommonDataKinds.Nickname

data class Pokemon(
    val id: Int?,
    val name: String?,
    val nickname: String?,
    val height: Int?,
    val weight: Int?,
    val species: String?,
    val types: String?,
    val imageUrl: String?,
)