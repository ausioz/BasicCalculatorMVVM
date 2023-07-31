package com.example.basiccalculatormvvm

data class MainActivityModel(
    var number1:String ="",
    var number2: String ="",
    var operation: CalculatorOperations? = null
)