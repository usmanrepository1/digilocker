package com.simplilearn.lockme.application;

import java.io.File;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.Scanner;

import com.simplilearn.lockme.model.UserCredentials;
import com.simplilearn.lockme.model.Users;

public class Authentication {
	

		//input data
	 	private static Scanner keyboard;
	 	private static Scanner input;
	 	private static Scanner lockerInput;
	 	//output data 
	 	private static PrintWriter output;
	 	private static PrintWriter lockerOutput;
	 	//model to store data.
	 	private static Users users;
	 	private static UserCredentials userCredentials;
	
	public static void main(String[] args) throws IOException, InterruptedException {
		
		initApp();
 		welcomeScreen();
 		signInOptions();
		
	}
	
	
	public static void initApp() throws IOException {

 		File  dbFile = new File("database1.txt");
 		dbFile.createNewFile();
 		File  lockerFile = new File("locker-file2.txt");
 		lockerFile.createNewFile();
 		
 		try {
 			//read data from db file
 			input = new Scanner(dbFile);

 			//read data from locker file
 			lockerInput = new Scanner(lockerFile);

 			//read data from keyboard
 			keyboard = new Scanner(System.in);

 			//output 
 			output = new PrintWriter( new FileWriter(dbFile,true));
 			lockerOutput = new PrintWriter( new FileWriter(lockerFile,true));

 			users = new Users();
 			userCredentials  = new UserCredentials();


 		} catch (IOException e) {
 			System.out.println("==============================");
 			System.out.println("404 : File Not Found ");
 			System.out.println("==============================");
			System.out.println("Enter a valid Response");
 			System.out.println("==============================");
			System.out.println("RELOGIN");
			System.out.println("===================");
 		}

 	}
		
		
		public static void signInOptions() throws InterruptedException, IOException {
			
		
			
	 		System.out.println("1 . Registration ");
	 		System.out.println("2 . Login ");
	 		System.out.print("Select Action required :");
	 		try {
	 			
	 		
	 		int option = keyboard.nextInt();
	
	 		switch(option) {
	 			case 1 : 
	 				registerUser();
	 				break;
	 			case 2 :
	 				loginUser();
	 				break;
	 			default :
	 				System.out.println("Please select 1 Or 2");
	 				signInOptions();
	 				break;
	 		}
	 		keyboard.close();
	 		input.close();
	 		}
	 		catch (Exception e) {
	 			System.out.println("==============================");
				System.out.println("Enter a valid Response");
	 			System.out.println("==============================");
				System.out.println("RELOGIN");
				System.out.println("===================");
	 			
	 			
	 			  
	 		}
	 		
			
			
	 	}

	 	public static void lockerOptions(String inpUsername) throws InterruptedException, IOException {
	 		
	 		
	 
	 		System.out.println("1 . FETCH ALL STORED CREDENTIALS ");
	 		System.out.println("2 . STORE CREDENTIALS ");
	 		System.out.println("3 . DELETE/MODIFY CREDENTIALS ");
	 		System.out.println("0 . LOGOUT");
	 		try {
	 			
	 		
	 		int option = keyboard.nextInt();
	 		
	 		
	 		switch(option) {
	 			case 0 :
	 				signInOptions();
	 			case 1 : 
	 				fetchCredentials(inpUsername);
	 				break;
	 			case 2 :
	 				storeCredentials(inpUsername);
	 				break;
	 			case 3 :
	 				DMdata();
	 				break;
	 			default :
	 				System.out.println("Please select 1 Or 2 Or 3");
	 				lockerOptions(inpUsername);
	 				break;
	 		}
	 		lockerInput.close();
	 		}
	 		catch (Exception e) {
	 			System.out.println("==============================");
				System.out.println("Enter a valid Response");
	 			System.out.println("==============================");
				System.out.println("RELOGIN");
				System.out.println("===================");
	 			

	 		}
	 		
	 	

	 		
	 	}

