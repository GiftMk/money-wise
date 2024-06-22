package com.moneywise.moneywise_api.earnings.models

import java.math.BigDecimal

class Income(private val amount: BigDecimal, private val frequency: Frequency) {}
