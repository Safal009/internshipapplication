package com.example.iv1

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.iv1.ui.*

enum class IVScreen(@StringRes val title: Int) {
    Start(title = R.string.app_name),
    DrugList(title = R.string.start),
    SelectedDrugs(title = R.string.review)
}

@Composable
fun IVAppBar(
    currentScreen: IVScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title ={ Text(stringResource(id = currentScreen.title)) },
        modifier = modifier,
        navigationIcon ={
            if(canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back")
                }
            }
        }
    )
}

@Composable
fun Start(
    modifier: Modifier = Modifier,
    viewModel: DrugViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = IVScreen.valueOf(
        backStackEntry?.destination?.route ?:IVScreen.Start.name
    )

    Scaffold(
        topBar = {
            IVAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding -> val uiState by viewModel.response

        NavHost(
            navController = navController,
            IVScreen.Start.name,
            modifier = modifier.padding(innerPadding)
        ) {
            composable(route = IVScreen.Start.name) {
                StartScreen(
                    onIRCalcButtonClicked = { /*TODO: Navigate to Infusion Rate Calculator */ },
                    onCompatibilityCheckButtonClicked = {
                        navController.navigate(IVScreen.DrugList.name)
                    }
                )
            }

            composable(route = IVScreen.DrugList.name) {
                SetData(
                    viewModel = viewModel,
                    onDoneBtnClicked = {
                        navController.navigate(IVScreen.SelectedDrugs.name)
                    }
                )
                /* TODO: implement cancel button logic */
            }

            composable(route = IVScreen.SelectedDrugs.name) {
                ShowSelectedList(drugs = tempList)
                /* TODO: display list of selected drugs
                *   two buttons : Cancel and Check
                * TODO: cancel and return to home screen
                *  check the incompatibility for selected drugs. */
            }
        }
    }
}