			COMP30220 Distributed Systems Systems Practical
				Lab 2 RMI Based Distribution


Problem 1: Distributing the Solution.

**************************************************************************************************************************

The Solution of problem 1 (a & b)
File name: Sampleins
	
	a) In this file, I have registered three quotation services remote object in main.java
	b) In the same file, I have made LocalBrokerService.java(RMIBrokerService) to use RMI.


**************************************************************************************************************************


The Solution of problem 1 (c)
File name: RMI_S_C
	
	c) In the RMI_S_C file, I have created the program, each quotation service as server and
	   main as client.
	
	Execution procedure:
		Step 1: Run AFQService, DDQService and GPQService on seperate JVM in any order.
		Step 2: Run main.java.	
		Output: For each user, AFQservice, DDQService and GPQService generate quotation seperately.


*****************************************************************************************************************************


Problem 2(a): Adding the vetting service
File name: individualservice
	
	a) I have implemented a vetting service in class RMIVettingService which has a database which
	   maintains the penality point for each driver's license number.
	    
           1. AFQService.java file acts as a server in which Vetting service has been binded to registry.
	   2. AFQServiceTest.java file lookup for vetting service to check whether driver input penality point
	      matches with point in RMIVettingService database. 
	   3. if penality point of driver matches with point in database, qutotations are given else we display
	      the "Mismatched penality point" with licence number of driver.
	
	Execution Procedure:
		Step 1: Run any Quotation service first followed by its Quotation service test
		Step 2: example run AFQService followed by AFQServiceTest


***************************************************************************************************************************

Problem 2(b)
File Name: InsuranceQuo
	1. In Main.java file, we are registering three quotation service object and vetting service object.
	2. Main.java file acts as server and LocalBrokerService.java as client.
	3. In LocalBrokerService.java, I am calling vetting service for each driver, which checks whether
	   driver entered penality point matches with database in RMIVettingService.java
	4. If there is a match, we provide quotation. If not, we display the "Mismatched penality point" with licence number of driver.



 
