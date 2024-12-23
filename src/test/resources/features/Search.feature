@Search @mobil
Feature:Search in mobilApp

  Background:
    Given click "Üye Olmadan Devam Et" button

     #Android-01
  Scenario: User should be able to search daily
    When search "Laptop"
    And click filter button
    And select filter "Bilgisayar, Donanım"
    And click filter apply button
    And sort "En Yüksek Fiyat"
    And select product index 10
    And click product Detail
    Then verify open product detail page