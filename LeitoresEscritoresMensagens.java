import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class LeitoresEscritoresMensagens {
  private final BlockingQueue<String> filaMensagens = new LinkedBlockingQueue<>();

  public void ler() throws InterruptedException {
    filaMensagens.put("leitor");
    // Seção crítica de leitura
    System.out.println(Thread.currentThread().getName() + " está lendo.");
    Thread.sleep(1000);
    filaMensagens.take();
  }

  public void escrever() throws InterruptedException {
    filaMensagens.put("escritor");
    // Seção crítica de leitura
    System.out.println(Thread.currentThread().getName() + " está escrevendo.");
    Thread.sleep(1000);
    filaMensagens.take();
  }
}
