public class Main {
  public static void main(String[] args) throws Exception {
    int n = 6;
    long N = 100_000_000;


    long startTime = System.currentTimeMillis();
    long result = APFormula.sum(n, N);
    long endTime = System.currentTimeMillis();
    System.out.println("Formula result: " + result);
    System.out.println("Formula time: " + (endTime - startTime) + " ms");



    startTime = System.currentTimeMillis();
    result = Sequential.sum(n, N);
    endTime = System.currentTimeMillis();
    System.out.println("Sequential result: " + result);
    System.out.println("Sequential time: " + (endTime - startTime) + " ms");



    for (int numThreads = 2; numThreads <= 32; numThreads *= 2) {
      startTime = System.currentTimeMillis();
      result = Parallel.sum(n, N, numThreads);
      endTime = System.currentTimeMillis();
      System.out.println("Parallel result with " + numThreads + " threads: " + result);
      System.out.println("Parallel time with " + numThreads + " threads: " + (endTime - startTime) + " ms");
    }
  }
}
