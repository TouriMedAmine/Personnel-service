package com.proj.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.proj.entities.Medecin;
import com.proj.repositories.MedecinRepository;
import com.proj.vo.Compte;
import com.proj.vo.ServiceDep;
import com.proj.vo.ResponseTemplateVO;

@Service
public class MedecinService {
	
	@Autowired
	private MedecinRepository medecinRepository;

	@Autowired
    private RestTemplate restTemplate;
	
	
	public Medecin saveMedecin(Medecin medecin) {
		Compte compte = restTemplate.getForObject("http://COMPTE-SERVICE/comptes/" ,Compte.class);
		Long idCompte = compte.getCompteId();
		
		Medecin medecinTosave = new Medecin();
		medecinTosave.setCinMedecin(medecin.getCinMedecin());
		medecinTosave.setNom(medecin.getNom());
		medecinTosave.setPrenom(medecin.getPrenom());
		medecinTosave.setSpecialite(medecin.getSpecialite());
		medecinTosave.setNomServiceDep(medecin.getNomServiceDep());
		
		medecinTosave.setIdCompte(idCompte);
        return medecinRepository.save(medecinTosave);
    }
	
	public List<Medecin> saveMedecins(List<Medecin> medecins) {
        return medecinRepository.saveAll(medecins);
    }
	
	public List<Medecin> getMedecins() {
        return medecinRepository.findAll();
    }
	
	public Medecin getMedecinById(Long id) {
        return medecinRepository.findById(id).orElse(null);
    }
	
	public Medecin getMedecinByCin(String cin) {
        return medecinRepository.findByCin(cin);
    }
	
	public String deleteMedecin(Long id) {
		medecinRepository.deleteById(id);
        return "médecin removed !! " + id;
    }
	
	public Medecin updateMedecin(Medecin medecin) {
        Medecin existingMedecin = medecinRepository.findById(medecin.getId()).orElse(null);
        
        existingMedecin.setIdCompte(medecin.getIdCompte());
       
        existingMedecin.setCinMedecin(medecin.getCinMedecin());
        existingMedecin.setNom(medecin.getNom());
        existingMedecin.setPrenom(medecin.getPrenom());
        existingMedecin.setSpecialite(medecin.getSpecialite());
        existingMedecin.setNomServiceDep(medecin.getNomServiceDep());
        
        return medecinRepository.save(existingMedecin);
    }
	
	public ResponseTemplateVO getMedecinWithDepartmentCompte(Long medecinId) {
        
        ResponseTemplateVO vo = new ResponseTemplateVO();
        Medecin medecin = medecinRepository.findById(medecinId).orElse(null);

        ServiceDep serviceDep =
                restTemplate.getForObject("http://SERVICEDEP-SERVICE/service/" + medecin.getNomServiceDep()
                        ,ServiceDep.class);
        
        Compte compte =
                restTemplate.getForObject("http://COMPTE-SERVICE/comptes/" + medecin.getIdCompte()
                        ,Compte.class);

        vo.setMedecin(medecin);
        vo.setServiceDep(serviceDep);
        vo.setCompte(compte);

        return  vo;
    }
	
	
	
	
	
	
	
	
}

