package com.beta.replyservice.service.impl;

import com.beta.replyservice.service.IRule;
import com.beta.replyservice.util.RuleType;
import org.springframework.stereotype.Service;

import static com.beta.replyservice.util.StringUtils.reverseString;

/**
 * Service class to hold the ReverseString Rule implementation.
 *
 * @author Kabilan_Selvaraj
 * @version 2.0
 */
@Service
public class ReverseStringRuleImpl implements IRule {

    @Override
    public RuleType getType() {
        return RuleType.REVERSE_STRING;
    }

    @Override
    public String apply(final String inputString) {
        return reverseString(inputString);
    }
}
