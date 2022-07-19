package util;

import model.LixeiraServidor;

import java.util.HashMap;
import java.util.Map;

public class DataStore {

    private Map<String, LixeiraServidor> lixeiraMap = new HashMap<>();


    private static DataStore instance = new DataStore();
    public static DataStore getInstance(){
        return instance;
    }


    private DataStore(){
        //dummy data
        lixeiraMap.put("lixeira1", new LixeiraServidor("lixeira1", 20, 10, true, true));
        lixeiraMap.put("lixeira2", new LixeiraServidor("lixeira2",25, 15, true, true));
        lixeiraMap.put("lixeira3", new LixeiraServidor("lixeira3", 30, 20, true, true));
    }

    public LixeiraServidor getLixeira(String name) {
        return lixeiraMap.get(name);
    }

    public void putLixeira(LixeiraServidor lixeira) {
        lixeiraMap.put(lixeira.getNome(), lixeira);
    }
}
