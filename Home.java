//Super market application
import java.util.*;
import credentials.*;
public class Home 
{
   
	public static void main(String args[])  throws Exception
	{
		int choice,n;
		System.out.format("%50s","FRESH MART");
		System.out.println();
		System.out.format("%60s","===============================");
		System.out.println();
		System.out.format("%50s","Loading...");
		System.out.println();
		
		Thread.sleep(500);
		boolean answer;
		do
		{
			Scanner sc=new Scanner(System.in);
			
			System.out.println("1.Log in as Admin");
			System.out.println("2.Log in as User");
			System.out.println("3.Exit");
			System.out.println("enter your choice");
			choice=sc.nextInt();									//getting user's choice
			sc.nextLine();
			switch(choice)
			{
				case 1:System.out.format("%50s","ADMIN PAGE");
					   System.out.println();
					   String adminName,adminPassword;
					   System.out.println("enter Admin name:");
					   adminName=sc.nextLine();
					   System.out.println("enter password");
					   adminPassword = String.valueOf(System.console().readPassword());
					   Admin admin=new Admin(adminName,adminPassword);
					   answer=admin.checkDetails();
					   if(answer==true)
					   {
						   admin.access();
					   }
					   break;
				case 2:System.out.format("%50s","USER PAGE");
					   System.out.println();
					   String userName,userPassword;
					   while(true)
					   {
						   System.out.println("1.Sign in");
						   System.out.println("2.Sign Up");
						   System.out.println("3.Back");
						   System.out.println("enter your choice");
						   n=sc.nextInt();
						   sc.nextLine();
						   if(n==1)
							{
								System.out.format("%50s","SIGN IN");
								System.out.println();
								System.out.println("enter user name:");
								userName=sc.nextLine();
								System.out.println("enter password");
								userPassword=sc.nextLine();
								User user=new User(userName,userPassword);
								answer=user.checkDetails();
								if(answer==true)
								{
									user.access();
								}
								
							}
							else if(n==2)
							{
								System.out.format("%50s","SIGN UP");
								System.out.println();
								User.createAccount();
								break;
							}
							else if(n==3)
							{
								break;
							}
					   }
					   break;
					   
				case 3:System.out.format("%50s","EXIT");
					   System.out.println();
					   System.out.println("Thank you for using our Application...Please visit again!!!");
					   break;
					   
			}
			
		}while(choice!=3);
	}
}