public class LeitoresEscritoesMonitor {
  private int leitores =0;
  private boolean escrevendo = false;

  public synchronized void ler() throws InterruptedException {
    while (escrevendo) {
      wait();
    }
    leitores++;
    // Seção crítica de leitura
    System.out.println(Thread.currentThread().getName() + " está escrevendo.");
    Thread.sleep(1000);

    leitores--;
    if (leitores == 0) {
      notifyAll();
    }
  }
  public synchronized void escrever() throws InterruptedException {
    while (escrevendo || leitores > 0) {
      wait();
    }
    escrevendo = true;
    // Seção cítica de leitura
    System.out.println(Thread.currentThread().getName() + " está escrevendo.");
    Thread.sleep(1000);

    escrevendo = false;
    notifyAll();
  }
}
