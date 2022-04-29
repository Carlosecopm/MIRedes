package view;

import controller.Lixeira;

/**
 *
 * @author Aloisio Kleyner
 */
public class main {
    
    public static void main(String[] a) {
        Lixeira lixeira = new Lixeira("127.0.0.1", 5555);
        lixeira.run();
    }
}
