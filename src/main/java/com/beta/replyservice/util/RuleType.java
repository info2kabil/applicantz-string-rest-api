package com.beta.replyservice.util;

/**
 * ENUM to hold the RuleType for dynamic approach.
 *
 * @author Kabilan_Selvaraj
 * @version 2.0
 */
public enum RuleType {

    REVERSE_STRING(1),
    ENCODE_STRING(2);

    int type;

    RuleType(int type) {
        this.type = type;
    }

    /**
     * To get the RuleType by the RuleNumber in the inputString.
     *
     * @param findByRuleTypeNumber - Input number to be used for identify the rule.
     * @return    - The identified {@link RuleType} for the input number.
     */
    public static RuleType findByRuleTypeNumber(final int findByRuleTypeNumber) {
        for (RuleType ruleType : values()) {
            if (ruleType.type == findByRuleTypeNumber) {
                return ruleType;
            }
        }
        throw new UnsupportedOperationException("Invalid Rule Type, Kindly Validate the Rule!");
    }
}