# HospitalJEE


Practical activity N° 3 : Spring MVC , Thymeleaf , Spring DATA

  Objectif  : Develop a web application based on Spring MVC, Thymeleaf and Spring Data that allows to manage Hospital ( Patients , Nurses , Doctors ... ) 

  Project Structure : 
![image](https://user-images.githubusercontent.com/83168701/167514349-9ee66f41-8e79-4a73-932b-b0de737e7c33.png)

  Work Done : 
  
    - Search For Patient :
   ![image](https://user-images.githubusercontent.com/83168701/167517530-ad5ffa52-6d78-4de5-9a9f-1f66b4104d74.png)

        -> Generate an abstract function named "findByNomContains" that'll be called in PatientController it allows the user/admin to find a Patient using a keyword ( in this case the keyword is the partient's name ) -> a new Patient list will be generated in the current Page. 
        
    - Delete Patient :
   ![image](https://user-images.githubusercontent.com/83168701/167517579-24387e71-499c-425c-8491-d153b208fe34.png)
   ![image](https://user-images.githubusercontent.com/83168701/167517609-6189a13e-1935-4998-bac0-267b6e8a905f.png)
    

         -> The deleteById() methode will be used in this case to delete a single entity based on it's id. followed by a confirmation message . ( Patient ID = 8 was deleted)

    - Add Patient : 
   ![image](https://user-images.githubusercontent.com/83168701/167517731-e2ad8fd6-0bc3-40ff-9183-7dff0bedba47.png)

    - Edit Patient : 
   ![image](https://user-images.githubusercontent.com/83168701/167517779-ed90792b-a63d-4439-a2df-54957e3d24cf.png)

  
  
  Practical activity N° 4 : Spring Security Part

    Objectif  : Secure access to the appplication that manages patients using Spring Security . The access to the application requires a Username and a password .
                A user can have multiple roles such as :
                    - ROLE_USER : Has the permition to search for Patients
                    - Role_Admin : Has the permition to add , edit and delete patients .
  

  Project Structure : 
  ![image](https://user-images.githubusercontent.com/83168701/167515557-be6eaca7-6d10-42ee-9440-185ca64998c6.png)



