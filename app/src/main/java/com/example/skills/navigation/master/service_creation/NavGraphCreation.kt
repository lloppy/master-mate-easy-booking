package com.example.skills.navigation.master.service_creation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.skills.master.MainMasterScreen
import com.example.skills.master.MasterCreateServiceScreen
import com.example.skills.master.service_creation.SurveyResultScreen
import com.example.skills.master.service_creation.SurveyRoute
import com.example.skills.navigation.master.account.ScreenMater
import com.example.skills.navigation.master.service_creation.Destinations.SURVEY_RESULTS_ROUTE
import com.example.skills.navigation.master.service_creation.Destinations.SURVEY_ROUTE

object Destinations {
    const val SURVEY_ROUTE = "survey"
    const val SURVEY_RESULTS_ROUTE = "surveyresults"
}

@Composable
fun SetupMasterServiceNavGraph(
    navHostController: NavHostController,
) {
    NavHost(navController = navHostController, startDestination = ScreenMater.MasterHomeScreen.route) {
        composable(route = ScreenMater.MasterHomeScreen.route){
            MainMasterScreen()
        }
        composable(route = ScreenMater.MasterCreateServiceScreen.route){
            MasterCreateServiceScreen()
        }

        composable(SURVEY_ROUTE) {
            SurveyRoute(
                onSurveyComplete = {
                    navHostController.navigate(SURVEY_RESULTS_ROUTE)
                },
                onNavUp = navHostController::navigateUp,
            )
        }

        composable(SURVEY_RESULTS_ROUTE) {
            SurveyResultScreen {
                navHostController.popBackStack(ScreenMater.MasterCreateServiceScreen.route, false)
            }
        }

    }
}