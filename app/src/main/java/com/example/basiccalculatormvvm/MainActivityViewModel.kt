package com.example.basiccalculatormvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.lang.StringBuilder

class MainActivityViewModel : ViewModel() {

    private var _state: MutableLiveData<MainActivityModel> = MutableLiveData(MainActivityModel())
    val state: LiveData<MainActivityModel> = _state


    fun onAction(action: CalculatorActions) {
        viewModelScope.launch {
            when (action) {
                is CalculatorActions.Number -> enterNumber(action.number)
                is CalculatorActions.Operation -> enterOperation(action.operation)
                is CalculatorActions.Decimal -> enterDecimal()
                is CalculatorActions.Calculate -> doCalculate()
                is CalculatorActions.Clear -> doClear()
            }
        }
    }

    private fun enterNumber(number: Int) {
        if (_state.value?.operation == null) {
            _state.value = _state.value?.copy(
                number1 = _state.value?.number1 + number
            )
            return
        } else {
            _state.value = _state.value?.copy(
                number2 = _state.value?.number2 + number
            )
        }
    }

    private fun enterOperation(operation: CalculatorOperations) {
        if (_state.value?.number1?.isNotBlank() == true) {
            _state.value = _state.value?.copy(
                operation = operation
            )
        }
        if (_state.value?.number1?.isBlank() == true) {
            _state.value = _state.value?.copy(
                number1 = "0", operation = operation
            )
        }

    }

    private fun enterDecimal() {
        val sb = StringBuilder()

        if (_state.value?.number1?.isNotBlank() == true && _state.value?.operation == null && _state.value?.number1?.contains(
                "."
            ) == false
        ) {

            _state.value = _state.value?.copy(
                number1 = _state.value?.number1 + sb.append(".")
            )
        }
        if (_state.value?.number2?.isNotBlank() == true && _state.value?.operation != null && _state.value?.number2?.contains(
                "."
            ) == false
        ) {
            _state.value = _state.value?.copy(
                number2 = _state.value?.number2 + sb.append(".")
            )
        }
    }

    private fun doCalculate() {
        val number1 = _state.value?.number1?.toDoubleOrNull()
        val number2 = _state.value?.number2?.toDoubleOrNull()

        if (number1 != null && number2 != null) {
            val result = when (_state.value?.operation) {
                is CalculatorOperations.Add -> number1 + number2
                is CalculatorOperations.Subtract -> number1 - number2
                is CalculatorOperations.Multiply -> number1 * number2
                is CalculatorOperations.Divide -> number1 / number2
                else -> {
                    return
                }
            }
            _state.value = _state.value?.copy(
                number1 = result.toString(), number2 = "", operation = null
            )
        }
    }

    private fun doClear() {
        _state.value = _state.value?.copy(
            number1 = "", number2 = "", operation = null
        )
    }

}