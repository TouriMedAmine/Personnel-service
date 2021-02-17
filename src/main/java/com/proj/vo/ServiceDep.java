package com.proj.vo;

public class ServiceDep {

	private Long serviceId;
    private String serviceName;
    private Long idDepartement;
	
    public ServiceDep() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ServiceDep(String serviceName, Long idDepartement) {
		super();
		this.serviceName = serviceName;
		this.idDepartement = idDepartement;
	}

	public Long getServiceId() {
		return serviceId;
	}

	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public Long getIdDepartement() {
		return idDepartement;
	}

	public void setIdDepartement(Long idDepartement) {
		this.idDepartement = idDepartement;
	}
    
    
	
    
}
