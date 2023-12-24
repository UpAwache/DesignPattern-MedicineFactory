package Main;
import MedicineFactory.MedicineFactory;

public class Main {
    public static void main(String[] args) {

        MedicineFactory factory = MedicineFactory.getInstance();
        factory.run();

    }
}

