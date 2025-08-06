package eu.tutorials.musicappui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Subscrition(){
    Column(
        modifier = Modifier.height(200.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Manage Subscription", fontSize = 16.sp, fontWeight = FontWeight.Medium)
        Card (modifier = Modifier.padding(8.dp), elevation = 4.dp){
            Column (modifier = Modifier.padding(8.dp)) {
                Column {
//                    Icon(imageVector = Icons.Default.DateRange)
//                    Text(text = "Musical")
                    Row (
                        Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Musical\nFree Tier")
                        TextButton(onClick = {  }) {
                            Row {
                                Text(text = "See All Plans", color = colorResource(id = R.color.mycolor))
                                Icon(imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                                    contentDescription = "See All Plans")
                            }
                        }
                    }
                }
                Divider(thickness = 2.dp,)
                Row (modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)) {
                    Icon(imageVector = Icons.Default.AccountBox, contentDescription = "Get a Plan")
                    Text(text = "Get a Plan")
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun subscrib(){
    Subscrition()
}