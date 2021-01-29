# Synacor Challenge (Java)
Solutions for the Synacor challenge, written in Java. More info at https://challenge.synacor.com/  

## Progress
Found 4/8 codes. Virtual machine is finished and passes all self-checks.

## How To
1. clone the repo
2. import to your IDE as a maven project
3. run the virtual machine (constructor expects the binary file as input) OR run the pre-configured App class  

## Next steps
Add stepping for easier debugging  

## Features
You can run the machine using by passing the challenge binary to the VirtualMachine constructor and calling the run()  
method or use the pre-configured App class in the Client package.  
If you aren't using the App class, you'll need to implement saving/loading states yourself  
Using the App class allows you to save the state by simply typing "save" or "load" when an input command is encountered. Make sure to change the file path before you do this.  