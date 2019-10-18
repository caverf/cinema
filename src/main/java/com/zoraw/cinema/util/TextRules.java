package com.zoraw.cinema.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class TextRules {
//ta metoda sprawdza więcej niż tylko czy pierwsza litera jest wielka. Misleading name
    public static boolean isFirstLetterCapital(String text) {
        return Character.isUpperCase(text.charAt(0))
                && text.trim()
                .substring(1)
                .chars()
                .allMatch(Character::isLowerCase);
    }
}
