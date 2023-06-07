import java.util.Scanner;

public class UI {
    Scanner scanner;
    API api;


    public UI(API api) {
        this.api = api;
        scanner = new Scanner(System.in);
    }

    public void printMenu() {
        System.out.println(
                """
                        1. Создать новое животное
                        2. Получить список животных
                        3. Обучить животное новым командам
                        4. Получить список команд, которые может выполнять животное
                        0. Выход"""
        );
    }

    public void run() {
        String command;
        boolean flag = true;
        while (flag) {
            printMenu();
            System.out.println("Введите команду: ");
            command = scanner.nextLine();
            switch (command) {
                case "1" -> createAnimal();
                case "2" -> takeAnimalList();
                case "3" -> trainAnimal();
                case "4" -> takeCommandList();
                case "0" -> {
                    flag = false;
                    System.out.println("До скорых встреч");
                }
                default -> System.out.println("Введена неверная комнда");
            }
        }
    }

    private void createAnimal() {
        System.out.println("Введите кличку животного:");
        String name = scanner.nextLine();
        boolean crFlag = true;
        String animalType = "";
        while (crFlag) {
            System.out.println("""
                    1 - Кошка
                    2 - Собака
                    3 - Хомяк
                    4 - Лошадь
                    5 - Верблюд
                    6 - Осёл""");
            System.out.println("Введите цифру, соответствующую типу животного:");
            String inputData = scanner.nextLine();
            switch (inputData) {
                case "1" -> {
                    animalType = "Cat";
                    crFlag = false;
                }
                case "2" -> {
                    animalType = "Dog";
                    crFlag = false;
                }
                case "3" -> {
                    animalType = "Hamster";
                    crFlag = false;
                }
                case "4" -> {
                    animalType = "Horse";
                    crFlag = false;
                }
                case "5" -> {
                    animalType = "Camel";
                    crFlag = false;
                }
                case "6" -> {
                    animalType = "Donkey";
                    crFlag = false;
                }
                default -> System.out.println("Введена некорректная цифра");
            }
            System.out.println("Введите возраст животного");
            String age = scanner.nextLine().replace(" ", "");
            try {
                api.createAnimal(name, Integer.parseInt(age), animalType);
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void takeAnimalList() {
        if (api.takeAnimalList().isEmpty()) {
            System.out.println("|||||||||||||||||||||||");
            System.out.println("Животных в магазине нет");
            System.out.println("|||||||||||||||||||||||");
        }
        else {
            for (String animal : api.takeAnimalList()
            ) {
                System.out.println(animal);
            }
        }
    }

    private void trainAnimal() {
        int id = takeId();
        System.out.println("Введите команду, которой хотите научить животное: ");
        String command = scanner.nextLine();
        try {
            api.trainCommand(id, command);
        }
        catch (NullPointerException e) {
            System.out.println("||||||||||||||||||||||||");
            System.out.println("Животного с таким ID нет");
            System.out.println("||||||||||||||||||||||||");
        }
    }

    private Integer takeId() {
        while (true) {
            System.out.println("Введите id животного: ");
            String input = scanner.nextLine();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Введено не число");
            }
        }
    }

    private void takeCommandList() {
        int id = takeId();
        try {
            if (api.takeCommandList(id).isEmpty()) {
                System.out.println("|||||||||||||||||||||||||||||||");
                System.out.println("Данное животное ничего не умеет");
                System.out.println("|||||||||||||||||||||||||||||||");
            }
            else {
                System.out.printf("%s умеет следующие команды:", api.getAnimalName(id));
                for (String command : api.takeCommandList(id)
                ) {
                    System.out.println(command);
                }
            }
        }
        catch (RuntimeException e) {
            System.out.println("||||||||||||||||||||||||");
            System.out.println("Животного с таким ID нет");
            System.out.println("||||||||||||||||||||||||");
        }
    }
}
