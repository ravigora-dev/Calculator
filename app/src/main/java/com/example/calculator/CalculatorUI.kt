package com.example.calculator

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculator.ui.theme.DarkGray
import com.example.calculator.ui.theme.MediumGray

enum class ButtonType {
    NUMBER, OPERATION, FUNCTION
}

fun getButtonColor(type: ButtonType): Color {
    return when (type) {
        ButtonType.NUMBER -> Color(0xFF44475A)
        ButtonType.OPERATION -> Color(0xFF6272A4)
        ButtonType.FUNCTION -> Color(0xFF8A8D93)
    }
}

@Composable
fun CalculatorUI(
    viewModel: CalculatorViewModel,
){
    val expressionState by viewModel.expression.observeAsState("")
    val buttonSpacing = 8.dp


    Log.d("MainActivity", "onCreate: ${viewModel.expression}")
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkGray)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            verticalArrangement = Arrangement.spacedBy(buttonSpacing),
        ) {
            LazyRow(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth(),
                reverseLayout = true
            ) {
                item {
                    Text(
                        text = expressionState,
                        textAlign = TextAlign.End,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 32.dp, horizontal = 8.dp),
                        fontWeight = FontWeight.Light,
                        fontSize = 80.sp,
                        color = Color.White,
                        maxLines = 1
                    )
                }
            }
            Divider(
                color = MediumGray,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                CalculatorButton(
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                        .clickable {
                            viewModel.clear()
                            Log.d("MainActivity", "onCreate: $expressionState")
                        },
                    symbol = "AC",
                    color = getButtonColor(ButtonType.FUNCTION)
                )

                CalculatorButton(
                    symbol = "±",
                    color = getButtonColor(ButtonType.OPERATION),
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                        .clickable {
                            viewModel.append("(")
                        }
                )
                CalculatorButton(
                    symbol = "DEL",
                    color = getButtonColor(ButtonType.FUNCTION),
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                        .clickable {
                            viewModel.delete()
                        }
                )

                CalculatorButton(
                    symbol = "÷",
                    color = getButtonColor(ButtonType.OPERATION),
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                        .clickable {
                            viewModel.append("÷")
                        }
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                CalculatorButton(
                    symbol = "7",
                    color = getButtonColor(ButtonType.NUMBER),
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                        .clickable {
                            viewModel.append("7")
                        }
                )
                CalculatorButton(
                    symbol = "8",
                    color = getButtonColor(ButtonType.NUMBER),
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                        .clickable {
                            viewModel.append("8")
                        }
                )
                CalculatorButton(
                    symbol = "9",
                    color = getButtonColor(ButtonType.NUMBER),
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                        .clickable {
                            viewModel.append("9")
                        }
                )
                CalculatorButton(
                    symbol = "×",
                    color = getButtonColor(ButtonType.OPERATION),
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                        .clickable {
                            viewModel.append("×")
                        }
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                CalculatorButton(
                    symbol = "4",
                    color = getButtonColor(ButtonType.NUMBER),
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                        .clickable {
                            viewModel.append("4")
                        }
                )
                CalculatorButton(
                    symbol = "5",
                    color = getButtonColor(ButtonType.NUMBER),
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                        .clickable {
                            viewModel.append("5")
                        }
                )
                CalculatorButton(
                    symbol = "6",
                    color = getButtonColor(ButtonType.NUMBER),
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                        .clickable {
                            viewModel.append("6")
                        }
                )
                CalculatorButton(
                    symbol = "-",
                    color = getButtonColor(ButtonType.OPERATION),
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                        .clickable {
                            viewModel.append("-")
                        }
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                CalculatorButton(
                    symbol = "1",
                    color = getButtonColor(ButtonType.NUMBER),
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                        .clickable {
                            viewModel.append("1")
                        }
                )
                CalculatorButton(
                    symbol = "2",
                    color = getButtonColor(ButtonType.NUMBER),
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)

                        .clickable {
                            viewModel.append("2")
                        }
                )
                CalculatorButton(
                    symbol = "3",
                    color = getButtonColor(ButtonType.NUMBER),
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                        .clickable {
                            viewModel.append("3")
                        }
                )
                CalculatorButton(
                    symbol = "+",
                    color = getButtonColor(ButtonType.OPERATION),
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                        .clickable {
                            viewModel.append("+")
                        }
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                CalculatorButton(
                    symbol = "00",
                    color = getButtonColor(ButtonType.NUMBER),
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                        .clickable {
                            viewModel.append("00")
                        }
                )
                CalculatorButton(
                    symbol = "0",
                    color = getButtonColor(ButtonType.NUMBER),
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                        .clickable {
                            viewModel.append("0")
                        }
                )
                CalculatorButton(
                    symbol = ".",
                    color = getButtonColor(ButtonType.NUMBER),
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                        .clickable {
                            viewModel.append(".")
                        },
                )
                CalculatorButton(
                    symbol = "=",
                    color = getButtonColor(ButtonType.FUNCTION),
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                        .clickable {
                            viewModel.evaluate()
                        }
                )
            }
        }
    }
}

