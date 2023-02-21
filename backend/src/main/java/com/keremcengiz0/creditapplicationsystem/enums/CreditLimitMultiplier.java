package com.keremcengiz0.creditapplicationsystem.enums;

import lombok.Getter;

@Getter
public enum CreditLimitMultiplier {

    CREDIT_LIMIT_MULTIPLIER(4);

    private final int value;

    CreditLimitMultiplier(int value) {
        this.value = value;
    }

}
