/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.falconit.joyform.client.application.validators;

import gwt.material.design.client.base.validator.RegExValidator;

/**
 *
 * @author User
 */
public class EmailValidator extends RegExValidator {
    public EmailValidator() {
      super("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.(?:[a-zA-Z]{2,6})$", "Not a valid email address.");
    }

    @Override
    public int getPriority() {
      return 0;
    }
  }
