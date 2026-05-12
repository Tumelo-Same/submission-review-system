import java.util.List;

public class SubmissionController
{
	private Validator validator;
	private Database database;
	private ReviewerManager reviewerManager;
	private EvaluationManager evaluationManager;
	
	public SubmissionController()
	{
		this.validator = new Validator();
		this.database = new Database();
		this.reviewerManager = new ReviewerManager(database);
		this.evaluationManager = new EvaluationManager();
	}
	
	// submit and hand off
	public void submit(String data)
	{
		System.out.println("Submitting research output...");
		
		// validate format
		boolean isValid = validator.validateFormat(data);
		
		if(!isValid)
		{
			System.out.println("Returning error to researcher.");
			return;
		}
		
		// save submission
		database.saveSubmission(data);
		
		// get filtered reviewers
		List<Reviewer> filteredReviewers = reviewerManager.getFilteredReviewers();
		
		// hand off to evaluation manager
		evaluationManager.startEvaluation(filteredReviewers, database);
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
}