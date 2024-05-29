package com.example.calculator

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculator.ui.theme.CalculatorTheme

data class CalculatorButtonData(val label: String, val type: ButtonType)

enum class ButtonType {
    NUMBER, OPERATION, FUNCTION
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculatorTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    CalculatorApp()
                }
            }
        }
    }
}

@Composable
fun CalculatorApp() {
    var display by remember { mutableStateOf("0") }
    var currentNumber by remember { mutableStateOf("") }
    var operation by remember { mutableStateOf("") }
    var result by remember { mutableStateOf(0.0) }
    var isResultDisplayed by remember { mutableStateOf(false) }
    val configuration = LocalConfiguration.current
    val isPortait = configuration.orientation == Configuration.ORIENTATION_PORTRAIT

    val buttons = listOf(
        CalculatorButtonData("AC", ButtonType.FUNCTION),
        CalculatorButtonData("±", ButtonType.FUNCTION),
        CalculatorButtonData("DEL", ButtonType.FUNCTION),
        CalculatorButtonData("/", ButtonType.OPERATION),
        CalculatorButtonData("7", ButtonType.NUMBER),
        CalculatorButtonData("8", ButtonType.NUMBER),
        CalculatorButtonData("9", ButtonType.NUMBER),
        CalculatorButtonData("*", ButtonType.OPERATION),
        CalculatorButtonData("4", ButtonType.NUMBER),
        CalculatorButtonData("5", ButtonType.NUMBER),
        CalculatorButtonData("6", ButtonType.NUMBER),
        CalculatorButtonData("-", ButtonType.OPERATION),
        CalculatorButtonData("1", ButtonType.NUMBER),
        CalculatorButtonData("2", ButtonType.NUMBER),
        CalculatorButtonData("3", ButtonType.NUMBER),
        CalculatorButtonData("+", ButtonType.OPERATION),
        CalculatorButtonData("00", ButtonType.NUMBER),
        CalculatorButtonData("0", ButtonType.NUMBER),
        CalculatorButtonData(".", ButtonType.NUMBER),
        CalculatorButtonData("=", ButtonType.OPERATION)
    )

    if (isPortait){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF282C34))
                .padding(16.dp)
        ) {
            Text(
                text = "Calculator",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(24.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .background(Color(0xFF282C34), shape = MaterialTheme.shapes.small)
                    .height(290.dp)
                    .padding(16.dp),
                contentAlignment = Alignment.BottomEnd
            ) {
                Text(
                    text = display,
                    fontSize = if (display.length > 12) 24.sp else 48.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.End
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                buttons.chunked(4).forEach { row ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        row.forEach { button ->
                            CalculatorButton(button, isPortait) {
                                when (button.label) {
                                    "AC" -> {
                                        display = "0"
                                        currentNumber = ""
                                        operation = ""
                                        result = 0.0
                                        isResultDisplayed = false
                                    }
                                    "DEL" -> {
                                        if (currentNumber.isNotEmpty()) {
                                            currentNumber = currentNumber.dropLast(1)
                                            display = if (currentNumber.isEmpty()) "0" else currentNumber
                                        }
                                    }
                                    "=" -> {
                                        if (operation.isNotEmpty() && currentNumber.isNotEmpty()) {
                                            val secondOperand = currentNumber.toDouble()
                                            result = when (operation) {
                                                "+" -> result + secondOperand
                                                "-" -> result - secondOperand
                                                "*" -> result * secondOperand
                                                "/" -> result / secondOperand
                                                "%" -> result % secondOperand
                                                else -> result
                                            }
                                            display = if (result % 1 == 0.0) {
                                                result.toInt().toString()
                                            } else {
                                                result.toString()
                                            }
                                            currentNumber = display
                                            operation = ""
                                            isResultDisplayed = true
                                        }
                                    }
                                    "+", "-", "*", "/" -> {
                                        if (currentNumber.isNotEmpty()) {
                                            result = currentNumber.toDouble()
                                            operation = button.label
                                            currentNumber = ""
                                            display = button.label
                                            isResultDisplayed = false
                                        }
                                    }
                                    "±" -> {
                                        if (currentNumber.isNotEmpty()) {
                                            val integerNumber = currentNumber.toIntOrNull()
                                            if (integerNumber != null) {
                                                currentNumber = (-integerNumber).toString()
                                                display = currentNumber
                                            } else {
                                                val number = currentNumber.toDouble()
                                                currentNumber = (-number).toString()
                                                display = currentNumber
                                            }
                                        }
                                    }
                                    "00" -> {
                                        if (currentNumber != "" || result != 0.0) {
                                            currentNumber += button.label
                                            display = currentNumber
                                        }
                                    }
                                    "0" -> {
                                        if (currentNumber != "" || result != 0.0) {
                                            currentNumber += button.label
                                            display = currentNumber
                                        }
                                    }
                                    "." -> {
                                        if (!currentNumber.contains(".")) {
                                            currentNumber += button.label
                                            display = currentNumber
                                        }
                                    }
                                    else -> {
                                        if (isResultDisplayed) {
                                            currentNumber = button.label
                                            display = currentNumber
                                            isResultDisplayed = false
                                        } else {
                                            currentNumber += button.label
                                            display = currentNumber
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    else {
        Row(modifier = Modifier
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Calculator",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(24.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .background(Color(0xFF282C34), shape = MaterialTheme.shapes.small)
                        .height(290.dp)
                        .padding(16.dp),
                    contentAlignment = Alignment.BottomEnd
                ) {
                    Text(
                        text = display,
                        fontSize = if (display.length > 12) 24.sp else 48.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        textAlign = TextAlign.End
                    )
                }


            }

            Column(
                verticalArrangement = Arrangement.spacedBy(5.dp),
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                buttons.chunked(4).forEach { row ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(30.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        row.forEach{button ->
                            CalculatorButton(button, isPortait) {
                                when (button.label) {
                                    "AC" -> {
                                        display = "0"
                                        currentNumber = ""
                                        operation = ""
                                        result = 0.0
                                        isResultDisplayed = false
                                    }
                                    "DEL" -> {
                                        if (currentNumber.isNotEmpty()) {
                                            currentNumber = currentNumber.dropLast(1)
                                            display = if (currentNumber.isEmpty()) "0" else currentNumber
                                        }
                                    }
                                    "=" -> {
                                        if (operation.isNotEmpty() && currentNumber.isNotEmpty()) {
                                            val secondOperand = currentNumber.toDouble()
                                            result = when (operation) {
                                                "+" -> result + secondOperand
                                                "-" -> result - secondOperand
                                                "*" -> result * secondOperand
                                                "/" -> result / secondOperand
                                                "%" -> result % secondOperand
                                                else -> result
                                            }
                                            display = if (result % 1 == 0.0) {
                                                result.toInt().toString()
                                            } else {
                                                result.toString()
                                            }
                                            currentNumber = display
                                            operation = ""
                                            isResultDisplayed = true
                                        }
                                    }
                                    "+", "-", "*", "/" -> {
                                        if (currentNumber.isNotEmpty()) {
                                            result = currentNumber.toDouble()
                                            operation = button.label
                                            currentNumber = ""
                                            display = button.label
                                            isResultDisplayed = false
                                        }
                                    }
                                    "±" -> {
                                        if (currentNumber.isNotEmpty()) {
                                            val integerNumber = currentNumber.toIntOrNull()
                                            if (integerNumber != null) {
                                                currentNumber = (-integerNumber).toString()
                                                display = currentNumber
                                            } else {
                                                val number = currentNumber.toDouble()
                                                currentNumber = (-number).toString()
                                                display = currentNumber
                                            }
                                        }
                                    }
                                    "00" -> {
                                        if (currentNumber != "" || result != 0.0) {
                                            currentNumber += button.label
                                            display = currentNumber
                                        }
                                    }
                                    "0" -> {
                                        if (currentNumber != "" || result != 0.0) {
                                            currentNumber += button.label
                                            display = currentNumber
                                        }
                                    }
                                    "." -> {
                                        if (!currentNumber.contains(".")) {
                                            currentNumber += button.label
                                            display = currentNumber
                                        }
                                    }
                                    else -> {
                                        if (isResultDisplayed) {
                                            currentNumber = button.label
                                            display = currentNumber
                                            isResultDisplayed = false
                                        } else {
                                            currentNumber += button.label
                                            display = currentNumber
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

            }
        }
    }

}

@Composable
fun CalculatorButton(buttonData: CalculatorButtonData, isPortrait: Boolean, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .size(if (isPortrait) 80.dp else 70.dp)
            .background(
                Color(0xFF44475A),
                shape = CircleShape
            ),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = getButtonColor(buttonData.type),
            contentColor = Color.White
        ),
        shape = CircleShape,
        elevation = ButtonDefaults.elevation(defaultElevation = 8.dp)
    ) {
        Text(
            text = buttonData.label,
            fontSize = if (buttonData.label.length > 1) if (buttonData.label.length > 2 && !isPortrait) 12.sp else 16.sp else 24.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }
}
fun getButtonColor(type: ButtonType): Color {
    return when (type) {
        ButtonType.NUMBER -> Color(0xFF44475A)
        ButtonType.OPERATION -> Color(0xFF6272A4)
        ButtonType.FUNCTION -> Color(0xFF8A8D93)
    }
}