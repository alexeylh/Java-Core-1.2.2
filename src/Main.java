import java.util.*;
import java.util.stream.Collectors;

class Main {
    public static void main(String[] args) {
        // Генерируем исходные данные - 10 тысяч объектов Person
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        // Найти количество несовершеннолетних (т.е. людей младше 18 лет)
        long count = persons.stream().filter(x -> x.getAge() < 18).count();
        System.out.println("Количество несовершеннолетних: " + count);

        // Получить список фамилий призывников (мужчин от 18 и до 27 лет)
        List<String> conscripts = persons.stream()
                .filter(x -> x.getSex() == Sex.MAN)
                .filter(x -> x.getAge() >= 18 && x.getAge() <= 27)
                .map(x -> x.getFamily())
                .collect(Collectors.toList());
        System.out.println("В список призывников включено " + conscripts.size() + " фамилий");

        /* Получить отсортированный по фамилии список потенциально работоспособных людей с высшим образованием
        (т.е. людей с высшим образованием от 18 до 60 лет для женщин и до 65 лет для мужчин) */
        List<Person> workers = persons.stream().filter(x -> x.getAge() >= 18)
                .filter(x -> x.getAge() >= 18)
                .filter(x -> x.getAge() < 60 || x.getSex() == Sex.MAN && x.getAge() < 65)
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
        System.out.println("В список работоспособных включено " + workers.size() + " фамилий");
    }
}
