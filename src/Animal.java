import java.util.HashSet;

public abstract class Animal {
    private final String name;
    private final int age;
    HashSet<String> commands;
    String family;
    String type;

    public Animal(String arg_name, int arg_age) {
        if (arg_name.equals("")) {
            throw new RuntimeException("Поле не может быть пустым");
        }
        this.name = arg_name;
        this.age = arg_age;
        this.commands = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public HashSet<String> getCommands() {
        return commands;
    }

    public void addCommand(String newCommand) {
        if (this.commands.contains(newCommand)) {
            System.out.println("Животное уже знает эту команду");
        } else {
            this.commands.add(newCommand);
            System.out.printf("%s только что выучилось команде %s\n", this.name, newCommand);
        }
    }

    public String toString() {
        return String.format("%s - %s - %s - Возраст: %s", name, type, family, age);
    }
}