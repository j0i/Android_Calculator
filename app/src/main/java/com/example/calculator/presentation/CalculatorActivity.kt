package com.example.calculator.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Backspace
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.layout
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculator.ui.theme.CalculatorTheme
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@AndroidEntryPoint
class CalculatorActivity : ComponentActivity() {
    val calculatorViewModel : CalculatorViewModel by viewModels() // injection of view model
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculatorTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    Column(
                        //horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .background(color = Color.Cyan)
                            .fillMaxSize()
                            .width(IntrinsicSize.Max),
                        verticalArrangement = Arrangement.Bottom) {
                        TextBox("Smonch")
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(.5f),
                            verticalAlignment = Alignment.Bottom
                        ) {
                            CalculatorColumn(modifier = Modifier.weight(1f)) {
                                CalculatorButton("1") { calculatorViewModel.calculatorButtonClick(it) }
                                CalculatorButton("4") { calculatorViewModel.calculatorButtonClick(it) }
                                CalculatorButton("7") { calculatorViewModel.calculatorButtonClick(it) }
                                CalculatorButton(imageVector = Icons.Filled.Backspace)
                            }
                            CalculatorColumn(modifier = Modifier.weight(1f)) {
                                CalculatorButton("2") { calculatorViewModel.calculatorButtonClick(it) }
                                CalculatorButton("5") { calculatorViewModel.calculatorButtonClick(it) }
                                CalculatorButton("8") { calculatorViewModel.calculatorButtonClick(it) }
                                CalculatorButton("0") { calculatorViewModel.calculatorButtonClick(it) }
                            }
                            CalculatorColumn(modifier = Modifier.weight(1f)) {
                                CalculatorButton("3") { calculatorViewModel.calculatorButtonClick(it) }
                                CalculatorButton("6") { calculatorViewModel.calculatorButtonClick(it) }
                                CalculatorButton("9") { calculatorViewModel.calculatorButtonClick(it) }
                                CalculatorButton("=") { calculatorViewModel.calculatorButtonClick(it) }
                            }
                            CalculatorColumn(modifier = Modifier.weight(1f)) {
                                CalculatorButton("+") { calculatorViewModel.calculatorButtonClick(it) }
                                CalculatorButton("-") { calculatorViewModel.calculatorButtonClick(it) }
                                CalculatorButton("/") { calculatorViewModel.calculatorButtonClick(it) }
                                CalculatorButton("*") { calculatorViewModel.calculatorButtonClick(it) }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CalculatorColumn(modifier: Modifier, content: @Composable () -> Unit){
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color.Blue)
            .height(400.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        content.invoke()
    }
}

@Composable
fun CalculatorButton(text: String? = null, imageVector: ImageVector? = null, action: ((value: String) -> Unit)? = null){
    Box(
        modifier = Modifier
            .clickable {
                if (text != null && action != null)
                    action?.invoke(text)
            }
            .size(80.dp)
            .clip(RoundedCornerShape(40.dp))
            .background(Color.Gray),
        contentAlignment = Alignment.Center
    ){
        text?.let {
            Text(
                text,
                fontSize = 20.sp,
            )
        }
        imageVector?.let {
            Icon(
                imageVector = imageVector,
                contentDescription = "Icon"
            )
        }
    }
}

@Composable
fun TextBox(text: String? = null){
   Row(
       verticalAlignment = Alignment.CenterVertically,
       horizontalArrangement = Arrangement.Start,
       modifier = Modifier.padding(15.dp, 0.dp, 0.dp, 15.dp).fillMaxWidth()
   ){
       Box(
           modifier = Modifier.border(BorderStroke(1.dp, color = Color.Black))
       ){
           if (text != null) {
               Text(
                   text,
                   color = Color.Black,
                   fontSize = 20.sp
               )
           }
       }
   }
}
