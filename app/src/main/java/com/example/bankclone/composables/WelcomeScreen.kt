package com.example.bankclone.composables

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Switch
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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bankclone.data.IconSource

private const val PREFS_USER = "MyUser"
private const val PREF_USERNAME = "username"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BankCloneBar() {

    val context = LocalContext.current
    val sharedPreferences = remember { context.getSharedPreferences(PREFS_USER, Context.MODE_PRIVATE) }

    var username by rememberSaveable { mutableStateOf(sharedPreferences.getString(PREF_USERNAME, "") ?: "") }
    var userDialog by rememberSaveable { mutableStateOf(false) }
    var newUser by rememberSaveable { mutableStateOf("") }

    userDialog = username == ""


    if (userDialog) {
        AlertDialog(
            onDismissRequest = { userDialog = false },
            title = { Text("Nome do usuario") },
            text = {
                TextField(
                    value = newUser,
                    onValueChange = {
                        newUser = it
                    },
                    label = { Text("Ex: Tyler Durden") },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                    modifier = Modifier.padding(16.dp)
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        username = newUser
                        userDialog = false
                        sharedPreferences.edit().putString(PREF_USERNAME, newUser).apply()
                    }
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                Button(
                    onClick = { userDialog = false }
                ) {
                    Text("Cancelar")
                }
            }
        )
    }



    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "hamburger",
                modifier = Modifier.size(30.dp),
                tint = Color.White
            )
        }
    }
    Row(
        modifier = Modifier
            .padding(horizontal = 14.dp, vertical = 36.dp)
    ) {
        Column (modifier = Modifier.padding(bottom = 170.dp)) {
            Text(
                text = "Olá,",
                fontSize = 20.sp,
                color = Color.White,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = username,
                fontSize = 26.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable { username = "" }
            )
        }
    }
}

@Composable
fun CloneMBWay() {

    val iconMBSource = IconSource()

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
    ) {
        Row(
            modifier = Modifier
                .background(Color(0xFFE7E7E7))
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 16.dp)
        ) {
            Text(
                text = "MB Pay",
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )
        }
        Row(
            modifier = Modifier
                .background(Color(0xFF464646))
                .fillMaxWidth()
                .padding(horizontal = 40.dp, vertical = 22.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically

        ) {
            for(welcomeMBIcon in iconMBSource.loadMBWay) {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(
                        onClick = { /*TODO*/ },
                        shape = CircleShape,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF1A1A1A)
                        ),
                        modifier = Modifier
                            .size(64.dp)
                    ) {
                        Box(Modifier.fillMaxSize()) {
                            Image(
                                painter = painterResource(id = welcomeMBIcon.imageResource),
                                contentDescription = welcomeMBIcon.description,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .scale(1.5f),
                                colorFilter = ColorFilter.tint(Color.White)
                            )
                        }
                    }
                    Text(
                        text = welcomeMBIcon.title,
                        fontSize = 15.sp,
                        color = Color.White,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun BankCloneWelcomeApp(
    navController: NavController
) {

    var switchOn by remember {
        mutableStateOf(false)
    }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF070707))
            .padding(horizontal = 6.dp)

    ) {
        BankCloneBar()
        CloneMBWay()

        /* Welcome Footer*/
        Row(
            modifier = Modifier
                .padding(horizontal = 14.dp, vertical = 64.dp)
        ) {
            Column {
                Text(
                    text = "Modo de privacidade",
                    fontSize = 20.sp,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 6.dp),
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = if (switchOn) "Os seus saldos serão omitido" else "Os seus saldos sestarão visíveis",
                    fontSize = 16.sp,
                    color = Color.White,
                )
            }
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.End
            ) {

                Switch(
                    checked = switchOn,
                    onCheckedChange = { switchOn_ ->
                        switchOn = switchOn_
                    }
                )
            }
        }
        Column {
            Button(
                onClick = { navController.navigate("Login") },
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .fillMaxWidth()
                    .size(60.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFE7E7E7)
                )

            ) {
                Text(
                    text = "Login",
                    color = Color.Black,
                    fontSize = 22.sp,
                    )
            }
        }
    }
}
