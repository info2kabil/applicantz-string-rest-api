package com.beta.replyservice.service;

import com.beta.replyservice.util.RuleType;

/**
 * The Interface to hold the abstractions of the rule implementation.
 *
 * @author Kabilan_Selvaraj
 * @version 2.0
 */
public interface IRule {

  /**
   * To keep track the RuleType of the implementation.
   *
   * @return {@link RuleType} Corresponding to the implementation
   */
  RuleType getType();

  /**
   * To apply the actual rule to the given inputString and return the new outputString.
   *
   * @param inputString - The given inputString where the rule to be applied.
   * @return - The outputString after applied the rule.
   */
  String apply(final String inputString);
}
