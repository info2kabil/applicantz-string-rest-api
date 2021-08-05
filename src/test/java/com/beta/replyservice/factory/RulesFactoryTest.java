package com.beta.replyservice.factory;

import com.beta.replyservice.service.IRule;
import com.beta.replyservice.util.RuleType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.util.reflection.FieldSetter;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
public class RulesFactoryTest {

    @InjectMocks
    RulesFactory rulesFactory;

    @Mock
    IRule encodeStringRuleImpl;

    @Mock
    IRule reverseStringRuleImpl;

    @Test
    public void getRuleFromFactory_success() throws NoSuchFieldException {

        FieldSetter.setField(rulesFactory, rulesFactory.getClass().getDeclaredField("rules"),
                Arrays.asList(encodeStringRuleImpl, reverseStringRuleImpl));
        rulesFactory.initRulesServiceCache();
        Map<RuleType, IRule> mockCache = new HashMap<>();
        mockCache.put(RuleType.ENCODE_STRING, encodeStringRuleImpl);
        FieldSetter.setField(rulesFactory, rulesFactory.getClass().getDeclaredField("rulesServiceCache"),
                mockCache);
        // Act
        IRule encodedStringRule = rulesFactory.getRule(RuleType.ENCODE_STRING);

        // Verify
        Assertions.assertNotNull(encodedStringRule);
    }

    @Test
    public void getUnRegisteredRuleFromFactory_failure() {

        Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            rulesFactory.getRule(RuleType.ENCODE_STRING);
        });
    }
}
