package com.proj.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proj.entities.Medecin;
import com.proj.services.MedecinService;
import com.proj.vo.ResponseTemplateVO;

@RestController
@RequestMapping("/Personnel")
public class MedecinController {
	
	@Autowired
	private MedecinService medecinService;
	
	@PostMapping("/Medecin/add")
    public Medecin addMedecin(@RequestBody Medecin medecin) {
        return medecinService.saveMedecin(medecin);
    }

	 
	@PostMapping("/Medecin/addMedecins")
	public List<Medecin> addMedecins(@RequestBody List<Medecin> medecins) {
		return medecinService.saveMedecins(medecins);
	}
	
	@GetMapping("/Medecin/medecins")
    public List<Medecin> findAllMedecins() {
        return medecinService.getMedecins();
    }
	
	@GetMapping("/Medecin/medecinById/{id}")
    public Medecin findMedecinById(@PathVariable Long id) {
        return medecinService.getMedecinById(id);
    }
	
	@GetMapping("/Medecin/medecinByCin/{cin}")
    public Medecin findMedecinByCin(@PathVariable String cin) {
        return medecinService.getMedecinByCin(cin);
    }
	
	@GetMapping("/Medecin/medecinWithDepCpt/{id}")
    public ResponseTemplateVO getUserWithDepartmentCompte(@PathVariable("id") Long medecinId) {
        return medecinService.getMedecinWithDepartmentCompte(medecinId);
    }
	
	@PutMapping("/Medecin/update")
    public Medecin updateMedecin(@RequestBody Medecin medecin) {
        return medecinService.updateMedecin(medecin);
    }
	
	@DeleteMapping("/Medecin/delete/{id}")
    public String deleteMedecin(@PathVariable Long id) {
        return medecinService.deleteMedecin(id);
    }
	
	
}


