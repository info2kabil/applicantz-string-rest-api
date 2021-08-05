package com.beta.replyservice.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

import static org.springframework.util.StringUtils.hasText;

/**
 * UTIL Class to hold the common methods and constants.
 *
 * @author Kabilan_Selvaraj
 * @version 2.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StringUtils {

    public static final String EMPTY_STRING = "";
    public static final String DEFAULT_SPLIT_CHARACTER = "-";

    /**
     * To reverse the given string if it's not empty.
     * If it's empty or null then returning the empty string.
     *
     * @param inputString - The inputString to be considered for the reverse conversion.
     * @return - The converted reverseString.
     */
    public static String reverseString(final String inputString) {
        return hasText(inputString) ? new StringBuilder(inputString).reverse().toString() : EMPTY_STRING;
    }

    /**
     * To split the inputString using defaultSplit Character.
     *
     * @param inputString - The inputString to be considered for the reverse conversion.
     * @return - The splatted string as {@link List}.
     */
    public static List<String> splitByStr(final String inputString) {
        return splitByStr(inputString, DEFAULT_SPLIT_CHARACTER);
    }

    /**
     * To split the inputString using given split Character.
     *
     * @param inputString - The inputString to be considered for the reverse conversion.
     * @param splitStr    - The string to be used for the split.
     * @return - The splatted string as {@link List}.
     */
    public static List<String> splitByStr(final String inputString, final String splitStr) {
        return Arrays.asList(inputString.split(splitStr));
    }
}
