package com.beta.replyservice.service;

import com.beta.replyservice.service.impl.ReverseStringRuleImpl;
import com.beta.replyservice.util.RuleType;
import com.beta.replyservice.util.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ReverseStringRuleTest {

    @InjectMocks
    ReverseStringRuleImpl reverseStringRule;

    @Test
    public void applyRule_successResponse() {

        // Act
        String reversedString = reverseStringRule.apply("test");

        // Verify
        Assertions.assertEquals(RuleType.REVERSE_STRING, reverseStringRule.getType());
        Assertions.assertNotNull(reversedString);
        Assertions.assertEquals("tset", reversedString);
    }

    @Test
    public void applyRule_withEmptyString() {

        // Act
        String reversedString = reverseStringRule.apply("");

        // Verify
        Assertions.assertNotNull(reversedString);
        Assertions.assertEquals("", reversedString);
    }

    @Test
    public void directStringUtils_withEmptyString() {

        // Act
        String reversedString = StringUtils.reverseString("");

        // Verify
        Assertions.assertNotNull(reversedString);
        Assertions.assertEquals("", reversedString);
    }
}
