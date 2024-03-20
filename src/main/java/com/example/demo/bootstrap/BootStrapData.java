package com.example.demo.bootstrap;

import com.example.demo.dao.CustomerRepository;
import com.example.demo.dao.DivisionRepository;
import com.example.demo.entities.Customer;
import com.example.demo.entities.Division;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final CustomerRepository customerRepository;
    private final DivisionRepository divisionRepository;

    public BootStrapData(CustomerRepository customerRepository, DivisionRepository divisionRepository) {
        this.customerRepository = customerRepository;
        this.divisionRepository = divisionRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Division division = new Division();
        division.setId(4L);

        // Create initialization customers
        Customer custOneInit = new Customer("Mary", "Bunion","125 Oakbrook Court", "87695","7869895432", division);
        Customer custTwoInit = new Customer("John", "Highney","6749 Muffins Lane", "96542","9368886574", division);
        Customer custThreeInit = new Customer("Charley", "Cutie","1510 Lovers Lane", "97211","5576724400", division);
        Customer custFourInit = new Customer("Atlas", "Furhaven","95 Barker Court", "89521","7756230088", division);
        Customer custFiveInit = new Customer("Frankey", "Wavytail","657 Growler Terrace", "55472","5032476754", division);

        // Add customers to division
        //assert division != null;
        division.getCustomers().add(custOneInit);
        division.getCustomers().add(custTwoInit);
        division.getCustomers().add(custThreeInit);
        division.getCustomers().add(custFourInit);
        division.getCustomers().add(custFiveInit);

        // Save customers to repo
        customerRepository.save(custOneInit);
        customerRepository.save(custTwoInit);
        customerRepository.save(custThreeInit);
        customerRepository.save(custFourInit);
        customerRepository.save(custFiveInit);

        System.out.println("Started in Bootstrap");


    }
}
