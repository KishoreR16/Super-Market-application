///USER PAGE
package credentials;
import java.util.*;
import java.io.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
interface Must
{
	public void buy() throws Exception;
	void cancel();
	void reduce();
}  
public class User implements Must
{
	User user;
	
	static ArrayList<Items> purchase;
	static ArrayList<Items> listOfItems;
	static ArrayList<String> list1=new ArrayList<String>();
	static ArrayList<String> list2=new ArrayList<String>();
	static String s; 
	static String userName,userPassword;
	static int n;
	static Scanner sc=new Scanner(System.in);
	public User() 
	{
		;		
	}
	public User(String userName,String userPassword)
	{
		this.userName=userName;
		this.userPassword=userPassword;
	}
	public static void createAccount()
	{
		try 
		{
			list1.clear();
			list2.clear();
			FileReader fr=new FileReader("D:/Courses/zoho incubation/Java/Mini_Application/task1/credentials/User_Credentials.txt");    
			BufferedReader br=new BufferedReader(fr);  
			int flag=0;
			while((s=br.readLine())!=null)
			{
				if(flag==0)
				{
					list1.add(s);
					flag=1;
				}
				else
				{
					flag=0;
					list2.add(s);
				}
			}	
			br.close(); 
			fr.close();   
            FileWriter writer = new FileWriter("D:/Courses/zoho incubation/Java/Mini_Application/task1/credentials/User_Credentials.txt",true);
			while(true)
			{
				System.out.println("enter user name:");
				userName=sc.nextLine();
				if(list1.contains(userName))
				{
					System.out.println("User name already exists....try another user name");
					System.out.println("1.Create Account");
					System.out.println("2.Back");
					System.out.println("enter your choice");
					n=sc.nextInt();
					sc.nextLine();
					if(n==2)
					{
						break;
					}
				}
				else
				{
					System.out.println("enter password:");
					userPassword=sc.nextLine();
					String regex = "^(?=.*[0-9])"+ "(?=.*[a-z])(?=.*[A-Z])"
                       + "(?=.*[@#$%^&+=])"+ "(?=\\S+$).{8,16}$";
					if(userPassword.matches(regex))
					{
						writer.write(userName);
						writer.write("\n");
						writer.write(userPassword);
						writer.write("\n");
						System.out.println("Successfully created the account");
						list1.add(userName);
						list2.add(userPassword);
						writer.close();
						break;
					}
					else
					{
						System.out.println("Password must contain One Uppercase,One Lowercase,One number and a special character $ or @ and must of length 8 to 16...try again!!!");
					}
					System.out.println("Do you want to go back->press 1...to continue->press 2");
					System.out.println("enter your choice");
					n=sc.nextInt();
					sc.nextLine();
					if(n==1)
					{
						break;
					}
				}
			}
        } 
		catch (IOException e) 
		{
            e.printStackTrace();
        }
	}
	public boolean checkDetails() throws Exception
	{
		user=new User();
		purchase=new ArrayList<Items>();
		listOfItems=new ArrayList<Items>();
		
		list1.clear();
		list2.clear();
		FileReader fr=new FileReader("D:/Courses/zoho incubation/Java/Mini_Application/task1/credentials/User_Credentials.txt");    
        BufferedReader br=new BufferedReader(fr);    
		int flag=0;
		while((s=br.readLine())!=null)
		{
			if(flag==0)
			{
				list1.add(s);
				flag=1;
			}
			else
			{
				flag=0;
				list2.add(s);
			}
		}	
		br.close(); 
		fr.close();   
		char choice;
		for(int i=0;i<list1.size();i++)
		{
			if((list1.get(i).compareTo(userName))==0)
			{
				if((list2.get(i).compareTo(userPassword))==0)
				{
					System.out.println("User exists");
					return true;
				}
				else
				{
					System.out.println("Password is wrong");
					System.out.println("1.forgot password");
					System.out.println("2.Go Back");
					n=sc.nextInt();
					sc.nextLine();
					if(n==1)
					{
						Random random=new Random();
						int otp = random.nextInt(1000);   
						FileWriter w = new FileWriter("D:/Courses/zoho incubation/Java/Mini_Application/task1/credentials/otp.txt");
						PrintWriter p=new PrintWriter(w);
						p.print(otp);
						p.close();
						w.close();
						int check;
						System.out.println("enter otp");
						check=sc.nextInt();
						sc.nextLine();
						if(check==otp)
						{
							while(true)
							{
								String regex = "^(?=.*[0-9])"+ "(?=.*[a-z])(?=.*[A-Z])"+ "(?=.*[@#$%^&+=])"+ "(?=\\S+$).{8,16}$";
								System.out.println("enter new password");
								userPassword=sc.nextLine();
								if(userPassword.matches(regex))
								{
									break;
								}
								else
								{
									System.out.println("Password must contain One Uppercase,One Lowercase,One number and a special character $ or @ and must of length 8 to 16...try again!!!");

								}
							}
							
							list2.set(i,userPassword);
							FileWriter fWriter = new FileWriter("D:/Courses/zoho incubation/Java/Mini_Application/task1/credentials/User_Credentials.txt");
							
							for(int j=0;j<list1.size();j++)
							{
								
									s=list1.get(j);
									fWriter.write(s);
									fWriter.write("\n");
								
								
									s=list2.get(j);
									fWriter.write(s);
									fWriter.write("\n");
								
							}
							fWriter.close();
							System.out.println("Successfully changed the password");
							
						}
						else
						{
							System.out.println("No longer valid...try again");
						}
					}
				}
				  
				return false;
			}
		}
		System.out.println("User doesn't exists");
		System.out.println("Do you want to register or create a new account");
		System.out.println("If YES press 'y'....If NO press 'n'....");
		choice=sc.next().charAt(0);
		if(choice=='y')
		{
			sc.nextLine();
			User.createAccount();
		}
		
		
		
		return false;
	}
	public void addToCart() throws Exception
	{
		String s,chosen;
		int select;
		ArrayList<String> category=new ArrayList<>();
		FileReader fr=new FileReader("D:/Courses/zoho incubation/Java/Mini_Application/task1/credentials/products/Category.txt");    
		BufferedReader br=new BufferedReader(fr);  
		while((s=br.readLine())!=null)
		{
			category.add(s);
			File file = new File("D:/Courses/zoho incubation/Java/Mini_Application/task1/credentials/products/"+s+".txt");
			boolean value = file.createNewFile();
		}
		br.close();
		fr.close();
		
		while(true)
		{
			
				int n2;
				System.out.println("1.Continue");
				System.out.println("2.Go back");
				n2=sc.nextInt();
				sc.nextLine();
				if(n2==2)
				{
					break;
				}
			System.out.println("The categories available are:");
			for(int i=0;i<category.size();i++)
			{
				System.out.println((i+1)+"."+category.get(i));
			}
			System.out.println();
			System.out.println("enter the category you like to select");
			select=sc.nextInt();
			sc.nextLine();
			if(select>=category.size()+1)
			{
				System.out.println("select a proper catogory");
				int n1;
				System.out.println("1.Continue");
				System.out.println("2.Go back");
				n1=sc.nextInt();
				sc.nextLine();
				if(n1==2)
				{
					break;
				}
				continue;
			}
				
			chosen=category.get(select-1);
			FileInputStream in=new FileInputStream("D:/Courses/zoho incubation/Java/Mini_Application/task1/credentials/products/"+chosen+".txt");				//deserialize
			ObjectInputStream ois=null;
			
			try{
			ois=new ObjectInputStream(in);  
			}
			catch(EOFException e)
			{
				;
			}
			// 
			Items s1;
			listOfItems.clear();
			while(in.available()!=0)
			{
				s1=(Items)ois.readObject();
				listOfItems.add(s1);  
				//in.skip(4);				//while appending int is also appended...so we are skipping
				
			}
			in.close();
			while(true)
			{
				System.out.println("The items available in this category are:");
				System.out.printf("%20s%20s%20s%20s","Product ID","Name","Quantity","Price\n"); 
				System.out.format("--------------------------------------------------------------------------------------------------------------------\n");
				for (Items list1: listOfItems)
				{
					System.out.format("%20s%20s%20d%20d\n" ,list1.productId,list1.name,list1.quantity,list1.price);  
				}
				int n;
				System.out.println("1.Continue");
				System.out.println("2.Go back");
				n=sc.nextInt();
				sc.nextLine();
				if(n==2)
				{
					break;
				}
				System.out.println("enter the product id you like to add to cart:");
				String cart;
				cart=sc.nextLine();
				for(Items l:listOfItems)
				{
					if(l.productId.equals(cart))
					{
						int getter;
						
						while(true)
						{
							System.out.println("enter the quantity:");
							getter=sc.nextInt();
							sc.nextLine();
							if(l.quantity<getter)
							{
								System.out.println("your request is higher than the stock...The available stock is "+l.quantity);
							}
							else
							{
								l.quantity=l.quantity-getter;
								Items items=new Items(l.productId,l.name,getter,l.price*getter);
								purchase.add(items);
								//purchase.get((purchase.size()-1)).quantity=getter;                         //getting recently added index reference and changing the value
								break;
							}
						}
						break;
					}
				}
				
				
				
			}
			
		}
	}
	public void buy() throws Exception
	{
		String phoneNo,name;
		int total=0;
		System.out.println("enter your name");
		name=sc.nextLine();
		System.out.println("enter your mobile number");
		phoneNo=sc.nextLine();
		System.out.println("purchased");
		System.out.println("Bill details");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		System.out.println("");
		System.out.println("NAME:"+name);
		System.out.println("MOBILE NUMBER:"+phoneNo);
		System.out.println("DATE & TIME:"+dtf.format(now));
		System.out.println("");
		System.out.printf("%20s%20s%20s%20s","Product ID","Name","Quantity","Price\n"); 
		System.out.format("--------------------------------------------------------------------------------------------------------------------\n");
		for (Items list1: purchase)
		{
			System.out.format("%20s%20s%20d%20d\n" ,list1.productId,list1.name,list1.quantity,list1.price); 
			total=total+list1.price;			
		}
		System.out.format("%60s%10d\n","TOTAL",total);
		ArrayList<String> category=new ArrayList<>();
		FileReader fr=new FileReader("D:/Courses/zoho incubation/Java/Mini_Application/task1/credentials/products/Category.txt");    
		BufferedReader br=new BufferedReader(fr);  
		while((s=br.readLine())!=null)
		{
			category.add(s);
			File file = new File("D:/Courses/zoho incubation/Java/Mini_Application/task1/credentials/products/"+s+".txt");
			boolean value = file.createNewFile();
		}
		br.close();
		fr.close();
		for(String s:category)
		{
			listOfItems.clear();
			FileInputStream in=new FileInputStream("D:/Courses/zoho incubation/Java/Mini_Application/task1/credentials/products/"+s+".txt");				//deserialize
			ObjectInputStream ois=null;
			try{ois=new ObjectInputStream(in);}
			catch(EOFException e)
			{;}
			// 
			Items s1;
			while(in.available()!=0)
			{
				s1=(Items)ois.readObject();
				listOfItems.add(s1);  
				
			}
			in.close();
			for(Items list1:listOfItems)
			{
				for(Items list2:purchase)
				{
					if(list1.name.equals(list2.name))
					{
						list1.quantity=list1.quantity-list2.quantity;
					}
				}
					
			}
			
			
			FileOutputStream file=new FileOutputStream("D:/Courses/zoho incubation/Java/Mini_Application/task1/credentials/products/"+s+".txt");		//Serialization
			ObjectOutputStream out=new ObjectOutputStream(file);
			for (Items list1: listOfItems)
			{
				out.writeObject(list1);
				
			}
			
			out.close();
			file.close();
		}
		
		//adding the history in purchase file 
		FileOutputStream fout=new FileOutputStream("D:/Courses/zoho incubation/Java/Mini_Application/task1/credentials/purchase.txt",true);    
		PrintStream out=new PrintStream(fout); 
		out.println("NAME:"+name);
		out.println("MOBILE NUMBER:"+phoneNo);
		out.println("DATE & TIME:"+dtf.format(now));
		out.println("");
		out.printf("%20s%20s%20s%20s\n","Product ID","Name","Quantity","Price\n"); 
		out.println("--------------------------------------------------------------------------------------------------------------------\n");
		for (Items list1: purchase)
		{
			out.format("%20s%20s%20d%20d\n" ,list1.productId,list1.name,list1.quantity,list1.price); 			
		}
		out.format("%60s%10d\n","TOTAL",total);
		out.println();
		out.close();
		fout.close();
		
		
	}
	public void cancel()
	{
		int total=0;
		System.out.println("The available items are:");
		System.out.println();
		System.out.printf("%20s%20s%20s%20s","Product ID","Name","Quantity","Price\n"); 
		System.out.format("--------------------------------------------------------------------------------------------------------------------\n");
		for (Items list1: purchase)
		{
			System.out.format("%20s%20s%20d%20d\n" ,list1.productId,list1.name,list1.quantity,list1.price); 
			total=total+list1.price;			
		}
		System.out.format("%60s%10d\n","TOTAL",total);
		String name;
		System.out.println("Enter the name of the product you would like to cancel:");
		name=sc.nextLine();
		int index=0;
		int removing=-1;
		for(Items list1:purchase)
		{
			if(list1.name.equals(name))
			{
				
				removing=index;
				break;
			}
			index++;
		}
		if(removing!=-1)
		{
			purchase.remove(index);
		}
	}
	public void reduce()
	{
		int total=0;
		System.out.println("The available items are:");
		System.out.println();
		System.out.printf("%20s%20s%20s%20s","Product ID","Name","Quantity","Price\n"); 
		System.out.format("--------------------------------------------------------------------------------------------------------------------\n");
		for (Items list1: purchase)
		{
			System.out.format("%20s%20s%20d%20d\n" ,list1.productId,list1.name,list1.quantity,list1.price); 
			total=total+list1.price;			
		}
		System.out.format("%60s%10d\n","TOTAL",total);
		String name;
		System.out.println("Enter the name of the product you would like to reduce:");
		name=sc.nextLine();
		for(Items list1:purchase)
		{
			if(list1.name.equals(name))
			{
				System.out.println("enter the quantity less than "+list1.quantity);
				int get1,price1;
				get1=sc.nextInt();
				sc.nextLine();
				price1=get1*(list1.price/list1.quantity);
				list1.quantity=list1.quantity-get1;
				list1.price=list1.price-price1;
				
				break;
			}
		}
	}
	public void display() throws Exception
	{
		int total=0;
		System.out.printf("%20s%20s%20s%20s","Product ID","Name","Quantity","Price\n"); 
		System.out.format("--------------------------------------------------------------------------------------------------------------------\n");
		for (Items list1: purchase)
		{
			System.out.format("%20s%20s%20d%20d\n" ,list1.productId,list1.name,list1.quantity,list1.price); 
			total=total+list1.price;			
		}
		System.out.format("%60s%10d\n","TOTAL",total);
		int end;
		do
		{
			System.out.println("1.Buy");
			System.out.println("2.Close");
			System.out.println("3.Cancel the items");
			System.out.println("4.Reduce the quantity");
			System.out.println("enter your choice:");
			end=sc.nextInt();
			sc.nextLine();
			switch(end)
			{
				case 1:System.out.printf("%50s\n","BUY");
					   buy();
					   break;
				case 2:
					   break;
				case 3:System.out.printf("%50s\n","CANCEL THE ITEMS");
					   cancel();
					   break;
				case 4:System.out.printf("%50s\n","REDUCE THE QUANTITY");
					   reduce();
					   break;
					   
			}			
		}while((end!=2)&&(end!=1));
		
		
	}
	public void access() throws Exception
	{
		int choice;
		do
		{
			System.out.println("\nEnter your choice:");
			System.out.println("1.ADD TO CART");
			System.out.println("2.DISPLAY");
			System.out.println("3.LOG OUT");
			System.out.println("enter your choice");
			choice=sc.nextInt();
			sc.nextLine();
			switch(choice)
			{
				case 1:System.out.format("%50s","ADD TO CART");
					   System.out.println();
					   user.addToCart();
					   break;
				case 2:System.out.format("%50s","DISPLAY");
					   System.out.println();
					   user.display();
					   break;
			    case 3:System.out.format("%50s","Successfully Logged out");
					   System.out.println();
					   break;

						
			}
		}while(choice!=3);
	}
}