package com.moneywise.moneywise_api.earnings.graphql

data class EarningsInput(
    val income: IncomeInput,
    val expenses: List<ExpenseInput>,
    val decimalPlaces: Int?
)
