
import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

class PegaTeclado implements NativeKeyListener{
        private static Lixeira cliente;
        private static PegaTeclado pegaT;
        
        public PegaTeclado(Lixeira cliente){
            PegaTeclado.cliente = cliente;
        }
        
        public void nativeKeyPressed(NativeKeyEvent e) {
		if (e.getKeyCode() == NativeKeyEvent.VC_1){
                    try {
                        GlobalScreen.removeNativeKeyListener(pegaT);
                        PegaTeclado.cliente.adicionaLixo();
                        GlobalScreen.addNativeKeyListener(pegaT);
                    } catch (IOException ex) {
                        Logger.getLogger(Lixeira.class.getName()).log(Level.SEVERE, null, ex);
                    }
                        
		}else if (e.getKeyCode() == NativeKeyEvent.VC_2){
                    try {
                        GlobalScreen.removeNativeKeyListener(pegaT);
                        PegaTeclado.cliente.encerraLixeira();
                        GlobalScreen.addNativeKeyListener(pegaT);
                    } catch (IOException ex) {
                        Logger.getLogger(Lixeira.class.getName()).log(Level.SEVERE, null, ex);
                    }
		}
	}
        
        public static void run(){
            try {
                GlobalScreen.registerNativeHook();
            } catch (NativeHookException ex) {
                System.err.println("There was a problem registering the native hook.");
            }
            PegaTeclado.pegaT = new PegaTeclado(cliente);
            GlobalScreen.addNativeKeyListener(pegaT);

                
        }

        /**
         * @return the cliente
         */
        public Lixeira getCliente() {
            return cliente;
        }

        /**
         * @param cliente the cliente to set
         */
        public void setCliente(Lixeira cliente) {
            PegaTeclado.cliente = cliente;
        }
    }