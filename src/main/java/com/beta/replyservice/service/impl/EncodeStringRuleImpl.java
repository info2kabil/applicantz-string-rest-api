package com.beta.replyservice.service.impl;

import com.beta.replyservice.service.IRule;
import com.beta.replyservice.util.RuleType;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Service class to hold the EncodeString Rule implementation.
 *
 * @author Kabilan_Selvaraj
 * @version 2.0
 */
@Service
public class EncodeStringRuleImpl implements IRule {

    @Override
    public RuleType getType() {
        return RuleType.ENCODE_STRING;
    }

    @Override
    public String apply(final String inputString) {
        final byte[] digest = digest(inputString.getBytes(StandardCharsets.UTF_8));
        return bytesToHex(digest);
    }

    /**
     * Apply the MD5 hash algorithm with the input byte.
     *
     * @param input - Bytes need to be converted with MessageDigest(MD5).
     * @return - The converted data in the byte format.
     */
    private byte[] digest(byte[] input) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e);
        }
        return md.digest(input);
    }

    /**
     * Convert the given bytes into HEX.
     * @param bytes - Bytes need to be converted into HEX String,
     * @return - The converted HEX String.
     */
    private String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
