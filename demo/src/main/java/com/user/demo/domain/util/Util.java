package com.user.demo.domain.util;

public class Util {

    public static final String NAME_NOT_BLANK = "El nombre no puede estar vacio";
    public static final String LAST_NAME_NOT_BLANK = "El apellido no puede estar vacio";
    public static final String IDENTIFICATION_NOT_BLANK = "El documento de identidad no puede estar vacio";
    public static final String IDENTIFICATION_PATTERN = "El documento de identidad debe ser numerico";
    public static final String PHONE_NOT_BLANK = "El telefono no puede estar vacio";
    public static final String PHONE_PATTERN = "El telefono no es valido";
    public static final String DATE_OF_BIRTH_NOT_NULL = "La fecha de nacimiento no puede estar vacia";
    public static final String EMAIL_NOT_BLANK = "El email no puede estar vacio";
    public static final String EMAIL_PATTERN = "El email no es valido";
    public static final String PASSWORD_NOT_BLANK = "La contrasena no puede estar vacia";
    public static final String ROLE_ID_NOT_NULL = "El ID del rol no puede estar vacio";
    public static final String USER_NOT_OF_LEGAL_EGE = "El usuario debe ser mayor de edad";
    public static final String USER_IDENTIFICATION_ALREADY_EXISTS = "La IDENTIFICATION ya existe";
    public static final String USER_EMAIL_ALREADY_EXISTS = "el EMAIL ya existe";
    public static final String ROLE_NOT_FUND = "Role not found";
    public static final int MAX_TIME = 3;
    public static final long LOCK_TIME_DURATION = 5000L;
    public static final String INVALID_CREDENTIALS = "Correo o contraseña inválido. Intentos restantes: ";
    public static final String ACCOUNT_LOCKED = "Cuenta bloqueada. Intenta nuevamente en 5 segundos.";
    public static final String TOO_MANY_ATTEMPTS = "Has alcanzado el número máximo de intentos fallidos. Bloqueado por 5 segundos.";
    public static final String USER_NOT_FOUND = "Usuario no encontrado";

    private Util(){}
}
