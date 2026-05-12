import java.util.List;

public class SubmissionController
{
	private Validator validator;
	private Database database;
	private ReviewerManager reviewerManager;
	private EvaluationManager evaluationManager;
	private NotificationService notificationService;
	
	public SubmissionController()
	{
		this.validator = new Validator();
		this.database = new Database();
		this.reviewerManager = new ReviewerManager();
		this.evaluationManager = new EvaluationManager();
		this.notificationService = new NotificationService();
	}
	
	// submit the data and run the whole flow
	public void submit(String data)
	{
		System.out.println("Submitting research output...");
		
		// validate the format
		boolean isValid = validator.validateFormat(data);
		
		if(!isValid)
		{
			System.out.println("Returning error to researcher.");
			return;
		}
		
		// save the submission
		database.saveSubmission(data);
		
		// get available reviewers
		List<Reviewer> filteredReviewers = reviewerManager.getFilteredReviewers(database);
		
		// assign reviewers and collect scores
		System.out.println("Assigning reviewers...");
		for(int i = 0; i < filteredReviewers.size(); i++)
		{
			System.out.println("Assigning review to: " + filteredReviewers.get(i).getName());
			filteredReviewers.get(i).submitScore();
		}
		
		// start evaluation
		System.out.println("Starting evaluation...");
		double average = evaluationManager.calculateAverage(filteredReviewers);
		boolean consensus = evaluationManager.checkConsensus(filteredReviewers);
		String decision = evaluationManager.applyRules(average, consensus);
		
		// save scores to database
		for(int i = 0; i < filteredReviewers.size(); i++)
		{
			database.saveScore(filteredReviewers.get(i).getName(), filteredReviewers.get(i).getScore());
		}
		
		// send the right notification
		if(decision.equals("ACCEPTED"))
		{
			notificationService.notifyAcceptance();
		}
		else if(decision.equals("REJECTED"))
		{
			notificationService.notifyRejection();
		}
		else
		{
			notificationService.notifyRevision();
		}
		
		notificationService.sendNotification();
	}
	
	public Validator getValidator()
	{
		return validator;
	}
	
	public Database getDatabase()
	{
		return database;
	}
	
	public ReviewerManager getReviewerManager()
	{
		return reviewerManager;
	}
	
	public EvaluationManager getEvaluationManager()
	{
		return evaluationManager;
	}
	
	public NotificationService getNotificationService()
	{
		return notificationService;
	}
}