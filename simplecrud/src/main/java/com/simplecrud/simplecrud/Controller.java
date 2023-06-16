package com.simplecrud.simplecrud;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class Controller {
    ArrayList <Employee> dummyFiles = new ArrayList<Employee>();

    @Autowired
    ItemRepository employeeSheet;

    @GetMapping("/")
    @CrossOrigin
    public ArrayList <Employee> mainRoute(){
        return dummyFiles;
    }

    @GetMapping("/get")
    @CrossOrigin
    public Employee getMethod(@RequestBody JsonNode ID){
        int serialID = ID.get("ID").asInt();
        System.out.println("Querying id " + serialID);
        Employee employee = employeeSheet.findById(serialID);
        System.out.println(employee.getInfo());
        return employee;
    }

    @PostMapping("/post")
    @CrossOrigin
    public String postMethod(@RequestBody JsonNode load){
        Employee newEmployee = new Employee(
                load.get("name").textValue(),
                load.get("email").textValue(),
                load.get("phone").textValue(),
                load.get("ID").asInt()
        );
        dummyFiles.add(newEmployee);
        employeeSheet.save(newEmployee);
        return newEmployee.getInfo();
    }

    @PatchMapping("/update")
    @CrossOrigin
    public Employee patchMethod(@RequestBody JsonNode updatedRecord){
        int ID = updatedRecord.get("ID").asInt();
        Employee pEmployee = null;
        for (Employee employee : dummyFiles) {
            if (employee.getSerialID() == ID) {
                pEmployee = employee;
                employee.updateEmployeeRegistry(
                        updatedRecord.get("name").textValue(),
                        updatedRecord.get("email").textValue(),
                        updatedRecord.get("phone").textValue(),
                        updatedRecord.get("ID").asInt()
                );
            }
        }
        if (pEmployee != null) {
            return pEmployee;
        }
        else {
            return null;
        }
    }

    @DeleteMapping("/delete")
    @CrossOrigin
    public String deleteMethod(@RequestBody JsonNode deletionID){
        int ID = deletionID.get("ID").asInt();
        boolean removed = false;
        Employee pEmployee = null;
        for (Employee employee : dummyFiles) {
            if (employee.getSerialID() == ID) {
                pEmployee = employee;
            }
        }

        if (pEmployee != null) {
            dummyFiles.remove(pEmployee);
            removed = true;
        }

        return (removed ? "Entry removed" : "Entry not found.");
    }


}
