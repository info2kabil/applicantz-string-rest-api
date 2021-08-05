package com.beta.replyservice.service;

import com.beta.replyservice.service.impl.EncodeStringRuleImpl;
import com.beta.replyservice.util.RuleType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class EncodeStringRuleTest {

    @InjectMocks
    EncodeStringRuleImpl encodeStringRule;

    @Test
    public void applyRule_successResponse() {

        // Act
        String encodedString = encodeStringRule.apply("Test");

        // Verify
        Assertions.assertEquals(RuleType.ENCODE_STRING, encodeStringRule.getType());
        Assertions.assertNotNull(encodedString);
        Assertions.assertEquals("0cbc6611f5540bd0809a388dc95a615b", encodedString);
    }
}
