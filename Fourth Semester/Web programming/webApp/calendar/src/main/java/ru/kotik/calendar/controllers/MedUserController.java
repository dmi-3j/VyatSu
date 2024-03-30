package ru.kotik.calendar.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kotik.calendar.entities.*;
import ru.kotik.calendar.services.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;


@Controller
public class MedUserController {

    @Autowired
    private UserService userService;

    @Autowired
    VaccineService vaccineService;

    @Autowired
    VaccineComponentService vaccineComponentService;

    @Autowired
    OrganizationService organizationService;

    @Autowired
    VaccinationService vaccinationService;
    @GetMapping("/med/users")
    public String showMedUserPage(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("users", userService.getUsers());
        return "medUserPage";
    }
    @GetMapping("/med/users/filterUsers")
    public String filterUsers(Model model,
                                @RequestParam(value = "lastname", required = false) String lastname,
                                @RequestParam(value = "firstname", required = false) String firstname,
                                @RequestParam(value = "insnum", required = false) String insnum) {
        List<User> filteredUsers = userService.getUsers(lastname, firstname, insnum);
        model.addAttribute("users", filteredUsers);
        model.addAttribute("user", new User());
        model.addAttribute("lastname", lastname);
        model.addAttribute("firstname",  firstname);
        model.addAttribute("insnum", insnum);
        return "medUserPage";
    }
    @GetMapping("/med/users/info/{username}")
    public String userInfoPage(@PathVariable String username,
                               Model model) {
        User user = userService.getUserByUserName(username);
        List<Vaccine> vaccines = vaccineService.getAllVaccines();
        model.addAttribute("user", user);
        model.addAttribute("vaccines", vaccines);
        List<MedicalOrganization> organizations = organizationService.getAllOrganizations();
        model.addAttribute("organizations", organizations);
        List<Vaccination> vaccinations = vaccinationService.getVaccinationsByUser(user);
        model.addAttribute("vaccinations", vaccinations);
        model.addAttribute("vaccination", new Vaccination());

        return "userinfopage";
    }
    @GetMapping("/med/users/filterVaccination")
    public String filterVaccination(@RequestParam("usr") String username,
                                    @RequestParam("seria") String seria,
                                    @RequestParam("vcc") String vaccineName,
                                    Model model) {
        User user = userService.getUserByUserName(username);
        List<Vaccine> vaccines = vaccineService.getAllVaccines();
        model.addAttribute("user", user);
        model.addAttribute("vaccines", vaccines);
        List<MedicalOrganization> organizations = organizationService.getAllOrganizations();
        model.addAttribute("organizations", organizations);
        List<Vaccination> vaccinations = vaccinationService.getVaccinationsByUser(user, seria, vaccineName);
        model.addAttribute("vaccinations", vaccinations);
        model.addAttribute("vaccination", new Vaccination());

        model.addAttribute("username", username);
        model.addAttribute("seria", seria);
        model.addAttribute("vaccineName", vaccineName);

        return "userinfopage";
    }

    @PostMapping("/med/users/addVaccination")
    public String addVaccination(@RequestParam("username") String username,
                                 @RequestParam("serial") String serial,
                                 @RequestParam("vaccineId") int vaccineId,
                                 @RequestParam("componentId") int componentId,
                                 @RequestParam("organizationId") int organizationId,
                                 Model model) {
        User user = userService.getUserByUserName(username);
        Vaccine vaccine = vaccineService.getVaccineById(vaccineId);
        VaccineComponent component = vaccineComponentService.getComponentById(componentId);
        MedicalOrganization organization = organizationService.getMedicalOrganizationById(organizationId);
        vaccinationService.saveVaccination(serial, user, vaccine, component, organization);
        return "redirect:/med/users/info/" + username;
    }
    @GetMapping("/med/vaccine/components/{vaccineId}")
    public ResponseEntity<?> getComponentsByVaccineId(@PathVariable int vaccineId) {
        List<VaccineComponent> components = vaccineComponentService.getComponentsByVaccineId(vaccineId);
        if (components != null && !components.isEmpty()) {
            return ResponseEntity.ok(components);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/med/vaccination/info/{id}")
    public String vaccinationInfo(Model model,
                              @PathVariable(value = "id") int id,
                              HttpServletRequest request) {
        Vaccination vaccination = vaccinationService.getById(id);
        model.addAttribute("vaccination", vaccination);
        String referer = request.getHeader("referer");
        model.addAttribute("referer", referer);
        return "vaccinationinfopage";
    }

}


