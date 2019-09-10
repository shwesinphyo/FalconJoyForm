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
public class MobileValidator extends RegExValidator {
    public MobileValidator() {
      super("(?:[0-9]{9,11})$", "Not a valid phone number.(valid -> 09xxxxxxxxx)");
    }

    @Override
    public int getPriority() {
      return 0;
    }
  }
