package model;

import exceptions.BookingMgtException;

public class Validator {
    public static void ensureValidPassword(String password) throws BookingMgtException {
        if (password.length() > 16 || !password.matches("(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[~@$!%^(#){/}*?&])[A-Za-z\\d@~$!%^(#){/}*?&]{8,}")) {
            throw new BookingMgtException("""
                    invalid password, password must contain at least 8 and maximum of 16 characters, a number ,
                     an Uppercase and a  special character
                    """);
        }
    }

    public static void ensureValidEmail(String email, String type) throws  BookingMgtException {
        if (!isValidEmail(email)) {
            throw new BookingMgtException("invalid " + type);
        }
    }

    public static boolean isValidEmail(String email) {
        return email.matches("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$");
    }

    public static void ensureBothPasswordMatches(String password, String confirmPassword) throws BookingMgtException {
        if (!password.equals(confirmPassword)) throw new BookingMgtException("passwords don't match");
    }

    public static void ensureValidPhone(String phoneNumber) throws BookingMgtException {
        String regex = "^(?!\\b(0)\\1+\\b)(\\+?\\d{1,3}[. -]?)?(\\(\\d{1,3}\\)|\\d{1,3})[. -]?\\d{3}[. -]?\\d{4}$";
        if (!phoneNumber.matches(regex)) {
            throw new BookingMgtException("Invalid phone number");
        }
    }

}
