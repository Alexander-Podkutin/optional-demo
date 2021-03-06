package optional.demo;

import java.util.Optional;

/**
 * Пример использования класса Optional
 * @author Alexander Podkutin
 *
 */
public class OptionalDemo {

    public static Optional<Person> getDefaultPerson() {
        Long defaultPersonId = 1l;
        return Optional.ofNullable(loadPerson(defaultPersonId));
    }
    
    private static Person loadPerson(Long personId) {
        if (personId == null) {
            return null;
        } else {
            Person personToLoad = new Person();
            personToLoad.setFirstName(null);
            personToLoad.setSecondName("Smith");
            personToLoad.setAge(33);
            PersonAddress address = new PersonAddress(new PersonAddressStreet("Manezhnaya str."));
            personToLoad.setAddress(address);
            return personToLoad;
        }
    }

    public static void main(String[] args) throws Exception {
        Optional<Person> person = getDefaultPerson();
        /** Пример использования Optional для удаления избыточного кода */
        /* Как было раньше (проверка на null)
        Person person = getDefaultPerson();
        if (person != null) {
            PersonAddress personAddress = person.getAddress();
            if (personAddress != null) {
                PersonAddressStreet street = personAddress.getStreet();
                if(street != null) {
                    streetName = street.getStreetName();
                } else {
                    streetName = "EMPTY";
                }
            }
        }
        */
        //Тот же фрагмент, но с использованием Optional.
        String streetName = person.flatMap(Person::getAddress)
            .flatMap(PersonAddress::getStreet)
            .map(PersonAddressStreet::getStreetName)
            .orElse("EMPTY");
        
        System.out.println(streetName);
        
        /** Создание Optional объектов */
        
        //Пустой Optional объект
        Optional<Person> optionalPerson = Optional.empty();
        
        //Optional объект с ненулевым значением
        Optional<Person> optionalNonNull = Optional.of(new Person());
        
        //Optional объект с возможностью нулевого значения
        Optional<Person> optionalNullable = Optional.ofNullable(new Person());
        
        /** Действия с объектом, с использованием метода ifPresent() */
        
        /* Как было раньше:
         if(person != null) {
          System.out.println(person);
         }
         */
        
        //То же самое, но с использованием Optional
        person.ifPresent(System.out::println);
        
        /** Действия с объектом с использованием isPresent() */
        /* Как было раньше
          if(person != null) {
            System.out.println(person)
          } else {
            System.out.println("Person not found!");
          }
         */
        
        //То же самое, но с использованием Optional
        //Изменения небольшие, но метод isPresent() придает больше информативности коду.
        
        if (person.isPresent()) {
            System.out.println(person.get());
        } else {
            System.out.println("Person not found!");
        }
        
        /** Действия с объектом с использованием orElse(), orElseThrow() */
        /* Как было раньше
          Person personNew = person != null ? person : new Person();
         */
        
        //То же самое, но с использованием Optional
        
        Person personNew = person.orElse(new Person());
        //Или если не хотим создавать объект, можно выбросить исключение
        Person personNewThrow = person.orElseThrow(Exception::new);
    }
}
