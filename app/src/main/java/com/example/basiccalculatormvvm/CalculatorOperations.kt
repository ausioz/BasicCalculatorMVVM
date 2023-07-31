package com.example.basiccalculatormvvm

sealed class CalculatorOperations(val operator:String){
    object Add:CalculatorOperations("+")
    object Subtract:CalculatorOperations("-")
    object Multiply:CalculatorOperations("x")
    object Divide:CalculatorOperations("/")
}
