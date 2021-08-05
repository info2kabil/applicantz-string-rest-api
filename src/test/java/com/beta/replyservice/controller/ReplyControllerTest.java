package com.beta.replyservice.controller;

import com.beta.replyservice.ReplyMessage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@ExtendWith(MockitoExtension.class)
public class ReplyControllerTest {

    @InjectMocks
    ReplyController replyController;

    @Test
    public void getReplyString_testEndpoint_successResponse() {
        // Prepare
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        // Act
        ReplyMessage response = replyController.replying();

        // Verify
        Assertions.assertNotNull(response);
        Assertions.assertEquals("Message is empty", response.getMessage());
    }

    @Test
    public void getReplyString_withRule1_successResponse() {
        // Prepare
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        // Act
        ReplyMessage response = replyController.replying("test");

        // Verify
        Assertions.assertNotNull(response);
        Assertions.assertEquals("tset", response.getMessage());
    }
}