	 	public static void registerUser() throws InterruptedException, IOException {
	 		System.out.println("==========================================");
	 		System.out.println("*					*");
	 		System.out.println("*   WELCOME TO REGISTRATION PAGE	*");
	 		System.out.println("*					*");
	 		System.out.println("==========================================");

	 		System.out.println("Enter Username :");
	 		String username = keyboard.next();
	 		users.setUsername(username);

	 		System.out.println("Enter Password :");
	 		String password = keyboard.next();
	 		users.setPassword(password);

	 		output.println(users.getUsername());
	 		output.println(users.getPassword());

	 		System.out.println("==============================");
	 		System.out.println("User Registration Suscessful !");
	 		System.out.println("==============================");
			System.out.println("RELOGIN");
			System.out.println("===================");
	 		output.close();
	 		Thread.sleep(2000);
	 		signInOptions();

	 	}
	 	public static void loginUser() throws InterruptedException, IOException {
	 		System.out.println("==========================================");
	 		System.out.println("*					*");
	 		System.out.println("*   WELCOME TO LOGIN PAGE	 *");
	 		System.out.println("*					*");
	 		System.out.println("==========================================");
	 		System.out.println("Enter Username :");
	 		String inpUsername = keyboard.next();
	 		boolean found = false;
	 		while(input.hasNext() && !found) {
	 			if(input.next().equals(inpUsername)) {                  
	 				System.out.println("Enter Password :");				
	 				String inpPassword = keyboard.next();
	 				if(input.next().equals(inpPassword)) {
	 					System.out.println("Login Successful ! 200OK");
	 					found = true;
	 					lockerOptions(inpUsername);
	 					break;
	 				}
	 			}
	 		}
	 		if(!found) {
	 			System.out.println("======================================");
	 			System.out.println("User Not Found : Login Failure : 404");
	 			System.out.println("======================================");
				System.out.println("RELOGIN");
				System.out.println("===================");
	 			Thread.sleep(2000);
	 			signInOptions();
	 		}
	 		
	 		

	 	}

	 	public static void welcomeScreen() {
	 		System.out.println("==========================================");
	 		System.out.println("*					*");
	 		System.out.println("*   Welcome To LockMe.com		*");
	 		System.out.println("*   Your Personal Digital Locaker	*");
	 		System.out.println("*					*");
	 		System.out.println("==========================================");

	 	}
	 	//store credentails
	 	public static void storeCredentials(String loggedInUser) throws InterruptedException, IOException {
	 		
	 		int continueRunning = 1;
	 		
	 		while(continueRunning != 0){

	 		
	 		System.out.println("==========================================");
	 		System.out.println("*					*");
	 		System.out.println("*   WELCOME TO DIGITAL LOCKER STORE YOUR CREDS HERE	 *");
	 		System.out.println("*					*");
	 		System.out.println("==========================================");

	 		
	 		
	 		System.out.println("how many credentials you wanna store");
	 		try {
	 		int n = keyboard.nextInt();
	 		
	 		int i;
	 		for(i=1 ; i <= n ; i++) {
	 			
	 		userCredentials.setLoggedInUser(loggedInUser);
	 		
	 		System.out.println("Enter Site Name :");
	 		String siteName = keyboard.next();
	 		userCredentials.setSiteName(siteName);

	 		System.out.println("Enter Username :");
	 		String username = keyboard.next();
	 		userCredentials.setUsername(username);

	 		System.out.println("Enter Password :");
	 		String password = keyboard.next();
	 		userCredentials.setPassword(password);

	 		lockerOutput.println(userCredentials.getLoggedInUser());
	 		lockerOutput.println(userCredentials.getSiteName());
	 		lockerOutput.println(userCredentials.getUsername());
	 		lockerOutput.println(userCredentials.getPassword());

	 		System.out.println("YOUR CREDS ARE STORED AND SECURED!");
	 		
	 		}
	 		
	 		lockerOutput.close();	
	 		System.out.println("Would you like to rerun this program? 1=yes, 0=no");
 			
			  continueRunning = keyboard.nextInt();
			  
	 		}
			 
	 		
	 		
	 		catch (Exception e) {
	 			System.out.println("===================");
	 			System.out.println("Enter a valid Response");
	 			System.out.println("===================");
				System.out.println("RELOGIN");
				System.out.println("===================");
				
	 			

	 		}
	 		
	 		
	 		}

	 			
	 	}

