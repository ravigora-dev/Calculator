package com.example.calculator

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class CalculatorViewModel(private val savedStateHandle: SavedStateHandle): ViewModel() {

    companion object {
        private const val STATE_EXPRESSION = "expression"
    }

    private val _expression = MutableLiveData(savedStateHandle[STATE_EXPRESSION] ?: "")
    val expression: LiveData<String> = _expression
    fun clear() {
        _expression.value = ""
        savedStateHandle[STATE_EXPRESSION] = ""
    }

    fun append(char: String) {
        viewModelScope.launch {
            Log.d("append", "$char Expression Value:${expression.value}")
            var exp = _expression.value
            if (char in "0123456789" || char == "00") {
                if (char == "00" && expression.value?.isEmpty() == true) {
                    return@launch
                }
                if (char == "0" && expression.value?.isEmpty() == true) {
                    return@launch
                }
                exp += char
            }else if(char in "+-×÷") {
                if (_expression.value?.isNotEmpty() == true) {
                    val lastChar = _expression.value!!.last()

                    if (lastChar in "+-×÷") {
                        delete()
                    }
                }
                exp += char
            }else if(char == ".") {
                if (_expression.value?.isNotEmpty() == true) {
                    val lastChar = expression.value!!.last()
                    if (lastChar!='.') {
                        // if last char is an operator, and the current char is a dot, add a zero before the dot
                        if (lastChar in "+-×÷") {
                            exp += "0"
                        }
                        exp += char
                    }
                }

            }
            _expression.value = exp
            savedStateHandle[STATE_EXPRESSION] = exp
        }
    }

    fun delete() {
        viewModelScope.launch{
            if (_expression.value?.isNotEmpty() == true) {
                val updatedExpression = _expression.value!!.dropLast(1)
                _expression.value = updatedExpression
                savedStateHandle[STATE_EXPRESSION] = updatedExpression
            }
        }
    }

    fun evaluate() {
        viewModelScope.launch{
            val newExpression = try {
                val result = evaluate(expression.value ?: "")
                result.toString()
            } catch (e: Exception) {
                "Error"
            }
            _expression.value = newExpression
            savedStateHandle[STATE_EXPRESSION] = newExpression
        }
    }
}