package co.tpcreative.compose_mvvm_news.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import co.tpcreative.core.navigation.Screen
import co.tpcreative.feature_details.DetailsScreen
import co.tpcreative.feature_favorite.FavoritesScreen
import co.tpcreative.feature_news.FeedScreen
import co.tpcreative.feature_search.SearchScreen
import co.tpcreative.feature_source.sourceNews.SourceNewsScreen
import co.tpcreative.feature_source.sources.SourceScreen


@Composable
fun SetupNavGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = Screen.Feed.route,
        modifier = modifier
    ) {
        composable(Screen.Feed.route) {
            FeedScreen(navController = navController)
        }
        composable(
            route = Screen.Search.route,
            arguments = listOf(navArgument("sourceId") {
                type = NavType.StringType
                nullable = true
            })
        ) {
            SearchScreen(navController = navController)
        }
        composable(Screen.Favorites.route) {
            FavoritesScreen(navController = navController)
        }
        composable(Screen.Source.route) {
            SourceScreen(navController = navController)
        }
        composable(
            route = Screen.SourceNews.route,
            arguments = listOf(
                navArgument("sourceId") { type = NavType.StringType },
                navArgument("sourceName") { type = NavType.StringType }
            )
        ) {
            SourceNewsScreen(navController = navController)
        }
        composable(
            route = Screen.Details.route,
            arguments = listOf(navArgument("article") { type = NavType.StringType })
        ) {
            DetailsScreen(navController = navController)
        }
    }
}