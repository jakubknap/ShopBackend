package pl.knap.shop.security.exception;

import pl.knap.shop.common.exception.BusinessException;

public class RegisterException extends BusinessException {
    public RegisterException(String message) {
        super(message);
    }
}