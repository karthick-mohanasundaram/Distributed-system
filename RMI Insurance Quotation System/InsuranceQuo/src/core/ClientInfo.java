package core;

import java.io.Serializable;

/**
 * Interface to define the state to be stored in ClientInfo objects
 */
public interface ClientInfo  extends Serializable 
{
	public static final char MALE				= 'M';
	public static final char FEMALE				= 'F';
	
	public String getName();
	public char getSex();
	public int getAge();
	public int getPoints();
	public int getNoClaims();
	public String getLicenceNumber();
	
}
