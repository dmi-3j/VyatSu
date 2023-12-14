package com.example.lr15.controllers;

import org.springframework.ui.Model;
import com.example.lr15.entities.MedicalOrganization;
import com.example.lr15.services.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

@Controller
@RequestMapping("/organizations")
public class OrganizationController {
    private OrganizationService organizationService;

    @Autowired
    public void setOrganizationService(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }
    @GetMapping
    public String showOrganizationsList(Model model) {
        MedicalOrganization organization = new MedicalOrganization();
        model.addAttribute("organizations", organizationService.getAllOrganizations());
        model.addAttribute("organization", organization);
        return "organizations";
    }

    @PostMapping("/addOrUpdate/add")
    public String addOrganization(@ModelAttribute(value = "medicalorganization")MedicalOrganization medicalorganization) {
        organizationService.add(medicalorganization);
        return "redirect:/organizations";
    }
    @GetMapping("/addOrUpdate/add")
    public String test(Model model) {
        MedicalOrganization organization = new MedicalOrganization();
        model.addAttribute("organizations", organizationService.getAllOrganizations());
        model.addAttribute("organization", organization);
        return "addOrUpdate";
    }
    @GetMapping("/addOrUpdate/edit/{id}")
    public String editOrganization(Model model, @PathVariable(value = "id") Integer id) {
        MedicalOrganization medicalOrganization = organizationService.getById(id);
        model.addAttribute("organization", medicalOrganization);
        return "addOrUpdate";
    }
    @PostMapping("/addOrUpdate/edit/update")
    public String updateOrganization(@ModelAttribute(value = "organization") MedicalOrganization updatedOrganization) {
        MedicalOrganization organization = organizationService.getById(updatedOrganization.getId());
        organizationService.update(organization, updatedOrganization);
        return "redirect:/organizations";
    }

    @GetMapping("/show/{id}")
    public String showOneOrganization(Model model, @PathVariable(value = "id") Integer id) {
        MedicalOrganization medicalOrganization = organizationService.getById(id);
        model.addAttribute("organization", medicalOrganization);
        return "organization-info";
    }
    @GetMapping("/delete/{id}")
    public String deleteOrganizations(Model model, @PathVariable(value = "id") Integer id) {
        MedicalOrganization medicalOrganization = organizationService.getById(id);
        organizationService.delete(medicalOrganization);
        return "redirect:/organizations";
    }

    @GetMapping("/filter")
    public String filterOrganizations(Model model,
                                      @RequestParam(value = "name", required = false)String name,
                                      @RequestParam(value = "address", required = false) String address,
                                      @RequestParam(value = "timeofwork", required = false) String timeofwork) {
        MedicalOrganization medicalOrganization = new MedicalOrganization();
        model.addAttribute("organizations", organizationService.getAllOrganizations(name, address, timeofwork));
        model.addAttribute("organization", medicalOrganization);
        model.addAttribute("name", name);
        model.addAttribute("address", address);
        model.addAttribute("timeofwork", timeofwork);
        return "organizations";
    }

}