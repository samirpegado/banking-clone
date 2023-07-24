package com.example.bankclone.composables

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.bankclone.data.LoginButtonAction
import com.example.bankclone.data.LoginState
import com.example.bankclone.data.LoginViewModel
import com.example.bankclone.R

@Composable
fun BankLoginBar(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        IconButton(onClick = { navController.navigate("Welcome") }) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "ArrowBack",
                modifier = Modifier.size(30.dp),
                tint = Color.White
            )
        }
    }    
}

@Composable
fun BankLoginHeader(
    navController: NavController,
    state: LoginState
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 80.dp)

    ) {
        Text(
            text = "Introduza seu PIN",
            fontSize = 20.sp,
            color = Color.White
        )

        Text(
            text = "•".repeat(state.number1.length),
            fontSize = 60.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        if(state.number1.length == 4){
            navController.navigate("Account")
            state.number1 = ""
        }
    }
}

@Composable
fun BankLoginKey(
    onAction: (LoginButtonAction) -> Unit

) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.9f)
            .padding(top = 120.dp),
        verticalArrangement = Arrangement.SpaceAround
    ) {
        //Linha 1
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 50.dp),
            horizontalArrangement = Arrangement.SpaceBetween

        ) {
            LoginButton(
                symbol = "1",
                modifier = Modifier,
                onClick = {
                    onAction(LoginButtonAction.Number(1))
                }
            )
            LoginButton(
                symbol = "2",
                modifier = Modifier,
                onClick = {
                    onAction(LoginButtonAction.Number(2))
                }
            )
            LoginButton(
                symbol = "3",
                modifier = Modifier,
                onClick = {
                    onAction(LoginButtonAction.Number(3))
                }
            )
        }
        //Linha 2
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 50.dp),
            horizontalArrangement = Arrangement.SpaceBetween

        ) {
            LoginButton(
                symbol = "4",
                modifier = Modifier,
                onClick = {
                    onAction(LoginButtonAction.Number(4))
                }
            )
            LoginButton(
                symbol = "5",
                modifier = Modifier,
                onClick = {
                    onAction(LoginButtonAction.Number(5))
                }
            )
            LoginButton(
                symbol = "6",
                modifier = Modifier,
                onClick = {
                    onAction(LoginButtonAction.Number(6))
                }
            )
        }
        //Linha 3
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 50.dp),
            horizontalArrangement = Arrangement.SpaceBetween

        ) {
            LoginButton(
                symbol = "7",
                modifier = Modifier,
                onClick = {
                    onAction(LoginButtonAction.Number(7))
                }
            )
            LoginButton(
                symbol = "8",
                modifier = Modifier,
                onClick = {
                    onAction(LoginButtonAction.Number(8))
                }
            )
            LoginButton(
                symbol = "9",
                modifier = Modifier,
                onClick = {
                    onAction(LoginButtonAction.Number(9))
                }
            )
        }
        //Linha 2
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp),
            horizontalArrangement = Arrangement.SpaceBetween

        ) {
            IconButton(onClick = { /*TODO*/ }) {
                Image(
                    painter = painterResource(id = R.drawable.fingerprint),
                    contentDescription = "Withdraw",
                    modifier = Modifier
                        .fillMaxSize()
                        .scale(1f),
                    colorFilter = ColorFilter.tint(Color.White)
                )
            }
            LoginButton(
                symbol = "0",
                modifier = Modifier,
                onClick = {
                    onAction(LoginButtonAction.Number(0))
                }
            )
            IconButton(onClick = { onAction(LoginButtonAction.Clear) }) {
                Image(
                    painter = painterResource(id = R.drawable.delete),
                    contentDescription = "Withdraw",
                    modifier = Modifier
                        .fillMaxSize()
                        .scale(1f),
                    colorFilter = ColorFilter.tint(Color.White)
                )
            }
        }

        
    }
    
}

@Composable
fun BankLoginFooter() {
    val context = LocalContext.current
    val toast = Toast.makeText(context, "Digite 4 números aleatórios", Toast.LENGTH_SHORT)
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 20.dp)
    ) {
        Text(
            text = "Esqueceu-se do seu PIN?",
            fontSize = 14.sp,
            color = Color.White,
            modifier = Modifier
                .clickable {
                    toast.show()
                }
        )
    }
}


@Composable
fun LoginScreen(navController: NavController) {

    val viewModel = viewModel<LoginViewModel>()
    val state = viewModel.state

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFF070707))
        .padding(horizontal = 6.dp)) {

        BankLoginBar(navController)
        BankLoginHeader(navController, state = state)
        BankLoginKey(onAction = viewModel::onAction)
        BankLoginFooter()

    }
}


