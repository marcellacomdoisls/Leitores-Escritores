import java.util.concurrent.Semaphore;

public class LeitoresEscritoresSemaforo {
  private int leitores = 0;
  private final Semaphore semaforo = new Semaphore(1);
  private final Semaphore acesso = new Semaphore(1);

  public void ler() throws InterruptedException {
    semaforo.acquire();
    leitores++;
    if (leitores == 1) {
      acesso.acquire();
    }
    semaforo.release();

    // Seção crítica de leitura
    System.out.println(Thread.currentThread().getName() + " está lendo.");
    Thread.sleep(1000);

    semaforo.acquire();
    leitores--;
    if (leitores == 0) {
      acesso.release();
    }
    semaforo.release();
  }

  public void escrever() throws InterruptedException {
    acesso.acquire();
    // Seção crítica de escrita
    System.out.println(Thread.currentThread().getName() + " está escrevendo.");
    Thread.sleep(1000);
    acesso.release();
  }
}
