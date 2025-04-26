public class LogManager {
    private static LogManager instance;

    // Construtor privado para evitar instanciação direta
    private LogManager() {
        System.out.println("LogManager instanciado."); // Apenas para verificar a instanciação
    }

    // Método getInstance sincronizado para garantir thread-safety
    public static synchronized LogManager getInstance() {
        if (instance == null) {
            instance = new LogManager();
        }
        return instance;
    }

    // Exemplo de um método para usar o LogManager
    public void log(String message) {
        System.out.println("[LOG]: " + message);
    }

    public static void main(String[] args) {
        // Demonstração em um ambiente single-threaded

        LogManager manager1 = LogManager.getInstance();
        manager1.log("Primeira mensagem de log.");

        LogManager manager2 = LogManager.getInstance();
        manager2.log("Segunda mensagem de log.");

        // Verifica se ambas as variáveis referenciam a mesma instância
        if (manager1 == manager2) {
            System.out.println("As instâncias manager1 e manager2 são as mesmas (Singleton funcionando).");
        }

        System.out.println("\nTestando em um ambiente simulado multithread:");

        // Simulação de ambiente multithread
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                LogManager.getInstance().log("Mensagem de log da thread " + Thread.currentThread().getId());
            }).start();
        }
    }
}