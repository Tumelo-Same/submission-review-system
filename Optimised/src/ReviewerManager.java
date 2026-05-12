import java.util.ArrayList;
import java.util.List;

public class ReviewerManager
{
	private List<Reviewer> filteredReviewers;
	private Database database;
	
	public ReviewerManager(Database database)
	{
		this.database = database;
		this.filteredReviewers = new ArrayList<>();
	}
	
	// fetch, filter and return ready reviewers
	public List<Reviewer> getFilteredReviewers()
	{
		List<Reviewer> reviewers = fetchReviewers();
		reviewers = filterConflicts(reviewers);
		filteredReviewers = checkWorkload(reviewers);
		System.out.println("Filtered reviewers ready.");
		return filteredReviewers;
	}
	
	// fetch reviewers directly from database
	private List<Reviewer> fetchReviewers()
	{
		System.out.println("Fetching reviewers...");
		return database.getAvailableReviewers();
	}
	
	// filter out reviewers with conflicts
	private List<Reviewer> filterConflicts(List<Reviewer> reviewerList)
	{
		System.out.println("Filtering conflicts...");
		List<Reviewer> noConflicts = new ArrayList<>();
		for(int i = 0; i < reviewerList.size(); i++)
		{
			if(!reviewerList.get(i).getHasConflict())
			{
				noConflicts.add(reviewerList.get(i));
			}
		}
		return noConflicts;
	}
	
	// filter out overloaded reviewers
	private List<Reviewer> checkWorkload(List<Reviewer> reviewerList)
	{
		System.out.println("Checking workload...");
		List<Reviewer> available = new ArrayList<>();
		for(int i = 0; i < reviewerList.size(); i++)
		{
			if(reviewerList.get(i).getWorkload() < 3)
			{
				available.add(reviewerList.get(i));
			}
		}
		return available;
	}
	
	public List<Reviewer> getFilteredReviewers(Database db)
	{
		return filteredReviewers;
	}
}