package core;

/**
 * Data Beans class that contains only data
 */
public class ClientInfo 
{
	public static final char MALE				= 'M';
	public static final char FEMALE				= 'F';
	
	private String name;
	private char sex;
	private int age;
	private int points;
	private int noClaims;
	private String licenceNumber;
	
	public ClientInfo()
	{
	}
	
	//constructor with arguments is used for initialising a newly created Clientinfo object.
	public ClientInfo(String name, char sex, int age,int points,int noClaims, String licenceNumber)
	{
		this.name=name;
		this.age=age;
		this.sex=sex;
		this.points=points;
		this.noClaims=noClaims;
		this.licenceNumber=licenceNumber;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setLicenceNumber(String licenceNumber)
	{
		this.licenceNumber = licenceNumber;
	}
	
	public String getLicenceNumber()
	{
		return licenceNumber;
	}
	
	public void setSex(char sex)
	{
		this.sex = sex;
	}
	
	public char getSex()
	{
		return sex;
	}
	
	public void setAge(int age)
	{
		this.age = age;
	}
	
	public int getAge()
	{
		return age;
	}
	
	public void setPoints(int points)
	{
		this.points = points;
	}
	
	public int getPoints()
	{
		return points;
	}
	
	public void setNoClaims(int claims)
	{
		this.noClaims = claims;
	}
	public int getNoClaims()
	{
		return noClaims;
	}
	
}
	
