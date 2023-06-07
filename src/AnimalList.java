import java.util.ArrayList;
import java.util.HashMap;

public class AnimalList {
    private final HashMap<Integer, Animal> list;
    private int maxId;
    StringBuilder stringBuilder;

    public AnimalList() {
        this.list = new HashMap<>();
        stringBuilder = new StringBuilder();
    }

    public HashMap<Integer, Animal> getList() {
        return list;
    }


    public void addAnimal(Animal animal) {
        refreshMaxId(maxId + 1);
        this.list.put(this.maxId, animal);
    }

    public Animal getAnimal(int id) {
        return list.get(id);
    }

    private void refreshMaxId(int newId) {
        if (newId >= maxId) {
            maxId = newId;
        }
    }

    public ArrayList<String> takeAnimalListArray() {
        ArrayList<String> data = new ArrayList<>();
        for (int id : list.keySet()) {
            data.add(String.format("%d - %s\n", id, list.get(id).toString()));
        }
        return data;
    }
}
