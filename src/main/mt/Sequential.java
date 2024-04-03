public class Sequential {
  public static long sum(int n, long N) {
    long result = 0;
    for (long i = 1; i <= N; i++) {
      result += i * n;
    }
    return result;
  }
}
