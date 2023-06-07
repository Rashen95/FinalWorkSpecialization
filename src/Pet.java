public abstract class Pet extends Animal {
    public Pet(String arg_name, int arg_age) {
        super(arg_name, arg_age);
        super.family = "Домашнее животное";
    }
}
