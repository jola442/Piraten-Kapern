Feature: Part 1 of the acceptance tests
  Scenario Outline: A player scores their first dice roll
    Given a player wishes to roll dice only once for their turn
    When the player draws a <fortune_card> and rolls <roll>
    Then the player scores <score> for this round
    Examples:
      |row|fortune_card|score|roll                                                          |
      |45 |COIN        |0    |SKULL, SKULL, SKULL, SWORD, SWORD, SWORD, SWORD, SWORD        |
      |52 |CAPTAIN     |800  |MONKEY, MONKEY, PARROT,PARROT, DIAMOND, DIAMOND, COIN, COIN   |
      |53 |COIN        |300  |MONKEY, MONKEY, SKULL,SKULL, SWORD, SWORD, PARROT, PARROT     |
      |54 |COIN        |300  |MONKEY, MONKEY, MONKEY, SWORD, SWORD, SWORD, SKULL, SKULL     |
      |55 |COIN        |500  |DIAMOND, DIAMOND, DIAMOND, SKULL, SKULL, MONKEY, SWORD, PARROT|
      |56 |COIN        |700  |COIN, COIN, COIN, COIN, SKULL, SKULL, SWORD, SWORD            |
      |62 |COIN        |1100 |MONKEY, MONKEY, MONKEY, MONKEY, MONKEY, MONKEY, SKULL, SKULL  |
      |63 |COIN        |2100 |PARROT, PARROT, PARROT, PARROT, PARROT, PARROT, PARROT, SKULL |
      |64 |COIN        |5400 |COIN, COIN, COIN, COIN, COIN, COIN, COIN, COIN                |
      |65 |DIAMOND     |5400 |COIN, COIN, COIN, COIN, COIN, COIN, COIN, COIN                |
      |66 |CAPTAIN     |9000 |SWORD, SWORD, SWORD, SWORD, SWORD, SWORD, SWORD, SWORD        |
      |67 |COIN        |4600 |MONKEY, MONKEY, MONKEY, MONKEY, MONKEY, MONKEY, SWORD, SWORD  |
      |68 |DIAMOND     |400  |MONKEY, MONKEY, SKULL, SKULL, SWORD, SWORD, PARROT, PARROT    |
      |72 |COIN        |600  |MONKEY, MONKEY, MONKEY, MONKEY, COIN, COIN, SKULL, SKULL      |



