public class Validator
{
	private boolean isValid;
	
	public Validator()
	{
		this.isValid = false;
	}
	
	// validate the submission format
	public boolean validateFormat(String data)
	{
		if(data != null && !data.isEmpty())
		{
			isValid = true;
			System.out.println("Validating format...");
			System.out.println("Format is valid.");
			return true;
		}
		else
		{
			isValid = false;
			System.out.println("Validating format...");
			System.out.println("Format is invalid.");
			return false;
		}
	}
	
	public boolean getIsValid()
	{
		return isValid;
	}
	
	public void setIsValid(boolean isValid)
	{
		this.isValid = isValid;
	}
}