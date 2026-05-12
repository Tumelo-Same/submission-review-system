public class NotificationService
{
	private String notification;
	
	public NotificationService()
	{
		this.notification = "";
	}
	
	// notify acceptance
	public void notifyAcceptance()
	{
		notification = "ACCEPTED";
		System.out.println("Sending acceptance notification...");
	}
	
	// notify rejection
	public void notifyRejection()
	{
		notification = "REJECTED";
		System.out.println("Sending rejection notification...");
	}
	
	// notify revision
	public void notifyRevision()
	{
		notification = "REVISION REQUIRED";
		System.out.println("Sending revision notification...");
	}
	
	// send the notification to the researcher
	public void sendNotification()
	{
		System.out.println("Notification sent to researcher: " + notification);
	}
	
	public String getNotification()
	{
		return notification;
	}
	
	public void setNotification(String notification)
	{
		this.notification = notification;
	}
}