package com.example.bankclone

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bankclone.composables.AccountPage
import com.example.bankclone.composables.BankCloneWelcomeApp
import com.example.bankclone.composables.LoginScreen
//import com.example.bankclone.composables.LoginScreen
import com.example.bankclone.ui.theme.BankCloneTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BankCloneTheme {

                val navController = rememberNavController()
                
                NavHost(navController = navController, startDestination = "Welcome"){
                    composable(route = "Welcome"){ BankCloneWelcomeApp(navController) }
                    composable(route = "Login"){ LoginScreen(navController) }
                    composable(route = "Account"){ AccountPage(navController) }
                }
            }
        }
    }
}