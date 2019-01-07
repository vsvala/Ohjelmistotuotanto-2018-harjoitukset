Feature: A new user account can be created if a proper unused username and password are given

    Scenario: creation is successful with valid username and password
        Given command new user is selected
        When  valid username  "eero"  and password "salainen1" are created
        Then  system will respond with "new user registered"
    
    Scenario: creation fails with already taken username and valid password
        Given command new user is selected
        When  username already taken "pekka" and valid password "moikkelis" are entered
        Then  system will respond with "new user not registered"


    Scenario: creation fails with too short username and valid password
        Given command new user is selected
        When  username "p" and valid password "moikkelis1" are entered
        Then  system will respond with "new user not registered"

    Scenario: creation fails with valid username and too short password
        Given command new user is selected
        When  username "Mina" and too short password "m" are entered
        Then  system will respond with "new user not registered"

    Scenario: creation fails with valid username and password enough long but consisting of only letters
        Given command new user is selected
        When  username "Sina" and  "moikkelis" are entered
        Then  system will respond with "new user not registered"

    Scenario: can login with successfully generated account
        Given user "eero" with password "salainen1" is created
        And   command login is selected
        When  user "eero" and  password "salainen1" are entered
        Then  system will respond with "logged in"

