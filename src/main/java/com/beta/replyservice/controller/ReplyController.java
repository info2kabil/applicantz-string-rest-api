package com.beta.replyservice.controller;

import com.beta.replyservice.ReplyMessage;
import com.beta.replyservice.controller.helper.ReplyControllerHelper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import static com.beta.replyservice.util.StringUtils.reverseString;

/**
 * The V1 Controller class to hold the existing functionality.
 *
 * @author Kabilan_Selvaraj
 * @version 1.0
 */
@RestController("V1")
public class ReplyController extends ReplyControllerHelper {

    @ApiOperation(value = "To test the application endpoint", notes = "Provides test data")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Test endpoint to check the default response ", response = ReplyMessage.class),
            @ApiResponse(code = 500, message = "Internal server error due to application failure")})
    @GetMapping("/reply")
    public ReplyMessage replying() {
        return new ReplyMessage("Message is empty");
    }

    @ApiOperation(value = "To get the reversed string", notes = "Provides reversed given input string")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful reversed the given string", response = ReplyMessage.class),
            @ApiResponse(code = 500, message = "Internal server error due to application failure")})
    @GetMapping("/reply/{message}")
    public ReplyMessage replying(@PathVariable String message) {
        return new ReplyMessage(reverseString(message));
    }
}