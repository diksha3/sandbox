package com.diksha;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
class Address implements Cloneable{
    String houseNo ;
    String apartmentNo ;
}

@AllArgsConstructor
@Getter
@Setter

class Person implements Cloneable{
    String firstName ;
    String lastName ;

    Address address ;

    public Person(Person p){
        this.firstName = p.getFirstName() ;
        this.lastName = p.getLastName() ;
        this.address = p.getAddress() ;

    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();

    }
}
public class CopyTest {

    public static void main(String[] args) {
        Person p1 = new Person("naman", "sinha", new Address("3", "36")) ;
        Person p2 = null;
        try {
            p2 = (Person) p1.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
        p1.getAddress().setApartmentNo("37");
          System.out.println(p2.getAddress().getApartmentNo());
    }
}
