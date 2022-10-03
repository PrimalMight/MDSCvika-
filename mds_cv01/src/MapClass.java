import java.util.*;

public class MapClass {
    private HashMap<Integer, String> whatever;

    public MapClass(){
        whatever = new HashMap<Integer, String>();
    }

    public void store(Integer id, String value) throws ArrayStoreException{
        if(whatever.containsKey(id)){
            throw new ArrayStoreException();
        }
        else{
            whatever.put(id, value);
        }
    }

    public String getValue(Integer id) throws NoSuchFieldError{
        if(whatever.containsKey(id)){
            return whatever.get(id);
        }
        else{
            throw new NoSuchFieldError();
        }

    }

    public void deleteKey(Integer id) throws NoSuchFieldError{
        if(whatever.containsKey(id)){
            whatever.remove(id);
        }
        else{
            throw new NoSuchFieldError();
        }
    }

    public int getSize(){
        return whatever.size();
    }

    public void print(){
        for (int key: whatever.keySet()) {
            System.out.println("Key: " + key + ", value: " + whatever.get(key));
        }
    }
}
