import java.util.List;

public class EvaluationManager
{
	private double average;
	private String decision;
	private NotificationService notificationService;
	
	public EvaluationManager()
	{
		this.average = 0.0;
		this.decision = "";
		this.notificationService = new NotificationService();
	}
	
	// run the full evaluation and notify
	public void startEvaluation(List<Reviewer> reviewers, Database database)
	{
		collectScores(reviewers, database);
		average = calculateAverage(reviewers);
		boolean consensus = checkConsensus(reviewers);
		decision = applyRules(average, consensus);
		sendNotification();
	}
	
	// collect scores from each reviewer
	private void collectScores(List<Reviewer> reviewers, Database database)
	{
		System.out.println("Starting evaluation...");
		for(int i = 0; i < reviewers.size(); i++)
		{
			reviewers.get(i).submitScore();
			database.saveScore(reviewers.get(i).getName(), reviewers.get(i).getScore());
		}
	}
	
	// calculate average score
	private double calculateAverage(List<Reviewer> reviewers)
	{
		System.out.println("Calculating average...");
		double total = 0;
		for(int i = 0; i < reviewers.size(); i++)
		{
			total += reviewers.get(i).getScore();
		}
		double avg = total / reviewers.size();
		System.out.println("Average score: " + avg);
		return avg;
	}
	
	// check if reviewers agree
	private boolean checkConsensus(List<Reviewer> reviewers)
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
	
	// apply rules from decision table
	private String applyRules(double average, boolean consensus)
	{
		System.out.println("Applying rules...");
		if(average >= 75 && consensus)
		{
			System.out.println("Decision: ACCEPTED");
			return "ACCEPTED";
		}
		else if(average < 50)
		{
			System.out.println("Decision: REJECTED");
			return "REJECTED";
		}
		else
		{
			System.out.println("Decision: REVISION");
			return "REVISION";
		}
	}
	
	// notify researcher directly
	private void sendNotification()
	{
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
	
	public double getAverage()
	{
		return average;
	}
	
	public String getDecision()
	{
		return decision;
	}
}