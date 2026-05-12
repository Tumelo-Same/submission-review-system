import java.util.ArrayList;
import java.util.List;

public class Database
{
	private List<String> submissions;
	private List<Reviewer> reviewers;
	
	public Database()
	{
		this.submissions = new ArrayList<>();
		this.reviewers = new ArrayList<>();
		loadReviewers();
	}
	
	// load some reviewers into memory
	private void loadReviewers()
	{
		reviewers.add(new Reviewer("Tumelo"));
		reviewers.add(new Reviewer("John"));
		reviewers.add(new Reviewer("Sarah"));
		reviewers.add(new Reviewer("Mike"));
	}
	
	// save the submission
	public String saveSubmission(String data)
	{
		submissions.add(data);
		System.out.println("Saving submission to database...");
		System.out.println("Submission saved. Confirmation received.");
		return "confirmation";
	}
	
	// fetch available reviewers
	public List<Reviewer> getAvailableReviewers()
	{
		System.out.println("Fetching available reviewers...");
		return reviewers;
	}
	
	// save a reviewer score
	public void saveScore(String reviewerName, int score)
	{
		System.out.println("Saving score for " + reviewerName + ": " + score);
	}
	
	public List<String> getSubmissions()
	{
		return submissions;
	}
}