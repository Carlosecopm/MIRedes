package util;

import model.LixeiraServidor;

import java.util.HashMap;
import java.util.Map;

public class DataStore {
    //Map of names to Lixeira instances.
    private Map<String, LixeiraServidor> lixeiraMap = new HashMap<>();

    //this class is a singleton and should not be instantiated directly!
    private static DataStore instance = new DataStore();
    public static DataStore getInstance(){
        return instance;
    }

    //private constructor so people know to use the getInstance() function instead
    private DataStore(){
        //dummy data
        lixeiraMap.put("lixeira1", new LixeiraServidor("lixeira1", 20, 10, true, true));
        lixeiraMap.put("lixeira2", new LixeiraServidor("lixeira2",25, 15, true, true));
        lixeiraMap.put("lixeira3", new LixeiraServidor("lixeira3", 30, 20, true, true));
    }

    public LixeiraServidor getPerson(String name) {
        return lixeiraMap.get(name);
    }

    public void putLixeira(LixeiraServidor lixeira) {
        lixeiraMap.put(lixeira.getNome(), lixeira);
    }
}
