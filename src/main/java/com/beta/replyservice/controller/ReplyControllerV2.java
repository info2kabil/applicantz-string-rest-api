package com.beta.replyservice.controller;

import com.beta.replyservice.ReplyMessage;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The V2 Controller class to hold the new functionality without affecting the existing V1 controlled.
 * And this Controller class extends the {@link ReplyController} to extract the unmodified methods.
 *
 * @author Kabilan_Selvaraj
 * @version 2.0
 */
@RestController("V2")
@RequestMapping("v2")
public class ReplyControllerV2 extends ReplyController {

    @ApiOperation(value = "To get the reply string with rules applied based on the input",
            notes = "The input string will now be comprised of two components, a rule and a string, separated by a dash (-). " +
                    "\n Rules always contain two numbers. Each number represents a string operation." +
                    "\n\n The supported numbers are:\n 1: reverse the string (E.g. kbzw9ru becomes ur9wzbk)" +
                    "\n 2: encode the string via MD5 hash algorithm (E.g. kbzw9ru becomes 0fafeaae780954464c1b29f765861fad)" +
                    "\n\n The numbers can be applied in sequence, i.e. the output of the first rule will serve as the input of the second rule. " +
                    " The numbers can also be repeated, i.e. a rule of 11 would mean reversing the string twice, resulting in no change to the string. (Ex: 11-kbzw9ru)")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful conversion of the given string", response = ReplyMessage.class),
            @ApiResponse(code = 500, message = "Internal server error due to application failure")})
    @GetMapping("/reply/{message}")
    public ReplyMessage replying(@PathVariable String message) {
        return new ReplyMessage(validateAndApplyRules(message));
    }
}