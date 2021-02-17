package com.proj.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.proj.entities.Admin;
import com.proj.repositories.AdminRepository;
import com.proj.vo.Compte;
import com.proj.vo.ResponseTemplateVO2;

@Service
public class AdminService {
	
	@Autowired
	private AdminRepository adminRepository;

	@Autowired
    private RestTemplate restTemplate;
	
	
	public Admin saveAdmin(Admin admin) {
		
		Compte compte = restTemplate.getForObject("http://COMPTE-SERVICE/comptes/" ,Compte.class);
		Long idCompte = compte.getCompteId();
		
		Admin adminTosave = new Admin();
		adminTosave.setCinAdmin(admin.getCinAdmin());
		adminTosave.setNom(admin.getNom());
		adminTosave.setPrenom(admin.getPrenom());
		
		adminTosave.setIdCompte(idCompte);
        return adminRepository.save(adminTosave);
    }
	
	
	public List<Admin> getAdmins() {
        return adminRepository.findAll();
    }
	
	public Admin getAdminById(Long id) {
        return adminRepository.findById(id).orElse(null);
    }
	
	public String deleteAdmin(Long id) {
		adminRepository.deleteById(id);
        return "administrateur removed !! " + id;
    }
	
	public Admin updateAdmin(Admin admin) {
        Admin existingAdmin = adminRepository.findById(admin.getId()).orElse(null);
        existingAdmin.setIdCompte(admin.getIdCompte());
        existingAdmin.setNom(admin.getNom());
        existingAdmin.setPrenom(admin.getPrenom());
        existingAdmin.setCinAdmin(admin.getCinAdmin());
        
        return adminRepository.save(existingAdmin);
    }
	
	
	
	public ResponseTemplateVO2 getAdminWithCompte(Long adminId) {
        
        ResponseTemplateVO2 vo = new ResponseTemplateVO2();
        Admin admin = adminRepository.findById(adminId).orElse(null);

        Compte compte =
                restTemplate.getForObject("http://COMPTE-SERVICE/comptes/" + admin.getIdCompte()
                        ,Compte.class);

        vo.setAdmin(admin);
        vo.setCompte(compte);

        return  vo;
    }

}
