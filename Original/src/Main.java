import java.io.PrintStream;
import java.io.OutputStream;

public class Main
{
    public static void main(String[] args) throws Exception
    {
        // suppress output during benchmark
        PrintStream original = System.out;
        System.setOut(new PrintStream(new OutputStream() {
            public void write(int b) {}
        }));

        // warm up the jvm first
        SubmissionController warmup = new SubmissionController();
        warmup.submit("warmup data");

        int runs = 1000;
        long totalTime = 0;

        for(int i = 0; i < runs; i++)
        {
            SubmissionController controller = new SubmissionController();
            long start = System.nanoTime();
            controller.submit("Research paper on AI in South African elections");
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