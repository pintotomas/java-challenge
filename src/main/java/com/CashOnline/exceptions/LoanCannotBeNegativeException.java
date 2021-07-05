package com.CashOnline.exceptions;

import javax.validation.constraints.Positive;

public class LoanCannotBeNegativeException extends Throwable {
    public LoanCannotBeNegativeException(String s) { super(s); }
}
