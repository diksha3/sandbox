package com.diksha;

import lombok.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class DIP {
    interface IStorage{
        public void doSomething() ;
    }
class MyService{
    IStorage storage;
}

class CloudStorage implements IStorage{

    @Override
    public void doSomething() {
        System.out.println("created Cloud storage");
    }
}

    class LocalStorage implements IStorage{

        @Override
        public void doSomething() {
            System.out.println("created Local storage");
        }
    }

enum StorageType{
        LOCAL ,
        CLOUD  ;
}

    public static void main(String[] args) {
 /*       @Value("myservice.storage.type")
                StorageType storageType ;
        if(storageType.equals(StorageType.CLOUD)) {
            MyService myService = new MyService(new CloudStorage()) ;
        }
        else if(storageType.equals(StorageType.LOCAL)){
            MyService myService = new MyService(new LocalStorage()) ;
        }*/

    }
}
