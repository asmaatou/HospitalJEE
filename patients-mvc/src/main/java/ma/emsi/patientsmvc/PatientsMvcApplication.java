package ma.emsi.patientsmvc;

import ma.emsi.patientsmvc.entities.Appointment;
import ma.emsi.patientsmvc.entities.Medecin;
import ma.emsi.patientsmvc.entities.Nurse;
import ma.emsi.patientsmvc.entities.Patient;
import ma.emsi.patientsmvc.repositories.AppointmentRepository;
import ma.emsi.patientsmvc.repositories.MedecinRepository;
import ma.emsi.patientsmvc.repositories.NurseRepository;
import ma.emsi.patientsmvc.repositories.PatientRepository;
import ma.emsi.patientsmvc.sec.service.SecurityService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@SpringBootApplication
public class PatientsMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatientsMvcApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //@Bean
    CommandLineRunner commandLineRunner(PatientRepository patientRepository){
        return args ->{
            patientRepository.save(
                    new Patient(null,"Manale",new Date(),"06xxxxxxxx","adress test nbr test",true,112));
            patientRepository.save(
                    new Patient(null,"Marwane",new Date(),"06xxxxxxxx","adress test nbr test",true,322));
            patientRepository.save(
                    new Patient(null,"Ayoub",new Date(),"06xxxxxxxx","adress test nbr test",true,654));
            patientRepository.save(
                    new Patient(null,"Saadia",new Date(),"06xxxxxxxx","adress test nbr test",true,400));

            patientRepository.findAll().forEach(p->{
                System.out.println(p.getNom());
            });
        };
    }
    //@Bean
    CommandLineRunner commandLineRunner1(MedecinRepository medecinRepository){
        return args ->{
            medecinRepository.save(
                    new Medecin(null,"Zineb","Surgery",new Date(),"zineb@gmail.com","06xxxxxxxx","adress test nbr test"));
            medecinRepository.save(
                    new Medecin(null,"Kawtar","Neurology",new Date(),"kawtar@gmail.com","06xxxxxxxx","adress test nbr test"));
            medecinRepository.save(
                    new Medecin(null,"Oussama","Pediatrics",new Date(),"oussama@gmail.com","06xxxxxxxx","adress test nbr test"));

            medecinRepository.findAll().forEach(m->
                    System.out.println(m.getNom()));
        };
    }

    //@Bean
            CommandLineRunner commandLineRunner3(NurseRepository nurseRepository){
        return args -> {
            nurseRepository.save(
                    new Nurse(null,"salma","ER",new Date(),"salma@gmail.com","06xxxxxxxx","adress test nbr test")
            );
            nurseRepository.save(
                    new Nurse(null,"Marwane","Travel",new Date(),"marwane@gmail.com","06xxxxxxxx","adress test nbr test")
            );
            nurseRepository.save(
                    new Nurse(null,"Imane","Cardiac",new Date(),"imane@gmail.com","06xxxxxxxx","adress test nbr test")
            );
            nurseRepository.findAll().forEach(n ->
                    System.out.println(n.getNom()));
        };
    }
    //@Bean
            CommandLineRunner commandLineRunner4(AppointmentRepository appointmentRepository){
        return args -> {
            appointmentRepository.save(
                    new Appointment(null,"Amine","Zineb",new Date(),new Date())
            );
            appointmentRepository.save(
                    new Appointment(null,"Houda","Kawtar",new Date(),new Date())
            );
            appointmentRepository.save(
                    new Appointment(null,"Youssef","Oussama",new Date(),new Date())
            );
            appointmentRepository.save(
                    new Appointment(null,"Youssra","Zineb",new Date(),new Date())
            );
            appointmentRepository.findAll().forEach(a->
                    System.out.println(a.getPatientname()));
        };
    }
    //@Bean
    CommandLineRunner saveUsers(SecurityService securityService){
        return args ->{
            securityService.saveNewUser("anass","1234","1234");
            securityService.saveNewUser("nada","1234","1234");


            securityService.saveNewRole("USER","");
            securityService.saveNewRole("ADMIN","");

            securityService.addRoleToUser("anass","USER");
            securityService.addRoleToUser("nada","ADMIN");
            securityService.addRoleToUser("nada","USER");


        };
    }


}
