package optional.demo;

import java.util.Optional;

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
		/** ������ ������������� Optional ��� �������� ����������� ���� */
		/* ��� ���� ������ (�������� �� null)
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
		//��� �� ��������, �� � �������������� Optional.
		String streetName = person.flatMap(Person::getAddress)
                .flatMap(PersonAddress::getStreet)
                .map(PersonAddressStreet::getStreetName)
                .orElse("EMPTY");
		
		System.out.println(streetName);
		
		/** �������� Optional �������� */
		
		//������ Optional ������
		Optional<Person> optionalPerson = Optional.empty();
		
		//Optional ������ � ��������� ���������
		Optional<Person> optionalNonNull = Optional.of(new Person());
		
		//Optional ������ � ������������ �������� ��������
		Optional<Person> optionalNullable = Optional.ofNullable(new Person());
		
		
		/** �������� � ��������, � �������������� ������ ifPresent() */
		
		/* ��� ���� ������:
		 if(person != null) {
		  System.out.println(person);
		 }
		 
		 */
		
		//�� �� �����, �� � �������������� Optional
		
		person.ifPresent(System.out::println);
		
		/** �������� � �������� � �������������� isPresent() */
		/* ��� ���� ������
		  if(person != null) {
		    System.out.println(person)
		  } else {
		    System.out.println("Person not found!");
		  }
		 */
		
		//�� �� �����, �� � �������������� Optional
		//��������� ���������, �� ����� isPresent() ������� ������ ��������������� ����.
		
		if (person.isPresent()) {
			System.out.println(person.get());
		} else {
			System.out.println("Person not found!");
		}
		
		/** �������� � �������� � �������������� orElse(), orElseThrow() */
		/* ��� ���� ������
		  Person personNew = person != null ? person : new Person();
		 */
		
		//�� �� �����, �� � �������������� Optional
		
		Person personNew = person.orElse(new Person());
		//��� ���� �� ����� ��������� ������, ����� ��������� ����������
		Person personNewThrow = person.orElseThrow(Exception::new);
		
		System.out.println();
		
		
		
	}

}
