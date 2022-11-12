Feature: Part 3 of the acceptance tests
  Background:
    Given all players have joined
  Scenario: Row 132
    When round 1 begins
    And player 1 draws a Captain card and rolls 7 Swords and 1 Skull
    And player 1 scores 4000 points for this round
    And player 2 draws a One Skull card and rolls 7 Swords and 1 Skull
    And player 2 scores 2000 points for this round
    And player 3 draws a Coin card and rolls 3 Skulls and 5 Monkeys
    And player 3 scores 0 points for this round
    Then the game ends with player 1 as the winner with 4000 points, player 2 with 2000 points and player 3 with 0 points.

  Scenario: Row 140
    When round 1 begins
    And player 1 draws a Captain card and rolls 7 Swords and 1 Skull
    And player 1 scores 4000 points for this round
    And player 2 draws a Coin card and rolls 3 Skulls and 5 Monkeys
    And player 2 scores 0 points for this round
    And player 3 draws a Captain card and rolls 6 Skulls and 2 Parrots
    And player 3 chooses not to re-roll
    And player 3 scores 0 points for this round
    And player 1 and player 2 get a 1200 point deduction
    And player 1's score drops to 2800
    And player 2's score stays at 0
    And round 2 begins
    And player 1 draws a Coin card and rolls 4 Monkeys and 4 Parrots
    And player 1 chooses not to re-roll
    And player 1 scores 1000 points for this round
    And player 2 draws a Captain card and rolls 3 Skulls and 5 Monkeys
    Then the game ends with player 1 as the winner with 3800 points, player 2 with 0 points and player 3 with 0 points.

  Scenario: Row 145
    When round 1 begins
    And player 1 draws a Captain card and rolls 3 Skulls and 5 Monkeys
    And player 1 scores 0 points for this round
    And player 2 draws a Captain card and rolls 7 Swords and 1 Skull
    And player 2 scores 4000 points for this round
    And player 3 draws a Two Skulls card and rolls 1 Skull and 7 Swords
    And player 3 scores 0
    And player 1 draws a Captain card and rolls 8 Swords
    And player 1 scores 9000 points for this round
    Then the game ends with player 1 as the winner with 9000 points, player 2 with 4000 points and player 3 with 0 points


  Scenario: Row 150
    When round 1 begins
    And player 1 draws a Coin card and rolls 6 Swords and 2 Skulls
    And player 1 scores 1100 points for this round
    And player 2 draws a Sorceress card and rolls 7 Skulls and 1 Coin
    And player 2 re-rolls a Skull die and gets a Parrot die
    And player 2 re-rolls a Coin die and a Parrot die and gets 2 Skulls dice
    And player 2 scores 0 for this round
    And player 1 and player 3 get an 800 point deduction
    And player 1's score drops to 300
    And player 3's score stays at 0
    Then the game stops

