import java.util.Random;

public class RandomValuesGenerator {
    public String randomFirstNameGenerator(){
        String[] firstname = {"Umar", "Zain", "Talha", "Mark", "Aiswarya", "Kashif"};
        Random rand = new Random();
        int randomIndex = rand.nextInt(firstname.length);
        String randomValue = firstname[randomIndex];
        return randomValue;
    }

    public String randomLastNameGenerator(){
        String[] firstname = {"Hassan", "Tahir", "Shafqat", "Angelo", "Rai", "Hafeez"};
        Random rand = new Random();
        int randomIndex = rand.nextInt(firstname.length);
        String randomValue = firstname[randomIndex];
        return randomValue;
    }

    public String RandomAddressGenerator(){
        String[] arr = {"Aqary", "FineHome", "Gravity", "Huawei", "Sunpo", "Wemart", "Greenhouse"};
        Random x = new Random();
        int randomIndex = x.nextInt(arr.length);
        // Return and print the random string
        String randomString = arr[randomIndex];
        return randomString;
    }

    public String RandomAreaGenerator(){
        String[] arr2 = {"Al Maryah Island", "Al Reem Island", "Mabali Island", "Hamdan Street"};
        Random rand = new Random();
        int randarr = rand.nextInt(arr2.length);
        String randomvalues = arr2[randarr];
        return randomvalues;
    }
}
