////ADMIN PAGE
package credentials;
import java.util.*;
import java.io.*;


class Items implements Serializable
{
	String productId,name;
	int quantity,price;
	Items(String productId,String name,int quantity,int price)
	{
		this.productId=productId;
		this.name=name;
		this.quantity=quantity;
		this.price=price;
	}
}
public class Admin 
{
	Admin admin;
	
	String adminName,adminPassword,s;
	Scanner sc=new Scanner(System.in);
		
	public Admin() 
	{
		;		
	}
	public Admin(String adminName,String adminPassword)
	{
		this.adminName=adminName;
		this.adminPassword=adminPassword;
	}
	void addCategory() throws Exception
	{
		FileWriter fr=new FileWriter("D:/Courses/zoho incubation/Java/Mini_Application/task1/credentials/products/Category.txt",true);   
		String s;
		System.out.println("enter the category you like to add:");
		s=sc.nextLine();
		fr.write(s);
		fr.write("\n");
		fr.close();  
		System.out.println("Category added Successfully");
							
	}
	void addStock() throws Exception
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
			ArrayList<Items> listOfItems=new ArrayList<>();
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
		
			while(in.available()!=0)
			{
				s1=(Items)ois.readObject();
				listOfItems.add(s1);  
				//in.skip(4);				//while appending int is also appended...so we are skipping
				
			}
			in.close();
			
			String productId,name;
			int quantity,price;
			System.out.println("enter product Id");
			productId=sc.nextLine();
			System.out.println("enter product name");
			name=sc.nextLine();
			System.out.println("enter product quantity");
			quantity=sc.nextInt();
			System.out.println("enter product price");
			price=sc.nextInt();
			sc.nextLine();
			Items items=new Items(productId,name,quantity,price);
			int flag=0;
			for (Items list1: listOfItems)
			{
				if(list1.name.equals(name) && (list1.productId.equals(productId)))
				{
					list1.productId=productId;
					list1.quantity=list1.quantity+quantity;
					list1.price=price;
					flag=1;
					System.out.println("Changed Successfully....");
				}
				else if(list1.productId.equals(productId))
				{
					System.out.println("Product Id given is for another product...Try again!!!");
					flag=1;
					break;
					
				}
			}
	
			if(flag==0)
			{
				listOfItems.add(items);
				System.out.println("Added Successfully...");
			}
			FileOutputStream file=new FileOutputStream("D:/Courses/zoho incubation/Java/Mini_Application/task1/credentials/products/"+chosen+".txt");		//Serialization
			ObjectOutputStream out=new ObjectOutputStream(file);
			for (Items list1: listOfItems)
			{
				out.writeObject(list1);
				
			}
			
			out.close();
			file.close();
			int n2;
				System.out.println("1.Continue");
				System.out.println("2.Go back");
				n2=sc.nextInt();
				sc.nextLine();
				if(n2==2)
				{
					break;
				}
			
		}
	}
			
			
	void printCategory() throws Exception
	{
		FileReader fr=new FileReader("D:/Courses/zoho incubation/Java/Mini_Application/task1/credentials/products/Category.txt");    
        BufferedReader br=new BufferedReader(fr);    
		System.out.println("The available catgories are:");
		String read;
		int count=1;
		while((read=br.readLine())!=null)
		{
			System.out.println(count+"."+read);
			count++;
		}	
		br.close(); 
		fr.close();   
	}
	void printStock() throws Exception
	{
		int select;
		String stock;
		ArrayList<String> category=new ArrayList<>();
		FileReader fr=new FileReader("D:/Courses/zoho incubation/Java/Mini_Application/task1/credentials/products/Category.txt");    
		BufferedReader br=new BufferedReader(fr);  
		while((s=br.readLine())!=null)
		{
			category.add(s);
		}
		br.close();
		fr.close();
		while(true)
		{
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
		
			stock=category.get(select-1);
			FileInputStream in=new FileInputStream("D:/Courses/zoho incubation/Java/Mini_Application/task1/credentials/products/"+stock+".txt");				//deserialize
			ObjectInputStream ois=null;
			
			try{
			ois=new ObjectInputStream(in);  
			}
			catch(EOFException e)
			{
				;
			}
			
			Items s1;
			System.out.printf("%20s%20s%20s%20s","Product ID","Name","Quantity","Price\n"); 
			System.out.format("--------------------------------------------------------------------------------------------------------------------\n");  

			while(in.available()!=0)
			{
				s1=(Items)ois.readObject();
				System.out.format("%20s%20s%20d%20d\n" ,s1.productId,s1.name,s1.quantity,s1.price);  

				//in.skip(4);											//while appending int is also appended...so we are skipping
			}
			
			in.close();
				int n2;
				System.out.println("1.Continue");
				System.out.println("2.Go back");
				n2=sc.nextInt();
				sc.nextLine();
				if(n2==2)
				{
					break;
				}
		
		
		}
		
		
	}
	void purchaseDetails() throws Exception
	{
		FileReader fr1=new FileReader("D:/Courses/zoho incubation/Java/Mini_Application/task1/credentials/purchase.txt");    
        BufferedReader br1=new BufferedReader(fr1);  
		while((s=br1.readLine())!=null)
		{
			System.out.println(s);
		}
		br1.close();
		fr1.close();
	}
	public void access() throws Exception
	{
		int choice;
		do
		{
			System.out.println("\nEnter your choice:");
			System.out.println("1.ADD CATEGORY");
			System.out.println("2.ADD STOCK");
			System.out.println("3.PRINT CATEGORIES");
			System.out.println("4.PRINT STOCK");
			System.out.println("5.PRINT CUSTOMER PURCHASE DETAILS");
			System.out.println("6.LOG OUT");
			System.out.println("enter your choice");
			choice=sc.nextInt();
			sc.nextLine();
			switch(choice)
			{
				case 1:System.out.format("%50s","ADD CATEGORY");
					   System.out.println();
					   admin.addCategory();
					   break;
				case 2:System.out.format("%50s","ADD STOCK");
					   System.out.println();
					   admin.addStock();
					   break;
				case 3:System.out.format("%50s","PRINT CATEGORY");
					   System.out.println();
					   admin.printCategory();
					   break;
				case 4:System.out.format("%50s","PRINT STOCK");
					   System.out.println();
					   admin.printStock();
					   break;
				case 5:System.out.format("%50s","PRINT CUSTOMER PURCHASE DETAILS");
					   System.out.println();
					   admin.purchaseDetails();
					   break;
			    case 6:System.out.format("%50s","Successfully Logged out");
					   System.out.println();
					   break;

						
			}
		}while(choice!=6);
	}
	public boolean checkDetails() throws Exception
	{
		admin=new Admin();
		FileReader fr=new FileReader("D:/Courses/zoho incubation/Java/Mini_Application/task1/credentials/Admin_Credentials.txt");    
        BufferedReader br=new BufferedReader(fr);    
        String s;    
		ArrayList<String> list1=new ArrayList<String>();
		ArrayList<String> list2=new ArrayList<String>();
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
		
		for(int i=0;i<list2.size();i++)
		{
			if((list1.get(i).compareTo(adminName))==0)
			{
				if((list2.get(i).compareTo(adminPassword))==0)
				{
					System.out.println("Admin exists");
					return true;
				}
				else
				{
					System.out.println("Password is wrong");
					br.close(); 
					fr.close();    
					return false;
				}
			}
		}
		System.out.println("Admin doesn't exists");
		br.close(); 
		fr.close();    
		return false;
	}
}