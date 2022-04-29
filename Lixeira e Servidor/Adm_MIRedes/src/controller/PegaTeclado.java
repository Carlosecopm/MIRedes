package controller;

/**
 *
 * @author Aloisio Kleyner
 */

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

class PegaTeclado implements NativeKeyListener{
        private static Administrador adm;
        private static PegaTeclado pegaT;
        
        public PegaTeclado(Administrador adm){
            PegaTeclado.adm = adm;
        }
        
        public void nativeKeyPressed(NativeKeyEvent e) {
		if (e.getKeyCode() == NativeKeyEvent.VC_1){
                    try {
                        GlobalScreen.removeNativeKeyListener(pegaT);
                        PegaTeclado.adm.infoLixeiras();
                        GlobalScreen.addNativeKeyListener(pegaT);
                    } catch (IOException ex) {
                        Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
                    }
                        
		}else if (e.getKeyCode() == NativeKeyEvent.VC_2){
                    try {
                        GlobalScreen.removeNativeKeyListener(pegaT);
                        PegaTeclado.adm.blockOrNotLixeiras();
                        GlobalScreen.addNativeKeyListener(pegaT);
                    } catch (IOException ex) {
                        Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
                    }
		} else if (e.getKeyCode() == NativeKeyEvent.VC_3){
                    try {
                        GlobalScreen.removeNativeKeyListener(pegaT);
                        PegaTeclado.adm.alterarColeta();
                        GlobalScreen.addNativeKeyListener(pegaT);
                    } catch (IOException ex) {
                        Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
                    }
		} else if (e.getKeyCode() == NativeKeyEvent.VC_4){
                    try {
                        GlobalScreen.removeNativeKeyListener(pegaT);
                        PegaTeclado.adm.infoColeta();
                        GlobalScreen.addNativeKeyListener(pegaT);
                    } catch (IOException ex) {
                        Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
                    }
		}
	}
        
        public static void run(){
            try {
                GlobalScreen.registerNativeHook();
            } catch (NativeHookException ex) {
                System.err.println("There was a problem registering the native hook.");
            }
            PegaTeclado.pegaT = new PegaTeclado(adm);
            GlobalScreen.addNativeKeyListener(pegaT);

                
        }

        /**
         * @return the cliente
         */
        public Administrador getCliente() {
            return adm;
        }

        /**
         * @param cliente the cliente to set
         */
        public void setCliente(Administrador adm) {
            PegaTeclado.adm = adm;
        }
    }