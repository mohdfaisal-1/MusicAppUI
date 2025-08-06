package eu.tutorials.musicappui.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.primarySurface
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Icon
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import eu.tutorials.musicappui.AccountView
import eu.tutorials.musicappui.AccoutDialog
import eu.tutorials.musicappui.BrowseScreen
import eu.tutorials.musicappui.Hoem
import eu.tutorials.musicappui.Library
import eu.tutorials.musicappui.MainViewModel
import eu.tutorials.musicappui.R
import eu.tutorials.musicappui.Screen
import eu.tutorials.musicappui.Subscrition
import eu.tutorials.musicappui.screensInBottom
import eu.tutorials.musicappui.screensInDrawer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch



@Composable
fun MainView() {
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val scope: CoroutineScope = rememberCoroutineScope()
    val viewModel: MainViewModel = viewModel()

    val controller: NavController = rememberNavController()
    val navBackStackEntry by controller.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val isSheetFullScreen by remember { mutableStateOf(false) }
    val modifier = if (isSheetFullScreen) Modifier.fillMaxSize() else Modifier.fillMaxWidth()

    val dialogOpen = remember { mutableStateOf(false) }

    val currentScreen = remember { viewModel.currentScreen.value }

    val title = remember { mutableStateOf(currentScreen.title) }

    val modalSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmValueChange = { it != ModalBottomSheetValue.HalfExpanded  }
    )
    val roundedCornerRadius = if (isSheetFullScreen) 0.dp else 10.dp

    val bottomBar: @Composable () -> Unit = {
        if (currentScreen is Screen.DrawerScreen || currentScreen == Screen.BottomScreen.Home){
            BottomNavigation (Modifier.wrapContentSize()){
                screensInBottom.forEach {
                    item ->
                    val isSelected = currentRoute == item.bRoute
                    Log.d("Navigation", "Item: ${item.bTitle}, Current route: $currentRoute, Is selected")
                    val tint = if (isSelected) Color.Black else Color.White
                    BottomNavigationItem(
                        selected = currentRoute == item.bRoute,
                        onClick = {controller.navigate(item.bRoute)
                                  title.value = item.bTitle
                                  },
                        icon = { Icon(tint = tint, painter = painterResource(id = item.icon), contentDescription = item.bTitle) },
                        label = {Text(text = item.bTitle, color = tint)},
                        selectedContentColor = Color.White,
                        unselectedContentColor = Color.Black
                    )
                }
            }
        }
    }
    ModalBottomSheetLayout(
        sheetState = modalSheetState,
        sheetShape = RoundedCornerShape(topStart = roundedCornerRadius, topEnd = roundedCornerRadius),
        sheetContent = {
            MoreBotomSheet(modifier = modifier)
        }
    ) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 46.dp)
    ) {
        Scaffold(
            scaffoldState = scaffoldState,
            bottomBar = bottomBar,
            topBar = {
                TopAppBar(
                    actions = {
                        IconButton(onClick = {
                            scope.launch {
                                if (modalSheetState.isVisible){
                                    modalSheetState.hide()
                                }
                                else{
                                    modalSheetState.show()
                                }
                            }
                        })
                        {
                         Icon(imageVector = Icons.Default.MoreVert, tint = Color.White, contentDescription = null)
                        }
                    },
                    title = { Text(title.value) },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                scaffoldState.drawerState.open()
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                tint = Color.White,
                                contentDescription = "Home"
                            )
                        }
                    }
                )
            },
//            scaffoldState = scaffoldState,
            drawerContent = {
                LazyColumn(Modifier.padding(16.dp)){
                    items(screensInDrawer){
                        item ->
                        DrawerItem(selected = currentRoute == item.dRoute, item = item) {
                            scope.launch {
                                scaffoldState.drawerState.close()
                            }
                            if (item.dRoute == "add_account"){
                                dialogOpen.value = true
                            }
                            else{
                                controller.navigate(item.dRoute)
                                title.value = item.dTitle
                            }
                        }
                    }
                }
            }
        ) {
            Column(
                modifier = Modifier.padding(it)
            ) {
                Navigation(navController = controller, viewModel = viewModel, pd = it)

                AccoutDialog(dialogOpen = dialogOpen)
            }
        }
    }
} }

@Composable
fun DrawerItem(
    selected: Boolean,
    item: Screen.DrawerScreen,
    onDrawerItemClicked: () -> Unit
){
    val background = if (selected) Color.LightGray else Color.White

    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp, vertical = 16.dp).background(background).clickable{
            onDrawerItemClicked()
        }
    ) {
        Icon(painter = painterResource(id = item.icon),
            contentDescription = item.title,
            Modifier.padding(end = 8.dp, top = 4.dp)
            )
        Text(
            text = item.title,
            style = MaterialTheme.typography.h5
        )
    }
}



@Composable
fun Navigation(navController: NavController, viewModel: MainViewModel, pd: PaddingValues){
    NavHost(
        navController = navController as NavHostController,
        startDestination = Screen.DrawerScreen.AddAccount.route,
        modifier = Modifier.padding(pd)
    ) {
        composable(Screen.BottomScreen.Home.bRoute){
            Hoem()
        }
        composable(Screen.BottomScreen.Browse.bRoute){
            BrowseScreen()
        }
        composable(Screen.BottomScreen.Library.bRoute){
            Library()
        }

        composable(Screen.DrawerScreen.Account.route){
            AccountView()
        }
        composable(Screen.DrawerScreen.Subscription.route){
            Subscrition()
        }
        composable(Screen.DrawerScreen.AddAccount.route){

        }
    }
}

@Composable
fun MoreBotomSheet(modifier: Modifier){
     Box(modifier = modifier.fillMaxSize().height(50.dp).background(MaterialTheme.colors.primarySurface)){
         Column (modifier = Modifier.padding(16.dp),
             verticalArrangement = Arrangement.SpaceBetween){
             Row (modifier = Modifier.padding(16.dp)){
                 Icon(modifier = Modifier.padding(end = 8.dp),
                     painter = painterResource(id = R.drawable.outline_share_24),
                     tint = Color.White,
                     contentDescription = "")
                 Text("Share", fontSize = 20.sp, fontWeight = FontWeight.Medium, color = Color.White)
             }
             Row (modifier = Modifier.padding(16.dp)){
                 Icon(modifier = Modifier.padding(end = 8.dp),
                     painter = painterResource(id = R.drawable.outline_settings_24), tint = Color.White,
                     contentDescription = "")
                 Text("Settings", fontSize = 20.sp, fontWeight = FontWeight.Medium, color = Color.White)
             }
             Row (modifier = modifier.padding(16.dp)){
                 Icon(modifier = Modifier.padding(end = 8.dp),
                     painter = painterResource(id = R.drawable.outline_live_help_24), tint = Color.White,
                     contentDescription = "")
                 Text("Help!", fontSize = 20.sp, fontWeight = FontWeight.Medium, color = Color.White)
             }
         }
     }
}


@Preview(showBackground = true)
@Composable
fun mainview(){
    MoreBotomSheet(modifier = Modifier)
}

























