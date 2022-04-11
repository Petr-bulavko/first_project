package org.example.projectWebsite.validator;

public class FilmDataValidator {
    private static final String FILM_NAME_REG_EXP = "^[A-Z][a-z]{1,16}$|^[А-Я][а-яё]{1,16}$";
    private static final String FILM_DESCRIPTION_REG_EXP = "^[a-zA-Z]+$|^[А-Яа-яЁё\\s]+$";

    public static boolean isValidFilmName(String filmName) {
        return filmName != null && filmName.matches(FILM_NAME_REG_EXP);
    }

    public static boolean isValidFilmDescription(String filmDescription) {
        return filmDescription != null && filmDescription.matches(FILM_DESCRIPTION_REG_EXP);
    }
}
