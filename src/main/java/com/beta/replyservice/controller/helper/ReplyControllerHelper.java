package com.beta.replyservice.controller.helper;

import com.beta.replyservice.factory.RulesFactory;
import com.beta.replyservice.util.RuleType;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static com.beta.replyservice.util.StringUtils.DEFAULT_SPLIT_CHARACTER;
import static com.beta.replyservice.util.StringUtils.splitByStr;
import static org.springframework.util.StringUtils.hasText;

/**
 * The helper class for the controller to keep the additional/added methods.
 *
 * @author Kabilan_Selvaraj
 * @version 2.0
 */
public class ReplyControllerHelper {

    @Autowired
    RulesFactory rulesFactory;

    /**
     * To validate the inputMessage has the proper split character or not?
     * If Yes, Apply the rules that are present in the input string.
     * Otherwise, Simply return the passed string.
     *
     * @param message - The input message received from the request.
     * @return - The final output of the conversion.
     */
    protected String validateAndApplyRules(final String message) {
        return hasValidInput(message) ? applyRules(message) : message;
    }

    /**
     * To validate the give inputSting has the split character or not.
     *
     * @param message - The input message received from the request.
     * @return {@link Boolean} - True for valid input & False for invalid input.
     */
    private boolean hasValidInput(final String message) {
        return hasText(message) && message.contains(DEFAULT_SPLIT_CHARACTER);
    }

    /**
     * To split the rule/input string & apply the rules dynamically from the rule factory.
     *
     * @param message - The input message received from the request.
     * @return - The final output after applied the rules in the input string.
     */
    private String applyRules(final String message) {
        List<String> splattedInputStr = splitByStr(message);

        // Initialize the outputMessage with initial string to handle the negative case scenario
        // If we got input like 111- or -Test or 111- then will initialize and return the same string except '-'.
        AtomicReference<String> outputMessage = new AtomicReference<>(splattedInputStr.get(0));

        if (splattedInputStr.size() > 1) {
            outputMessage.set(splattedInputStr.get(1));
            // Iterate the first part of the input string as char by char
            // Identified the ruleType for the character and apply against the inputString iteratively.
            splattedInputStr.get(0).chars().forEach(ruleNumber ->
                    outputMessage.set(rulesFactory.getRule(
                            RuleType.findByRuleTypeNumber(Character.getNumericValue(ruleNumber))
                    ).apply(outputMessage.get())));
        }
        return outputMessage.get();
    }
}
