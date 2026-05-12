import java.util.List;

public class EvaluationManager
{
	private double average;
	private String decision;
	
	public EvaluationManager()
	{
		this.average = 0.0;
		this.decision = "";
	}
	
	// calculate the average score
	public double calculateAverage(List<Reviewer> reviewers)
	{
		System.out.println("Calculating average...");
		double total = 0;
		for(int i = 0; i < reviewers.size(); i++)
		{
			total += reviewers.get(i).getScore();
		}
		average = total / reviewers.size();
		System.out.println("Average score: " + average);
		return average;
	}
	
	// check if reviewers agree
	public boolean checkConsensus(List<Reviewer> reviewers)
	{
		System.out.println("Checking consensus...");
		int max = reviewers.get(0).getScore();
		int min = reviewers.get(0).getScore();
		for(int i = 1; i < reviewers.size(); i++)
		{
			if(reviewers.get(i).getScore() > max)
			{
				max = reviewers.get(i).getScore();
			}
			if(reviewers.get(i).getScore() < min)
			{
				min = reviewers.get(i).getScore();
			}
		}
		boolean consensus = (max - min) <= 20;
		System.out.println("Consensus reached: " + consensus);
		return consensus;
	}
	
	// apply rules and make a decision
	public String applyRules(double average, boolean consensus)
	{
		System.out.println("Applying rules...");
		if(average >= 75 && consensus)
		{
			decision = "ACCEPTED";
		}
		else if(average < 50)
		{
			decision = "REJECTED";
		}
		else
		{
			decision = "REVISION";
		}
		System.out.println("Decision: " + decision);
		return decision;
	}
	
	public double getAverage()
	{
		return average;
	}
	
	public String getDecision()
	{
		return decision;
	}
	
	public void setAverage(double average)
	{
		this.average = average;
	}
	
	public void setDecision(String decision)
	{
		this.decision = decision;
	}
}