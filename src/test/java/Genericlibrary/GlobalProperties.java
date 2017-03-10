package Genericlibrary;

//This class will handle setting and getting global properties
public class GlobalProperties {

	//List of Global Level Properties to be used at runtime
	static String GP_PID;
	
	
	//Method to set a global variable
	public void setVariable(String propertyName, String propertyValue)
    {
		switch (propertyName){
		case "PID":
			GP_PID = propertyValue;
		}
    }
	
	//Method to retrieve a global variable
	public String getVariable(String propertyName)
    {
		switch (propertyName){
		case "PID":
			return GP_PID;
		}
		return null;
    }
}