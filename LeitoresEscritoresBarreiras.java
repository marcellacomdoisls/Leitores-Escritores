import java.util.concurrent.CyclicBarrier;

public class LeitoresEscritoresBarreiras {
  private final CyclicBarrier barreira;

  public LeitoresEscritoresBarreiras(int numeroLeitores) {
    this.barreira = new CyclicBarrier(numeroLeitores, () -> {
      System.out.println("Todos os leitores terminaram. Escritores podem iniciar.");
    });
  }

  public void ler() throws InterruptedException {
    try {
      System.out.println(Thread.currentThread().getName() + " está lendo.");
      Thread.sleep(1000);
      barreira.await();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void escrever() throws InterruptedException {
    System.out.println(Thread.currentThread().getName() + " está escrevendo.");
    Thread.sleep(1000);
  }
}
