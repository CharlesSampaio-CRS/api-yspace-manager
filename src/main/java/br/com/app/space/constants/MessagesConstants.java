package br.com.app.space.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MessagesConstants {
    public static final String INVALID_EMAIL = "Invalid email";
    public static final String USER_ALREADY_EXISTS = "User already exists";
    public static final String EMPTY_REQUEST = "Empty request";
    public static final String SUCCESSFUL_TOKEN_GENERATOR = "Successful token generator";
    public static final String AUTHENTICATE_ERROR = "Authenticate error";
    public static final String SPACE_ALREADY_EXISTS = "Space already exists";
    public static final String OK = "Ok";

    public static final Integer DEFAULT_SPACE = 1;
}
