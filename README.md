# Coronariac (under development, Version 0.6 )
Coronariac is an emulator of CARDIAC but, what is CARDIAC?
Cardiac is a paper computer.Yes! its an actual computer made of paper! , but, how does a paper computer works? well its actually powered by you, but this doesnt make it less interesting...
It was designed by bell labs to teach how computers work back in the 60's, this project is an emulator of this computer developed in java.

At the moment, the emulator is capable of:
  
  Program the memory in the memory sheet
  
  Save the memory into a file to your pc

  Usage of all opcodes, but can not show the i/o contents, it has to be shown in the java console, planning for next version completting all i/0 functions.
  Right now, the input is emulated and contains the numers 3 and 8

  Here is a simple adding program from official Cardiac's mannual:
  
  0 034 [load the first number from imput and store it in the memory adress 34]
  
  1 035 [load the second number from imput and store it in the memory adress 35]
  
  1 134 [clear the accumulator and load the content of the adress 34 as the first numer to add]
  
  3 235 [add the contents of the adress 35 as the second number and add it]
  
  4 636 [the result of the adding will be stored in the memory adress 36]
  
  5 536 [the contents of the memory adress 36 will now be shown in the output]
  
  6 900 [stops the machine]

  Write this in coronariac's memory sheet and press [program] button, then step the machine using the [step] for execute every cycle of the adding program

