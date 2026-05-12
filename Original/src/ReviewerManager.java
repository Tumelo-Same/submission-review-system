import java.util.ArrayList;
import java.util.List;

public class ReviewerManager
{
	private List<Reviewer> filteredReviewers;
	
	public ReviewerManager()
	{
		this.filteredReviewers = new ArrayList<>();
	}
	
	// fetch reviewers from database
	public List<Reviewer> fetchReviewers(Database database)
	{
		System.out.println("Fetching reviewers...");
		return database.getAvailableReviewers();
	}
	
	// filter out reviewers with conflicts
	public List<Reviewer> filterConflicts(List<Reviewer> reviewerList)
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
	
	// check workload and filter overloaded reviewers
	public List<Reviewer> checkWorkload(List<Reviewer> reviewerList)
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
	
	// get the final filtered reviewer list
	public List<Reviewer> getFilteredReviewers(Database database)
	{
		List<Reviewer> reviewers = fetchReviewers(database);
		reviewers = filterConflicts(reviewers);
		filteredReviewers = checkWorkload(reviewers);
		System.out.println("Filtered reviewers ready.");
		return filteredReviewers;
	}
	
	public List<Reviewer> getFilteredReviewers()
	{
		return filteredReviewers;
	}
}