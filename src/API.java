import java.util.ArrayList;

public class API {
    AnimalList animalList;
    Counter counter;


    public API() {
        animalList = new AnimalList();
        counter = new Counter();
    }

    public void createAnimal(String name, int age, String animalType) throws RuntimeException {
        Animal newAnimal;
        try {
            switch (animalType) {
                case "Cat" -> newAnimal = new Cat(name, age);
                case "Dog" -> newAnimal = new Dog(name, age);
                case "Hamster" -> newAnimal = new Hamster(name, age);
                case "Horse" -> newAnimal = new Horse(name, age);
                case "Camel" -> newAnimal = new Camel(name, age);
                case "Donkey" -> newAnimal = new Donkey(name, age);
                default -> throw new RuntimeException("Введен некорректный тип животного");
            }
            animalList.addAnimal(newAnimal);
            counter.add();
        } catch (RuntimeException e) {
            throw new RuntimeException("Поле 'ИМЯ' не может быть пустым");
        }
    }


    public ArrayList<String> takeAnimalList() {
        return animalList.takeAnimalListArray();
    }

    public void trainCommand(int id, String newCommand) {
        animalList.getAnimal(id).addCommand(newCommand);
    }

    public String getAnimalName(int id) {
        return animalList.getAnimal(id).getName();
    }

    public ArrayList<String> takeCommandList(int id) {
        if (animalList.getList().containsKey(id)) {
            return new ArrayList<>(animalList.getAnimal(id).getCommands());
        } else {
            throw new RuntimeException("Животного с данным id нет");
        }
    }
}
