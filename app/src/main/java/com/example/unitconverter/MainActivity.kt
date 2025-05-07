package com.example.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import java.nio.file.WatchEvent
import kotlin.math.roundToInt


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnitConverterTheme {
                UnitConverter()
            }
        }
    }
}

@Composable
fun UnitConverter() {

    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Centimeters") }
    var outputUnit by remember { mutableStateOf("Meters") }
    var iExpanded by remember { mutableStateOf(false) }
    var oExpanded by remember { mutableStateOf(false) }
    val conversionFactor = remember { mutableStateOf(1.00) }
    val oConversionFactor=remember { mutableStateOf(1.00) }


    fun convertUnits(){
        val inputValueDouble=inputValue.toDoubleOrNull()?:0.0
        val result=(inputValueDouble * conversionFactor.value * 100.0/ oConversionFactor.value).roundToInt()/100.0
        outputValue=result.toString()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )


    {
        // things will be stacked below each other
        Text("Unit Converter", modifier = Modifier.padding(70.dp),
            style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = inputValue, onValueChange = {
            inputValue=it
            convertUnits()

        },
            label = {Text("Enter Value")})


        Spacer(modifier = Modifier.height(16.dp))
        Row {

            Box {
                Button(onClick = {iExpanded=true}) {
                    Text(inputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Dropdown")
                }
                DropdownMenu(expanded = iExpanded , onDismissRequest = {iExpanded=false}) {
                    DropdownMenuItem(
                        text = {Text("Centimeter")},
                        onClick = {
                            iExpanded=false
                            inputUnit="Centimeters"
                            conversionFactor.value=0.01
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = {Text("Meter")},
                        onClick = {
                            iExpanded=false
                            inputUnit="Meter"
                            conversionFactor.value=1.0
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = {Text("Feet")},
                        onClick = {
                            iExpanded=false
                            inputUnit="Feet"
                            conversionFactor.value=0.3048
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = {Text("Millimeters")},
                        onClick = {
                            iExpanded=false
                            inputUnit="Millimeters"
                            conversionFactor.value=0.001
                            convertUnits()
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Box {
                Button(onClick = {oExpanded=true}) {
                    Text(outputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Dropdown")
                }
                DropdownMenu(expanded = oExpanded , onDismissRequest = {oExpanded=false}) {
                    DropdownMenuItem(
                        text = {Text("Centimeter")},
                        onClick = {
                            oExpanded=false
                            outputUnit="Centimeters"
                            oConversionFactor.value=0.01
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = {Text("Meter")},
                        onClick = {
                            oExpanded=false
                            outputUnit="Meters"
                            oConversionFactor.value=1.0
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = {Text("Feet")},
                        onClick = {
                            oExpanded=false
                            outputUnit="feet"
                            oConversionFactor.value=0.3048
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = {Text("Millimeters")},
                        onClick = {
                            oExpanded=false
                            outputUnit="Millimeters"
                            oConversionFactor.value=0.001
                            convertUnits()
                        }
                    )
                }

            }
            //things will be stacked side by side
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Result :${outputValue}${outputUnit}",
            style = MaterialTheme.typography.headlineMedium)
    }
}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {
    UnitConverter()
}
