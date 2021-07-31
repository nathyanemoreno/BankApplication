package com.nappla;

import com.nappla.exceptions.AccountCreationException;
import com.nappla.exceptions.AccountNotFound;

public interface IButtonClickEvent {
    void onClick() throws AccountCreationException, AccountNotFound;
}
