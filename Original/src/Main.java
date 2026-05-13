import java.io.PrintStream;
import java.io.OutputStream;

public class Main
{
	public static void main(String[] args)
	{
		// run a normal submission first so the flow is visible
		System.out.println("=== Valid Submission ===");
		SubmissionController controller = new SubmissionController();
		controller.submit("Research paper on AI in South African elections");
		
		System.out.println();
		System.out.println("=== Invalid Submission ===");
		SubmissionController controller2 = new SubmissionController();
		controller2.submit("");
		
		System.out.println();
		System.out.println("=== Running Benchmark (1000 runs) ===");
		
		// now suppress output for the benchmark
		PrintStream original = System.out;
		System.setOut(new PrintStream(new OutputStream() {
			public void write(int b) {}
		}));
		
		// warm up
		SubmissionController warmup = new SubmissionController();
		warmup.submit("warmup data");
		
		int runs = 1000;
		long totalTime = 0;
		
		for(int i = 0; i < runs; i++)
		{
			SubmissionController c = new SubmissionController();
			long start = System.nanoTime();
			c.submit("Research paper on AI in South African elections");
			long end = System.nanoTime();
			totalTime += (end - start);
		}
		
		// restore output
		System.setOut(original);
		
		long averageTime = totalTime / runs;
		
		System.out.println("=== Baseline Benchmark Results ===");
		System.out.println("Total runs: " + runs);
		System.out.println("Total time: " + totalTime + " ns");
		System.out.println("Average time per run: " + averageTime + " ns");
		System.out.println("Average time in ms: " + averageTime / 1000000.0 + " ms");
	}
}