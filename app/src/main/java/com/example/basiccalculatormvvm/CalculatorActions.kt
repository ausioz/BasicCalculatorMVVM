package com.example.basiccalculatormvvm

sealed class CalculatorActions {
    data class Number(val number: Int) : CalculatorActions()
    data class Operation(val operation:CalculatorOperations): CalculatorActions()
    object Decimal: CalculatorActions()
    object Calculate: CalculatorActions()
    object Clear: CalculatorActions()
}
