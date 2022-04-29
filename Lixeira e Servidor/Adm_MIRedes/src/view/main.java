package view;

import controller.Administrador;

/**
 *
 * @author Aloisio Kleyner
 */
public class main {
    public static void main(String[] a) {
        Administrador adm = new Administrador("127.0.0.1", 5555);
        adm.run();
    }
}
