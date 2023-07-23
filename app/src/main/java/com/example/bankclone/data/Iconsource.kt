package com.example.bankclone.data

import androidx.annotation.DrawableRes
import com.example.bankclone.R

class IconSource {
    val loadFooter = listOf(
        DataIcons(R.drawable.wallet, "contas", "Contas"),
        DataIcons(R.drawable.transfer, "transferir", "Transferir"),
        DataIcons(R.drawable.euro, "pagar", "Pagar"),
        DataIcons(R.drawable.globe, "para si", "Para Si"),
        DataIcons(R.drawable.more, "mais", "Mais")
    )

    val loadHero = listOf(
        DataIcons(R.drawable.transferir, "nacional", "Transferir"),
        DataIcons(R.drawable.invoice, "servicos", "Pagar"),
        DataIcons(R.drawable.phone, "telemovel", "Carregar"),
        DataIcons(R.drawable.send, "IBAN", "Partilhar"),
    )

    val loadMBWay = listOf(
        DataIcons(R.drawable.send, "enviar", "Enviar"),
        DataIcons(R.drawable.qr, "pagar", "Pagar"),
        DataIcons(R.drawable.levantar, "levantar", "Levantar"),
    )
}

data class DataIcons (
    @DrawableRes val imageResource: Int,
    val description: String,
    val title: String
)