package com.example.bankclone.composables

import android.content.Context
import android.os.Build
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bankclone.R
import com.example.bankclone.data.IconSource
import com.example.bankclone.data.OrderRepository
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@Composable
fun AccountHeader() {
    Row(horizontalArrangement = Arrangement.End,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .padding(top = 40.dp, bottom = 10.dp)
        ) {
            Box(modifier = Modifier
                .padding(horizontal = 5.dp)
                .clip(CircleShape)
                .size(35.dp)
                .background(Color.Black)

            ){
                Image(
                    painter = painterResource(id = R.drawable.bell),
                    contentDescription = "",
                    modifier = Modifier
                        .scale(0.6f),
                    colorFilter = ColorFilter.tint(Color.White)
                )
            }
            Box(modifier = Modifier
                .clip(CircleShape)
                .height(35.dp)
                .background(Color.Black)){
                Image(
                    painter = painterResource(id = R.drawable.mbway),
                    contentDescription = "",
                    modifier = Modifier
                        .scale(0.6f),
                    colorFilter = ColorFilter.tint(Color.White)
                )
            }
        }
    }

private const val PREFS_NAME = "MyPrefs"
private const val PREF_SALDO = "saldo"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountBody() {

    val context = LocalContext.current
    val sharedPreferences = remember { context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE) }

    var saldo by rememberSaveable { mutableStateOf(sharedPreferences.getString(PREF_SALDO, "200.000") ?: "200.000") }
    var showDialog by rememberSaveable { mutableStateOf(false) }
    var novoSaldo by rememberSaveable { mutableStateOf("") }

    // AlertDialog
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Novo saldo") },
            text = {
                TextField(
                    value = novoSaldo,
                    onValueChange = {
                        novoSaldo = it
                    },
                    label = { Text("Novo saldo") },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    modifier = Modifier.padding(16.dp)
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        saldo = novoSaldo
                        showDialog = false
                        // Salvar o novo valor do saldo no SharedPreferences
                        sharedPreferences.edit().putString(PREF_SALDO, novoSaldo).apply()
                    }
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                Button(
                    onClick = { showDialog = false }
                ) {
                    Text("Cancelar")
                }
            }
        )
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()){
        Row{
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.92f)
                    .height(175.dp)
                    .clip(shape = RoundedCornerShape(10.dp))
                    .background(
                        color = Color(0xFF070707)
                    )
            ){
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    Text(
                        text = "CONTA À ORDEM",
                        color = Color.White,
                    )
                    Row(verticalAlignment = Alignment.Bottom,
                        modifier = Modifier.padding(10.dp)) {
                        Text(
                            text = "$saldo,00",
                            color = Color.White,
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 5.dp)
                        )
                        Text(
                            text = "EUR",
                            color = Color.White,
                            modifier = Modifier.padding(vertical = 5.dp)
                        )
                    }
                    Button(
                        onClick = { showDialog = true },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent
                        ),
                        modifier = Modifier
                            .border(1.dp, Color.White, shape = CircleShape)
                            .height(32.dp)
                    ) {
                        Text(
                            text = "VER DETALHE",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )

                    }

                }
            }
        }
    }
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
    ) {
        Box(modifier = Modifier
            .padding(horizontal = 5.dp)
            .clip(CircleShape)
            .size(10.dp)
            .background(Color.Black)
        )
        Box(modifier = Modifier
            .padding(horizontal = 5.dp)
            .clip(CircleShape)
            .size(10.dp)
            .background(Color(0xFFE9E9E9))
        )
        Box(modifier = Modifier
            .padding(horizontal = 5.dp)
            .clip(CircleShape)
            .size(10.dp)
            .background(Color(0xFFE9E9E9))
        )
    }
}

@Composable
fun AccountHero() {
    val iconHeroSource = IconSource()
    Row(Modifier
        .padding(top = 15.dp)

    ) {
        for(heroIcon in iconHeroSource.loadHero) {
            Column(
                modifier = Modifier
                    .width(90.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally

            ){
                Box(
                    modifier = Modifier
                        .width(62.dp)
                        .height(62.dp)
                        .clip(shape = RoundedCornerShape(20.dp))
                        .background(Color(0xFFE9EAEF))
                        .clickable { },
                    contentAlignment = Alignment.Center

                ) {
                    Image(
                        painter = painterResource(id = heroIcon.imageResource
                        ),
                        contentDescription = "",
                        modifier = Modifier.size(26.dp)
                    )
                }
                Text(
                    text = heroIcon.title,
                    fontSize = 13.sp,
                    modifier = Modifier
                        .padding(top = 10.dp)
                )
                Text(
                    text = heroIcon.description,
                    fontSize = 13.sp,
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                )
            }
        }
    }
}

@Composable
fun AccountFooter() {
    val iconSource = IconSource()


    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,


    ) {
        Divider(
            color = Color(0xFFE9EAEF),
            thickness = 2.dp
        )
        Row {
            for (footerIcon in iconSource.loadFooter){
                var isClicked by remember { mutableStateOf(false) }
                val color = if(isClicked) Color.Gray else Color.Black
                
                Column(modifier = Modifier
                    .width(78.dp)
                    .padding(vertical = 10.dp)
                    .clickable { isClicked = !isClicked }
                    .pointerInput(Unit) {
                        detectTapGestures { isClicked = !isClicked }
                    },
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = footerIcon.imageResource),
                        contentDescription = footerIcon.description,
                        colorFilter = ColorFilter.tint(color),
                        modifier = Modifier
                            .size(25.dp)
                    )
                    Text(
                        text = footerIcon.title,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = color
                    )
                }
            }                   
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AccountDetails() {
    val orderRepository = OrderRepository()
    Column(modifier = Modifier
        .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 10.dp),
        ) {
            val formatter = DateTimeFormatter.ofPattern("dd/MM")
            val today = LocalDate.now().format(formatter)

            Text(
                text = "Hoje, $today",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
        LazyColumn (
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.83f)
                .padding(top = 10.dp)
        ) {
            itemsIndexed(
                orderRepository.orders
            ){
                _, string ->
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 5.dp),
                    verticalAlignment = Alignment.Top

                ) {
                    Box(modifier = Modifier
                        .padding(start = 30.dp, end = 10.dp)
                        .clip(CircleShape)
                        .size(35.dp)
                        .background(Color(0xFF3F3F3F))
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(0.7f)
                    ) {
                        Text(
                            text = string.description,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold

                        )
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 30.dp),
                        horizontalAlignment = Alignment.End,
                        verticalArrangement = Arrangement.Top
                    ) {
                        Text(
                            text = string.price,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AccountPage(
    navController: NavController
) {
    BackHandler { }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),


    ){
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(Color(0xFF181818), Color(0xFF222222))
                    )
                ),
        )
        Column(
            modifier = Modifier
                .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AccountHeader()
            AccountBody()
            AccountHero()
            AccountDetails()
            AccountFooter()
        }
    }
}