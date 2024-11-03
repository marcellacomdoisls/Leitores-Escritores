import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LeitoresEscritoresMutex {
  private final Lock mutex = new ReentrantLock();
  private int leitores = 0;
  private boolean escrevendo = false;

  public void ler () throws InterruptedException {
    mutex.lock();
    try {
      while (escrevendo) {
        mutex.wait();
      }
      leitores++;
    } finally {
      mutex.unlock();
    }
    // Seção crítica de leitura
    System.out.println(Thread.currentThread().getName() + " está lendo.");
    Thread.sleep(1000);

    mutex.lock();
    try {
      leitores--;
      if (leitores == 0) {
        mutex.notifyAll();
      }
    } finally {
      mutex.unlock();
    }
  }

  public void escrever () throws InterruptedException {
    mutex.lock();
    try {
      while (escrevendo || leitores == 0) {
        mutex.wait();
      }
      escrevendo = true;
    } finally {
      mutex.unlock();
    }

    // Seção crítica de escrita
    System.out.println(Thread.currentThread().getName() + " está escrevendo.");
    Thread.sleep(1000);

    mutex.lock();
    try {
      escrevendo = false;
      mutex.notifyAll();
    } finally {
      mutex.unlock();
    }
  }
}