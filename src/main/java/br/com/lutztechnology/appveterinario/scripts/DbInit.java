package br.com.lutztechnology.appveterinario.scripts;

import br.com.lutztechnology.appveterinario.enums.State;
import br.com.lutztechnology.appveterinario.model.Address;
import br.com.lutztechnology.appveterinario.model.Employee;
import br.com.lutztechnology.appveterinario.model.Role;
import br.com.lutztechnology.appveterinario.repository.EmployeeRepository;
import br.com.lutztechnology.appveterinario.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class DbInit implements CommandLineRunner {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {

        if (roleRepository.findAll().isEmpty()) {
            // insert default roles
            Role adminRole = new Role("Gerente");
            Role veterinarianRole = new Role("Veterinário");
            List<Role> roles = new ArrayList<>();
            roles.add(adminRole);
            roles.add(veterinarianRole);
            roleRepository.saveAll(roles);

            // insert default user
            Employee admin = new Employee(
                    "glutzm@gmail.com",
                    "Gustavo Matos",
                    "046.343.021-28",
                    "(11) 97393-6293",
                    LocalDate.parse("1992-08-08"),
                    Address.builder()
                            .state(State.SP)
                            .city("São Paulo")
                            .district("Jardim Ubirajara")
                            .publicPlace("Rua Zike Tuma")
                            .number("224")
                            .cep("04458-000")
                            .complement("Casa 44")
                            .build(),
                    passwordEncoder.encode("12345"),
                    LocalDate.parse("2020-10-29"),
                    null,
                    true,
                    "Cardiologista",
                    State.MS,
                    "123456",
                    adminRole,
                    null);

            employeeRepository.save(admin);
        }
    }
}