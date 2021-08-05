package com.beta.replyservice.factory;

import com.beta.replyservice.service.IRule;
import com.beta.replyservice.util.RuleType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * The Factory class to register the rules and the time component instantiation
 * and returns the rules based on the rule type.
 *
 * @author Kabilan_Selvaraj
 * @version 2.0
 */
@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RulesFactory {
    // Added private Constructor via lombok to avoid the object creation from outside.

    @Autowired
    private List<IRule> rules;
    private Map<RuleType, IRule> rulesServiceCache = new HashMap<>();

    /**
     * To register the rule service that has implemented {@link IRule} interface.
     */
    @PostConstruct
    public void initRulesServiceCache() {
        for (IRule rule : rules) {
            rulesServiceCache.put(rule.getType(), rule);
        }
    }

    /**
     * To get the Rules Implementation by rule type.
     *
     * @param ruleType - Required rules implementation.
     * @return {@link IRule}  - The rule implementation for the passed type.
     */
    public IRule getRule(final RuleType ruleType) {
        if (Objects.nonNull(rulesServiceCache.get(ruleType))) {
            return rulesServiceCache.get(ruleType);
        }
        throw new UnsupportedOperationException("Invalid Rule Type, Kindly Validate the Rule!");
    }

}