@Composable
fun CalculatorLandscapeUI(
    viewModel: CalculatorViewModel,
) {
    val expressionState by viewModel.expression.observeAsState("")
    val buttonSpacing = 8.dp
//    val fontScale = LocalDensity.current.fontScale

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkGray)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
        ) {
            // Column for displaying the expression
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .background(DarkGray)
                    .horizontalScroll(rememberScrollState(0), reverseScrolling = true),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = expressionState,
                    textAlign = TextAlign.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 32.dp, horizontal = 8.dp),
                    fontWeight = FontWeight.Light,
                    fontSize = 80.sp,
                    color = Color.White,
                    maxLines = 1
                )
            }

            // Column for calculator buttons
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceEvenly,
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
//                        .aspectRatio(1f)
                        .weight(1f),
                    horizontalArrangement = Arrangement.spacedBy(buttonSpacing),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CalculatorButton(
                        symbol = "AC",
                        color = getButtonColor(ButtonType.FUNCTION),
                        modifier = Modifier
//                            .aspectRatio(1f)
                            .weight(1f)
                            .fillMaxHeight(0.9f)
                            .clickable {
                                viewModel.clear()
                            }
                    )

                    CalculatorButton(
                        symbol = "±",
                        color = getButtonColor(ButtonType.OPERATION),
                        modifier = Modifier
//                            .aspectRatio(1f)
                            .weight(1f)
                            .fillMaxHeight(0.9f)
                            .clickable {
                                viewModel.append("(")
                            }
                    )
                    CalculatorButton(
                        symbol = "DEL",
                        color = getButtonColor(ButtonType.FUNCTION),
                        modifier = Modifier
//                            .aspectRatio(1f)
                            .weight(1f)
                            .fillMaxHeight(0.9f)
                            .clickable {
                                viewModel.delete()
                            }
                    )

                    CalculatorButton(
                        symbol = "÷",
                        color = getButtonColor(ButtonType.OPERATION),
                        modifier = Modifier
//                            .aspectRatio(1f)
                            .weight(1f)
                            .fillMaxHeight(0.9f)
                            .clickable {
                                viewModel.append("÷")
                            }
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
//                        .aspectRatio(1f)
                        .weight(1f),
                    horizontalArrangement = Arrangement.spacedBy(buttonSpacing),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CalculatorButton(
                        symbol = "7",
                        color = getButtonColor(ButtonType.NUMBER),
                        modifier = Modifier
//                            .aspectRatio(0.5f, true)
                            .weight(1f)
                            .fillMaxHeight(0.9f)
                            .clickable {
                                viewModel.append("7")
                            }
                    )
                    CalculatorButton(
                        symbol = "8",
                        color = getButtonColor(ButtonType.NUMBER),
                        modifier = Modifier
//                            .aspectRatio(0.5f, true)
                            .weight(1f)
                            .fillMaxHeight(0.9f)
                            .clickable {
                                viewModel.append("8")
                            }
                    )
                    CalculatorButton(
                        symbol = "9",
                        color = getButtonColor(ButtonType.NUMBER),
                        modifier = Modifier
//                            .aspectRatio(0.5f, true)
                            .weight(1f)
                            .fillMaxHeight(0.9f)
                            .clickable {
                                viewModel.append("9")
                            }
                    )
                    CalculatorButton(
                        symbol = "×",
                        color = getButtonColor(ButtonType.OPERATION),
                        modifier = Modifier
//                            .aspectRatio(0.5f, true)
                            .weight(1f)
                            .fillMaxHeight(0.9f)
                            .clickable {
                                viewModel.append("×")
                            }
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
//                        .aspectRatio(1f)
                        .weight(1f),
                    horizontalArrangement = Arrangement.spacedBy(buttonSpacing),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CalculatorButton(
                        symbol = "4",
                        color = getButtonColor(ButtonType.NUMBER),
                        modifier = Modifier
//                            .aspectRatio(1f)
                            .weight(1f)
                            .fillMaxHeight(0.9f)
                            .clickable {
                                viewModel.append("4")
                            }
                    )
                    CalculatorButton(
                        symbol = "5",
                        color = getButtonColor(ButtonType.NUMBER),
                        modifier = Modifier
//                            .aspectRatio(1f)
                            .weight(1f)
                            .fillMaxHeight(0.9f)
                            .clickable {
                                viewModel.append("5")
                            }
                    )
                    CalculatorButton(
                        symbol = "6",
                        color = getButtonColor(ButtonType.NUMBER),
                        modifier = Modifier
//                            .aspectRatio(1f)
                            .weight(1f)
                            .fillMaxHeight(0.9f)
                            .clickable {
                                viewModel.append("6")
                            }
                    )
                    CalculatorButton(
                        symbol = "-",
                        color = getButtonColor(ButtonType.OPERATION),
                        modifier = Modifier
//                            .aspectRatio(1f)
                            .weight(1f)
                            .fillMaxHeight(0.9f)
                            .clickable {
                                viewModel.append("-")
                            }
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
//                        .aspectRatio(1f)
                        .weight(1f),
                    horizontalArrangement = Arrangement.spacedBy(buttonSpacing),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CalculatorButton(
                        symbol = "1",
                        color = getButtonColor(ButtonType.NUMBER),
                        modifier = Modifier
//                            .aspectRatio(1f)
                            .weight(1f)
                            .fillMaxHeight(0.9f)
                            .clickable {
                                viewModel.append("1")
                            }
                    )
                    CalculatorButton(
                        symbol = "2",
                        color = getButtonColor(ButtonType.NUMBER),
                        modifier = Modifier
//                            .aspectRatio(1f)
                            .weight(1f)
                            .fillMaxHeight(0.9f)
                            .clickable {
                                viewModel.append("2")
                            }
                    )
                    CalculatorButton(
                        symbol = "3",
                        color = getButtonColor(ButtonType.NUMBER),
                        modifier = Modifier
//                            .aspectRatio(1f)
                            .weight(1f)
                            .fillMaxHeight(0.9f)
                            .clickable {
                                viewModel.append("3")
                            }
                    )
                    CalculatorButton(
                        symbol = "+",
                        color = getButtonColor(ButtonType.OPERATION),
                        modifier = Modifier
//                            .aspectRatio(1f)
                            .weight(1f)
                            .fillMaxHeight(0.9f)
                            .clickable {
                                viewModel.append("+")
                            }
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
//                        .aspectRatio(1f)
                        .weight(1f),
                    horizontalArrangement = Arrangement.spacedBy(buttonSpacing),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CalculatorButton(
                        symbol = "00",
                        color = getButtonColor(ButtonType.NUMBER),
                        modifier = Modifier
//                            .aspectRatio(1f)
                            .weight(1f)
                            .fillMaxHeight(0.9f)
                            .clickable {
                                viewModel.append("00")
                            }
                    )
                    CalculatorButton(
                        symbol = "0",
                        color = getButtonColor(ButtonType.NUMBER),
                        modifier = Modifier
//                            .aspectRatio(1f)
                            .weight(1f)
                            .fillMaxHeight(0.9f)
                            .clickable {
                                viewModel.append("0")
                            }
                    )
                    CalculatorButton(
                        symbol = ".",
                        color = getButtonColor(ButtonType.NUMBER),
                        modifier = Modifier
//                            .aspectRatio(1f)
                            .weight(1f)
                            .fillMaxHeight(0.9f)
                            .clickable {
                                viewModel.append(".")
                            },
                    )
                    CalculatorButton(
                        symbol = "=",
                        color = getButtonColor(ButtonType.FUNCTION),
                        modifier = Modifier
//                            .aspectRatio(1f)
                            .weight(1f)
                            .fillMaxHeight(0.9f)
                            .clickable {
                                viewModel.evaluate()
                            }
                    )
                }
            }
        }
    }
}





