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
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(.5f)
                        .background(color = Color.Cyan),
                        verticalAlignment = Alignment.Bottom
                    ) {
                        CalculatorColumn(modifier = Modifier.weight(1f)) {
                            CalculatorButton("1")
                            CalculatorButton( "4")
                            CalculatorButton("7")
                            CalculatorButton(imageVector = Icons.Filled.Backspace)
                        }
                        CalculatorColumn(modifier = Modifier.weight(1f)) {
                            CalculatorButton("2")
                            CalculatorButton( "5")
                            CalculatorButton("8")
                            CalculatorButton("0")
                        }
                        CalculatorColumn(modifier = Modifier.weight(1f)) {
                            CalculatorButton("3")
                            CalculatorButton( "6")
                            CalculatorButton("9")
                            CalculatorButton("=")
                        }
                        CalculatorColumn(modifier = Modifier.weight(1f)) {
                            CalculatorButton("+")
                            CalculatorButton( "-")
                            CalculatorButton("/")
                            CalculatorButton("*")
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
        modifier = Modifier.clickable {
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