	 	//fetch credentials
	 	public static void fetchCredentials(String inpUsername) throws InterruptedException, IOException {
	 		System.out.println("==========================================");
	 		System.out.println("*					*");
	 		System.out.println("*   WELCOME TO DIGITAL LOCKER 	 *");
	 		System.out.println("*   YOUR CREDS ARE 	 *");
	 		System.out.println("*					*");
	 		System.out.println("==========================================");
	 		System.out.println(inpUsername);


	 		while(lockerInput.hasNext()) {
	 //			System.out.println(lockerInput.hasNext());
	 			if(lockerInput.next().equals(inpUsername)) {
	 				System.out.println("Site Name: "+lockerInput.next());
	 				System.out.println("User Name: "+lockerInput.next());
	 				System.out.println("User Password: "+lockerInput.next());
	 			}
	 		}
	 		System.out.println("===================");
			System.out.println("RELOGIN");
			System.out.println("===================");
			
	 		System.exit(0); // not needed
	 		
	 		
	 		
 			

	 	}
	 	
	 	
		
		public static void DMdata() throws IOException, InterruptedException {
			
			int continueRunning = 1;
			
			while(continueRunning != 0){
				
				System.out.println("==========================================");
		 		System.out.println("*					*");
		 		System.out.println("*      WELCOME TO DELETE/UPDATE PAGE     *");
		 		System.out.println("*					*");
		 		System.out.println("==========================================");

			
			//Instantiating the File class
		      String filePath = "/Users/the/eclipse-workspace/digilocker/locker-file2.txt";
		      //Instantiating the Scanner class to read the file
		      Scanner sc = new Scanner(new File(filePath));
		      //instantiating the StringBuffer class
		      StringBuffer buffer = new StringBuffer();
		      //Reading lines of the file and appending them to StringBuffer
		      while (sc.hasNextLine()) {
		         buffer.append(sc.nextLine()+System.lineSeparator());
		      }
		      String fileContents = buffer.toString();
		      //System.out.println("Contents of the file: "+fileContents);
		      //closing the Scanner object
		      sc.close();
		      String oldLine;
		      String newLine;
		      int choice;
		      System.out.println("What do you want to edit?\n 1. username \n 2. password");
		      choice = keyboard.nextInt();
		      if(choice==1) {
		      System.out.println("Enter the username to be changed");
		      oldLine = keyboard.next();
		      System.out.println("Enter new username");
		      newLine = keyboard.next();
		      //Replacing the old line with new line
		      fileContents = fileContents.replaceAll(oldLine, newLine);
		      }
		      if(choice==2) {
		      System.out.println("Enter the password to be changed");
		      oldLine = keyboard.next();
		      System.out.println("Enter new password");
		      newLine = keyboard.next();
		      //Replacing the old line with new line
		      fileContents = fileContents.replaceAll(oldLine, newLine);
		      }
		      else {
		    	  System.out.println("Select 1 or 2");
		    	  DMdata();
		      }
		      
		      //instantiating the FileWriter class
		      FileWriter writer = new FileWriter(filePath);
		      System.out.println("");
		      //System.out.println("new data: "+fileContents);
		      writer.append(fileContents);
		      writer.flush();
			
		      System.out.println("Would you like to rerun this program? 1=yes, 2=no");
		      
		        continueRunning = keyboard.nextInt();

		      
			}
			
			System.out.println("===================");
			System.out.println("RELOGIN");
			System.out.println("===================");
			
			
			System.exit(0); // not needed

		}




	 }
	 	