Hacker Rank Test Harness

This is a small project for working through Hacker Rank exercises using a real IDE instead of the Hacker Rank web ui.
This allows for better auto-complete and the ability to debug.

I use this with IntelliJ.

How I debug exercises through IntelliJ
1) In the tester set the DEBUG constant to true
2) Add Thread.sleep(...) line in the exercise main method to give myself time to attach a debugger
3) Run the main method in the tester
4) In the IntelliJ menu go to "Run"->"Attach to Process"
5) In the dialog that shows up select the process for the exercise
6) Make sure to have a breakpoint set in your exercise and enjoy debugging
