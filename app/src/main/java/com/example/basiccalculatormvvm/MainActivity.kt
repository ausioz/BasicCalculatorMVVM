package com.example.basiccalculatormvvm

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels

import com.example.basiccalculatormvvm.databinding.ActivityMainBinding


class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: MainActivityViewModel by viewModels()
        val onAction = viewModel::onAction


        viewModel.state.observeForever {
            binding.inputTv.text =
                viewModel.state.value?.number1 + (viewModel.state.value?.operation?.operator
                    ?: "") + viewModel.state.value?.number2

            binding.btnClear.setOnClickListener { viewModel.onAction(CalculatorActions.Clear) }
            binding.btnPoint.setOnClickListener { viewModel.onAction(CalculatorActions.Decimal) }
            binding.btnEqual.setOnClickListener { viewModel.onAction(CalculatorActions.Calculate) }

            binding.btnDivide.setOnClickListener {
                viewModel.onAction(
                    CalculatorActions.Operation(
                        CalculatorOperations.Divide
                    )
                )
            }
            binding.btnMultiply.setOnClickListener {
                viewModel.onAction(
                    CalculatorActions.Operation(
                        CalculatorOperations.Multiply
                    )
                )
            }
            binding.btnSubtract.setOnClickListener {
                viewModel.onAction(
                    CalculatorActions.Operation(
                        CalculatorOperations.Subtract
                    )
                )
            }
            binding.btnAdd.setOnClickListener {
                viewModel.onAction(
                    CalculatorActions.Operation(
                        CalculatorOperations.Add
                    )
                )
            }

            binding.btn0.setOnClickListener { onAction(CalculatorActions.Number(0)) }
            binding.btn1.setOnClickListener { onAction(CalculatorActions.Number(1)) }
            binding.btn2.setOnClickListener { onAction(CalculatorActions.Number(2)) }
            binding.btn3.setOnClickListener { onAction(CalculatorActions.Number(3)) }

            binding.btn4.setOnClickListener { onAction(CalculatorActions.Number(4)) }
            binding.btn5.setOnClickListener { onAction(CalculatorActions.Number(5)) }
            binding.btn6.setOnClickListener { onAction(CalculatorActions.Number(6)) }

            binding.btn7.setOnClickListener { onAction(CalculatorActions.Number(7)) }
            binding.btn8.setOnClickListener { onAction(CalculatorActions.Number(8)) }
            binding.btn9.setOnClickListener { onAction(CalculatorActions.Number(9)) }
        }
    }
}