# Skunk-Game
A Java implementation of a customized Skunk dice game, designed with an engaging risk-and-reward mechanic. Players must choose wisely—stay safe, or risk it all for more points!
# **Game Highlights**
  Multiplayer support with dynamic decision-making
  Interactive turn structure where all players decide to "stay" or "risk"
  Real-time dice roll simulation
  Strategic elimination and scoring system
  Clean and modular Java code using OOP principles
  
# **Custom Rules**
  At the start of each round, all players choose to either stay or risk.
  Only players who choose "risk" will see the outcome of the dice roll.
  Two dice are rolled:
    If a single 1 appears → all standing players lose their points earned in this round.
    If double 1s appear → all standing players lose all points accumulated in the game.  
  The process repeats after each roll, asking remaining standing players whether to continue.
  A round ends when no players are left standing.
