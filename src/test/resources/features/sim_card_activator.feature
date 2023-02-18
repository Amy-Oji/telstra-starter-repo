Feature: SIM Card Activation Test
  This is a test to know if a SIM card was successfully activated or not

  Scenario Outline: is SIM card activated or is not?
    Given SIM card iccid is "<iccid>"
    When asked if SIM card is activated
    Then return this "<answer>"

    Examples:
      | iccid                | answer  |
      | 1255789453849037777  | true    |
      | 8944500102198304826  | false   |