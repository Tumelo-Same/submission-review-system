public class Reviewer
{
	private String name;
	private int score;
	private boolean hasConflict;
	private int workload;
	
	public Reviewer(String name)
	{
		this(name, 0, false, 0);
	}
	
	public Reviewer(String name, int score, boolean hasConflict, int workload)
	{
		setName(name);
		setScore(score);
		setHasConflict(hasConflict);
		setWorkload(workload);
	}
	
	// reviewer submits their score
	public int submitScore()
	{
		score = (int)(Math.random() * 40) + 60;
		System.out.println("Reviewer " + name + " submitting score: " + score);
		return score;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setScore(int score)
	{
		this.score = score;
	}
	
	public void setHasConflict(boolean hasConflict)
	{
		this.hasConflict = hasConflict;
	}
	
	public void setWorkload(int workload)
	{
		this.workload = workload;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getScore()
	{
		return score;
	}
	
	public boolean getHasConflict()
	{
		return hasConflict;
	}
	
	public int getWorkload()
	{
		return workload;
	}
	
	public String toString()
	{
		return "Reviewer: " + name + " | Score: " + score;
	}
}