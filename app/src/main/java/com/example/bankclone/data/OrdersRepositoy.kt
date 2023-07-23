package com.example.bankclone.data

class OrderRepository {
    val orders = listOf(
        Order("COMPRA 3569 ARMAZEM CENTRAL","200,46"),
        Order("COMPRA 2526 ARENAPARK","50,76"),
        Order("COMPRA 8465 SUPERMERCADO TOP","150,30"),
        Order("COMPRA 7890 LOJA MAGIA","75,90"),
        Order("COMPRA 4567 CAFETERIA DELICIA","30,25"),
        Order("COMPRA 9876 LIVRARIA LITERATOS","85,60"),
        Order("COMPRA 1691 FARMACIA SAUDE","40,15"),
        Order("COMPRA 2621 LOJA ESPORTIVA","120,75"),
        Order("COMPRA 8156 RESTAURANTE GOSTOSO","180,50"),
        Order("COMPRA 3694 LOJA DE ELETRONICOS","220,90")
    )
}

data class Order(
    val description: String,
    val price: String
)

