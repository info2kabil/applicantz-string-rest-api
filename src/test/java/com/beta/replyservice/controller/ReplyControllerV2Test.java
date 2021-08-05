package com.beta.replyservice.controller;

import com.beta.replyservice.ReplyMessage;
import com.beta.replyservice.factory.RulesFactory;
import com.beta.replyservice.service.IRule;
import com.beta.replyservice.util.RuleType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
public class ReplyControllerV2Test {

    @InjectMocks
    ReplyControllerV2 replyControllerV2;

    @Mock
    RulesFactory rulesFactory;

    @Mock
    IRule encodeStringRuleImpl;

    @Mock
    IRule reverseStringRuleImpl;

    @Test
    public void getReplyString_testEndpoint_successResponse() {
        // Prepare
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        // Act
        ReplyMessage response = replyControllerV2.replying();

        // Verify
        Assertions.assertNotNull(response);
        Assertions.assertEquals("Message is empty", response.getMessage());
    }

    @Test
    public void getReplyString_withRule1_successResponse() {
        // Prepare
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        when(rulesFactory.getRule(RuleType.REVERSE_STRING)).thenReturn(reverseStringRuleImpl);
        when(reverseStringRuleImpl.apply("test")).thenReturn("tset");

        // Act
        ReplyMessage response = replyControllerV2.replying("1-test");

        // Verify
        Assertions.assertNotNull(response);
        Assertions.assertEquals("tset", response.getMessage());
        verify(rulesFactory, times(1)).getRule(RuleType.REVERSE_STRING);
        verify(reverseStringRuleImpl, times(1)).apply("test");
        verifyNoMoreInteractions(rulesFactory);
        verifyNoMoreInteractions(reverseStringRuleImpl);
    }

    @Test
    public void getReplyString_withRule2_successResponse() {
        // Prepare
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        when(rulesFactory.getRule(RuleType.ENCODE_STRING)).thenReturn(encodeStringRuleImpl);
        when(encodeStringRuleImpl.apply("test")).thenReturn("12edf34");

        // Act
        ReplyMessage response = replyControllerV2.replying("2-test");

        // Verify
        Assertions.assertNotNull(response);
        Assertions.assertEquals("12edf34", response.getMessage());
        verify(rulesFactory, times(1)).getRule(RuleType.ENCODE_STRING);
        verify(encodeStringRuleImpl, times(1)).apply("test");
        verifyNoMoreInteractions(rulesFactory);
        verifyNoMoreInteractions(reverseStringRuleImpl);
    }

    @Test
    public void getReplyString_withRule12_successResponse() {
        // Prepare
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        when(rulesFactory.getRule(RuleType.ENCODE_STRING)).thenReturn(encodeStringRuleImpl);
        when(rulesFactory.getRule(RuleType.REVERSE_STRING)).thenReturn(reverseStringRuleImpl);
        when(reverseStringRuleImpl.apply("test")).thenReturn("tset");
        when(encodeStringRuleImpl.apply("tset")).thenReturn("12edf34");

        // Act
        ReplyMessage response = replyControllerV2.replying("12-test");

        // Verify
        Assertions.assertNotNull(response);
        Assertions.assertEquals("12edf34", response.getMessage());
        verify(rulesFactory, times(1)).getRule(RuleType.ENCODE_STRING);
        verify(rulesFactory, times(1)).getRule(RuleType.REVERSE_STRING);
        verify(reverseStringRuleImpl, times(1)).apply("test");
        verify(encodeStringRuleImpl, times(1)).apply("tset");
        verifyNoMoreInteractions(rulesFactory);
        verifyNoMoreInteractions(reverseStringRuleImpl);
    }

    @Test
    public void getReplyString_withRule21_successResponse() {
        // Prepare
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        when(rulesFactory.getRule(RuleType.ENCODE_STRING)).thenReturn(encodeStringRuleImpl);
        when(rulesFactory.getRule(RuleType.REVERSE_STRING)).thenReturn(reverseStringRuleImpl);
        when(reverseStringRuleImpl.apply("12edf34")).thenReturn("43fde21");
        when(encodeStringRuleImpl.apply("test")).thenReturn("12edf34");

        // Act
        ReplyMessage response = replyControllerV2.replying("21-test");

        // Verify
        Assertions.assertNotNull(response);
        Assertions.assertEquals("43fde21", response.getMessage());
        verify(rulesFactory, times(1)).getRule(RuleType.ENCODE_STRING);
        verify(rulesFactory, times(1)).getRule(RuleType.REVERSE_STRING);
        verify(reverseStringRuleImpl, times(1)).apply("12edf34");
        verify(encodeStringRuleImpl, times(1)).apply("test");
        verifyNoMoreInteractions(rulesFactory);
        verifyNoMoreInteractions(reverseStringRuleImpl);
    }

    @Test
    public void getReplyString_withEmptyData_splitterAtLast() {
        // Prepare
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        // Act
        ReplyMessage response = replyControllerV2.replying("1-");

        // Verify
        Assertions.assertNotNull(response);
        Assertions.assertEquals("1", response.getMessage());
        verifyNoMoreInteractions(rulesFactory);
        verifyNoMoreInteractions(reverseStringRuleImpl);
    }

    @Test
    public void getReplyString_withEmptyData_splitterAtFront() {
        // Prepare
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        // Act
        ReplyMessage response = replyControllerV2.replying("-Test");

        // Verify
        Assertions.assertNotNull(response);
        Assertions.assertEquals("Test", response.getMessage());
        verifyNoMoreInteractions(rulesFactory);
        verifyNoMoreInteractions(reverseStringRuleImpl);
    }

    @Test
    public void getReplyString_withInvalidData() {
        // Prepare
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        // Act
        ReplyMessage response = replyControllerV2.replying("Test");

        // Verify
        Assertions.assertNotNull(response);
        Assertions.assertEquals("Test", response.getMessage());
        verifyNoMoreInteractions(rulesFactory);
        verifyNoMoreInteractions(reverseStringRuleImpl);
    }

    @Test
    public void getReplyString_withEmptyString_inBothRuleAndData() {
        // Prepare
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        // Act
        ReplyMessage response = replyControllerV2.replying("");

        // Verify
        Assertions.assertNotNull(response);
        Assertions.assertEquals("", response.getMessage());
        verifyNoMoreInteractions(rulesFactory);
        verifyNoMoreInteractions(reverseStringRuleImpl);
    }

    @Test
    public void getReplyString_withInvalidRuleTye() {
        // Act
        Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            replyControllerV2.replying("3-test");
        });
    }
}
