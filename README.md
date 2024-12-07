# Coronariac (under development, Version 0.3 now with 4 working OPcodes)
Coronariac is an emulator of CARDIAC but, what is CARDIAC?
Cardiac is a paper computer.Yes! its an actual computer made of paper! , but, how does a paper computer works? well its actually powered by you, but this doesnt make it less interesting...
It was designed by bell labs to teach how computers work back in the 60's, this project is an emulator of this computer developed in java.

At the moment, the emulator is capable of:
  
  Program the memory in the memory sheet
  
  Save the memory into a file to your pc

  Usage of the opcodes 1,2,3 and 6... since is not capable of negative numbers yet, opcode 3 is kinda useless... but is a beginning for the turing completeness

  Here is a simple program:
  
  01 112
  
  02 213
  
  03 614
  
  .
  
  .
  
  .

  12 99

  13 100

  This program will load the number stored in position 12 and 13 and add it, then will be stored in position 14.
  Since there is no memory sheet refresh, the best way to check this is saving the memory and open it with notepad. The position 14 will be 199

Next version adding negative numbers suport, refresh the memory sheet, adding more instructions
