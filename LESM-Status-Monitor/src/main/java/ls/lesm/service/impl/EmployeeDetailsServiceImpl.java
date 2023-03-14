package ls.lesm.service.impl;

import java.security.Principal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import ls.lesm.exception.DateMissMatchException;
import ls.lesm.exception.DuplicateEntryException;
import ls.lesm.exception.SupervisorAlreadyExistException;
import ls.lesm.model.Address;
import ls.lesm.model.AtClientAllowances;
import ls.lesm.model.Departments;
import ls.lesm.model.EmployeeResponsibilities;
import ls.lesm.model.EmployeesAtClientsDetails;
import ls.lesm.model.History;
import ls.lesm.model.InternalExpenses;
import ls.lesm.model.MasterEmployeeDetails;
import ls.lesm.model.ReleaseEmpDetails;
import ls.lesm.model.Salary;
import ls.lesm.model.SecondaryManager;
import ls.lesm.model.SubDepartments;
import ls.lesm.model.Technology;
import ls.lesm.model.User;
import ls.lesm.model.enums.ResponsibilitiesTypes;
import ls.lesm.model.enums.UpdatedStatus;
import ls.lesm.payload.request.ClientEmpUpdateReq;
import ls.lesm.payload.request.EmployeeDetailsRequest;
import ls.lesm.payload.request.EmployeeDetailsUpdateRequest;
import ls.lesm.payload.response.AllEmpCardDetails;
import ls.lesm.payload.response.EmpCorrespondingDetailsResponse;
import ls.lesm.payload.response.EmployeeAtClientResponse;
import ls.lesm.payload.response.EmployeeCompleteDetailsResponse;
import ls.lesm.payload.response.EmployeeDetailResponse;
import ls.lesm.payload.response.EmployeeDetailsResponse;
import ls.lesm.payload.response.InternalExpensesResponse;
import ls.lesm.repository.AddressRepositoy;
import ls.lesm.repository.AddressTypeRepository;
import ls.lesm.repository.AllowancesOfEmployeeRepo;
import ls.lesm.repository.ClientsRepository;
import ls.lesm.repository.DepartmentsRepository;
import ls.lesm.repository.DesignationsRepository;
import ls.lesm.repository.EmployeeResponsibilitiesRepository;
import ls.lesm.repository.EmployeeTypeRepository;
import ls.lesm.repository.EmployeesAtClientsDetailsRepository;
import ls.lesm.repository.HistoryRepository;
import ls.lesm.repository.InternalExpensesRepository;
import ls.lesm.repository.MasterEmployeeDetailsRepository;
import ls.lesm.repository.ReleaseEmpDetailsRepository;
import ls.lesm.repository.SalaryRepository;
import ls.lesm.repository.SecondaryManagerRepository;
import ls.lesm.repository.SubDepartmentsRepository;
import ls.lesm.repository.TechnologyRepository;
import ls.lesm.repository.UserRepository;
import ls.lesm.service.EmployeeDetailsService;

@Service
public class EmployeeDetailsServiceImpl implements EmployeeDetailsService {

	@Autowired
	private AddressRepositoy addressRepositoy;

	@Autowired
	private AddressTypeRepository addressTypeRepository;

	@Autowired
	private MasterEmployeeDetailsRepository masterEmployeeDetailsRepository;

	@Autowired
	private DepartmentsRepository departmentsRepository;

	@Autowired
	private SubDepartmentsRepository subDepartmentsRepositorye;

	@Autowired
	private DesignationsRepository designationsRepository;

	@Autowired
	private EmployeesAtClientsDetailsRepository employeesAtClientsDetailsRepository;

	@Autowired
	private InternalExpensesRepository internalExpensesRepository;

	@Autowired
	private SalaryRepository salaryRepository;

	@Autowired
	private ClientsRepository clientsRepository;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private EmployeeTypeRepository employeeTypeRepo;
	
	@Autowired
	private SecondaryManagerRepository secondaryManagerRepo;
	
	@Autowired
	private HistoryRepository historyRepository;
	
	@Autowired
	private ReleaseEmpDetailsRepository releaseEmpDetailsRepo;
	
	@Autowired
	private EmployeeResponsibilitiesRepository responsibilityRepo;
	

	@Autowired
	private AllowancesOfEmployeeRepo allowancesOfEmployeeRepo;

	@Autowired
	private TechnologyRepository technologyRepository;
	
	// UMER
	public EmployeeDetailsRequest insetEmpDetails(EmployeeDetailsRequest empDetails, Principal principal) {

		empDetails.getMasterEmployeeDetails().setCreatedAt(LocalDate.now());
		empDetails.getMasterEmployeeDetails().setCreatedBy(principal.getName());
		if (empDetails.getMasterEmployeeDetails().getDOB().isAfter(LocalDate.now())) {
			throw new DateMissMatchException("Date Of Birth can not be after todays date");
		}
		empDetails.getMasterEmployeeDetails()
				.setLancesoft(empDetails.getMasterEmployeeDetails().getLancesoft().toUpperCase());

		

		empDetails.getAddress().setCreatedAt(LocalDate.now());
		empDetails.getAddress().setCreatedBy(principal.getName());

		empDetails.getSalary().setCreatedAt(empDetails.getMasterEmployeeDetails().getJoiningDate());
		empDetails.getSalary().setCreatedBy(principal.getName());

		
		

		this.addressRepositoy.save(empDetails.getAddress());
		
		this.salaryRepository.save(empDetails.getSalary());
		return empDetails;
	}

	// UMER
	



	// UMER
	@Override
	public Page<AllEmpCardDetails> getAllEmpCardDetails(PageRequest pageRequest) {
		Page<AllEmpCardDetails> page = this.masterEmployeeDetailsRepository.getAlEmpCardDetails(pageRequest);
		return page;
	}

	// UMER
	@Override
	public Page<AllEmpCardDetails> getSortedEmpCardDetailsByDesg(Integer desgId, PageRequest pageRequest) {
		Page<AllEmpCardDetails> page = this.masterEmployeeDetailsRepository.getSortedEmpCardDetailsByDesg(desgId,
				pageRequest);
		return page;
	}

	// UMER
	@Override
	public EmpCorrespondingDetailsResponse getEmpCorresDetails(EmpCorrespondingDetailsResponse corssDetails,
			int empid) {
		Optional<InternalExpenses> data = this.internalExpensesRepository.findBymasterEmployeeDetails_Id(empid);
		if (data.isPresent())
			corssDetails.setInternalExpenses(data.get());// .setBenchTenure(data.get().getBenchTenure())

		Optional<Salary> data3 = this.salaryRepository.findBymasterEmployeeDetails_Id(empid);
		if (data3.isPresent())
			corssDetails.setSalary(data3.get());// .setSalary(data3.get().getSalary());

		EmployeeDetailsResponse data4 = this.masterEmployeeDetailsRepository.getEmpDetailsById(empid);
		corssDetails.setEmployeeDetailsResponse(data4);
		return corssDetails;
	}

	// UMER
//	@Transactional
	@Override
	public EmployeeDetailsUpdateRequest updateEmployee(EmployeeDetailsUpdateRequest empReq, int id) {
		MasterEmployeeDetails employee = this.masterEmployeeDetailsRepository.findById(id).get();

		if (employee != null) {
			employee.setFirstName(empReq.getMasterEmployeeDetails().getFirstName());
			employee.setLastName(empReq.getMasterEmployeeDetails().getLastName());
			
			employee.setTechnology2(empReq.getMasterEmployeeDetails().getTechnology2());

			employee.setDOB(empReq.getMasterEmployeeDetails().getDOB());

			employee.setEmail(empReq.getMasterEmployeeDetails().getEmail());
			employee.setJoiningDate(empReq.getMasterEmployeeDetails().getJoiningDate());

			// MasterEmployeeDetails
			// emp=this.masterEmployeeDetailsRepository.findByLancesoft(empReq.getMasterEmployeeDetails().getLancesoft());

			
			if (employee.getLancesoft().equals(empReq.getMasterEmployeeDetails().getLancesoft()))
				employee.setLancesoft(empReq.getMasterEmployeeDetails().getLancesoft());

			if (employee.getLancesoft() != empReq.getMasterEmployeeDetails().getLancesoft()) {

				MasterEmployeeDetails matchEmp = this.masterEmployeeDetailsRepository
						.findByLancesoft(empReq.getMasterEmployeeDetails().getLancesoft());
				if (matchEmp == null) {

					User user = this.userRepo.findByUsername(employee.getLancesoft());
					System.out.println("=================" + employee.getLancesoft());
					user.setUsername(empReq.getMasterEmployeeDetails().getLancesoft());
					this.userRepo.save(user);
					employee.setLancesoft(empReq.getMasterEmployeeDetails().getLancesoft());
				}

				else
					throw new DuplicateEntryException("With this " + empReq.getMasterEmployeeDetails().getLancesoft()
							+ " employee id employee already exist");

			}

			employee.setLocation(empReq.getMasterEmployeeDetails().getLocation());
			employee.setPhoneNo(empReq.getMasterEmployeeDetails().getPhoneNo());
			employee.setGender(empReq.getMasterEmployeeDetails().getGender());
			employee.setVertical(empReq.getMasterEmployeeDetails().getVertical());
			;
			employee.setStatus(empReq.getMasterEmployeeDetails().getStatus());
			if (empReq.getSupervisor() > 0) {
				employee.setSupervisor(this.masterEmployeeDetailsRepository.findById(empReq.getSupervisor()).get());
			}
			if (empReq.getSupervisor() == 0) {
				employee.setSupervisor(null);
			}
			if (empReq.getSubDepartments() > 0) {
				SubDepartments subD = this.subDepartmentsRepositorye.findById(empReq.getSubDepartments()).get();
				employee.setSubDepartments(subD);
			}
			if (empReq.getSubDepartments() == 0) {
				employee.setSubDepartments(null);
			}

			if (empReq.getDepartments() > 0) {
				Departments depart = this.departmentsRepository.findById(empReq.getDepartments()).get();
				employee.setDepartments(depart);
			}
			if (empReq.getDepartments() == 0) {
				employee.setDepartments(null);
			}
			
			if (empReq.getTechnology1() > 0) {
				Technology tech = this.technologyRepository.findById(empReq.getTechnology1()).get();
				employee.setTechnology1(tech);
			}
			if (empReq.getTechnology1() == 0) {
				employee.setTechnology1(null);
			}

			if (empReq.getEmployeeType() > 0)
				employee.setDesignations(this.designationsRepository.findById(empReq.getDesignations()).get());
			employee.setEmployeeType(this.employeeTypeRepo.findById(empReq.getEmployeeType()).get());
			try {

				List<Salary> salaries = this.salaryRepository.findsBymasterEmployeeDetails_Id(employee.getEmpId());

				if (salaries.size() == 1) {// if there is only one directly setting up salary

					Optional<Salary> salary = this.salaryRepository.findById(salaries.get(0).getSalId());// with latest
																											// salary id
																											// finding
																											// the sal
					if (salary.isPresent()) {
						salary.get().setSalary(empReq.getSalary());
						this.salaryRepository.save(salary.get());
					}

				} else {

					HashMap<LocalDate, String> salaryDates = new HashMap<LocalDate, String>();// taken key,val bcz want
																								// to know from which
																								// col we're getting
																								// sals
					for (Salary s : salaries) {
						if (s.getUpdatedAt() == null) {
							salaryDates.put(s.getCreatedAt(), "Created");
						} else {
							salaryDates.put(s.getUpdatedAt(), "Updated");
						}
					}
					LocalDate maxDate = salaryDates.keySet().stream().filter(e -> e.isBefore(LocalDate.now()))
							.max(LocalDate::compareTo).orElse(null);// found latest sal date which is before current
																	// date
					String val = salaryDates.get(maxDate);// got val of latest sal key

					if (val.equals("Created")) {// using val of latest sal key,finding record with that col query mehtod
						List<Salary> currentSals = this.salaryRepository
								.findByCreatedAtAndMasterEmployeeDetails(maxDate, employee);// return list bcz ther
																							// might be multiple dame
																							// date

						Optional<Salary> salary = this.salaryRepository
								.findById(currentSals.stream().findFirst().get().getSalId());// with latest salary id
																								// finding the sal
						if (salary.isPresent()) {
							salary.get().setSalary(empReq.getSalary());
							this.salaryRepository.save(salary.get());
						}
					} else {
						List<Salary> currentSals = this.salaryRepository
								.findByUpdatedAtAndMasterEmployeeDetails(maxDate, employee);
						Optional<Salary> salary = this.salaryRepository
								.findById(currentSals.stream().findFirst().get().getSalId());// with latest salary id
																								// finding the sal
						if (salary.isPresent()) {
							salary.get().setSalary(empReq.getSalary());
							this.salaryRepository.save(salary.get());
						}
					}
					// record

//			//Salary sal=this.salaryRepository.findByMasterEmployeeDetails(employee);
//			//edge case for admin
//				Salary sal=new Salary();
//				sal.setCreatedAt(LocalDate.now());
//				sal.setSalary(empReq.getSalary());
//				sal.setMasterEmployeeDetails(employee);
//				this.salaryRepository.save(sal);
				}

			} catch (NoSuchElementException nsee) {

			}

		}
		if (empReq.getMasterEmployeeDetails().getDOB().isAfter(LocalDate.now())) {
			throw new DateMissMatchException("Date Of Birth can not be after todays date");
		}
		MasterEmployeeDetails updatedEmployee = this.masterEmployeeDetailsRepository.save(employee);
		
		Address add = this.addressRepositoy.findByEmpId(employee.getEmpId());
		if (add != null) {
			ArrayList<Address> addList = new ArrayList<Address>();

			add.setCity(empReq.getAddress().getCity());
			add.setCountry(empReq.getAddress().getCountry());
			add.setZipCod(empReq.getAddress().getZipCod());
			add.setState(empReq.getAddress().getState());
			add.setStreet(empReq.getAddress().getStreet());
			addList.add(add);
			List<Address> UpdateEmpAddress = this.addressRepositoy.saveAll(addList);
		}

		return empReq;
	}

	// UMER
	@Override
	public ClientEmpUpdateReq updateEmpClientDetails(ClientEmpUpdateReq req,int clientId) {
          
		Optional<EmployeesAtClientsDetails> details=this.employeesAtClientsDetailsRepository.findById(clientId);
		//System.out.print("============"+details);
		MasterEmployeeDetails emp=this.masterEmployeeDetailsRepository.findById(details.get().getMasterEmployeeDetails().getEmpId()).get();
		if(details.isPresent()) {
         details.get().setClientEmail(req.getClientManagerEmail());
         details.get().setClientManagerName(req.getClientManagerEmail());
         details.get().setClients(this.clientsRepository.findById(req.getClientId()).get());
         details.get().setClientSalary(req.getClientSalary());
         details.get().setDesgAtClient(req.getDesgAtClient());
         details.get().setCGST(req.getCGST());
         details.get().setIGST(req.getIGST());
         details.get().setSGST(req.getSGST());
         details.get().setTotalTax(req.getTotalTax());
       //  details.get().setTowerHead(req.getTowerHead());
         //details.get().setHandledBy(req.getHandledBy());
         details.get().setPODate(req.getPODate());
         details.get().setPOValue(req.getPOValue());
         
         Optional<EmployeesAtClientsDetails> poNumberDetals=this.employeesAtClientsDetailsRepository.findByPONumberIgnoreCase(req.getPONumber());
         
         if(poNumberDetals.isPresent()) {
        	 if(!details.equals(poNumberDetals))
 				throw new SupervisorAlreadyExistException("This PO Number Already Assigned To "
 			+poNumberDetals.get().getMasterEmployeeDetails().getFirstName()
 			+" "+poNumberDetals.get().getMasterEmployeeDetails().getLastName()
 			+" ("+poNumberDetals.get().getMasterEmployeeDetails().getLancesoft()+") for "+poNumberDetals.get().getClients().getClientsNames());
 
         }
         else {
         details.get().setPONumber(req.getPONumber());
       
         }
         
         if(req.getPoSDate().isBefore(emp.getJoiningDate())||req.getPODate().isBefore(emp.getJoiningDate()))
        	 throw new DateMissMatchException("PO start date/Po date is before employee joing date Joinging date ::(" +emp.getJoiningDate()+ ")");
         if(req.getPoEDate()!=null) {
         if(req.getPoSDate().isAfter(req.getPoEDate()))
        	 throw new DateMissMatchException("PO start date can not be after PO end date");
         }
         details.get().setPOSdate(req.getPoSDate());
         if(req.getPoEDate()==null) {
        	 //do nothing
         }else {
        	 
         details.get().setPOEdate(req.getPoEDate());
         }
         
         this.employeesAtClientsDetailsRepository.save(details.get());
		}
		
		return req;
	}

	// UMER
	@Override
	public EmployeeCompleteDetailsResponse empDetails(EmployeeCompleteDetailsResponse response, Integer empId) {
		//getting custom employee details in custom response class eg:- designation, department, and etc;
		 try {
			 DateTimeFormatter dtf=DateTimeFormatter.ofPattern("dd,MMMM,yyyy",Locale.ENGLISH);
			 Long allClientsEarning=0l;
			 
		EmployeeDetailsResponse allDetailsOfEmp = this.masterEmployeeDetailsRepository.getEmpDetailsById(empId);
		//allDetailsOfE
		EmployeeDetailResponse res=new EmployeeDetailResponse();
		res.setDepartment(allDetailsOfEmp.getDepartment());
		res.setDesignation(allDetailsOfEmp.getDesignation());
		res.setEmail(allDetailsOfEmp.getEmail());
		res.setEmpId(allDetailsOfEmp.getEmpId());
		res.setEmployeeId(allDetailsOfEmp.getEmployeeId());
		res.setEmployeeType(allDetailsOfEmp.getEmployeeType());
		res.setFirstName(allDetailsOfEmp.getFirstName());
		res.setLastName(allDetailsOfEmp.getLastName());
		res.setGender(allDetailsOfEmp.getGender());
		res.setLocation(allDetailsOfEmp.getLocation());
		
		
		
		
		
		res.setStatus(allDetailsOfEmp.getStatus());
		
		
		
		
		
		
		
		
		
		
		
		res.setSubDepartName(allDetailsOfEmp.getSubDepartName());
		res.setVertical(allDetailsOfEmp.getVertical());
		res.setPhoneNo(allDetailsOfEmp.getPhoneNo());
		res.setDob(dtf.format(allDetailsOfEmp.getDob()));
		res.setJoiningDate(dtf.format(allDetailsOfEmp.getJoiningDate()));
		
		
		//finding employee just for geting emplyee crossponding details
		MasterEmployeeDetails employe=this.masterEmployeeDetailsRepository.findById(empId).get();
		if(employe.getTechnology1()!=null)
	    res.setTechnology1(employe.getTechnology1().getTechnology());
		res.setTechnology2(employe.getTechnology2());
		List<InternalExpenses> exp= this.internalExpensesRepository.findByMasterEmployeeDetails(employe);
		List<InternalExpensesResponse> intRes=new ArrayList<InternalExpensesResponse>();
		for(InternalExpenses i: exp) {
			InternalExpensesResponse iRes=new InternalExpensesResponse();
			iRes.setBenchTenure(i.getBenchTenure());
			iRes.setInternalExpId(i.getInternalExpId());
			if(i.getTotalExpenses()!=null)
			iRes.setTotalExpenses(NumberFormat.getIntegerInstance().format(i.getTotalExpenses()));
			if(i.getProfitOrLoss()!=null)
			iRes.setProfitOrLoss(NumberFormat.getIntegerInstance().format(i.getProfitOrLoss()));
			if(i.getTotalSalPaidTillNow()!=null)
			iRes.setTotalSalPaidTillNow(NumberFormat.getIntegerInstance().format(i.getTotalSalPaidTillNow()));
			if(i.getBenchPay()!=null)
				iRes.setBenchPay(NumberFormat.getIntegerInstance().format(i.getBenchPay()));
			//if(i.getDaysOnBench()!=null)
				iRes.setDaysOnBench(i.getDaysOnBench());
			intRes.add(iRes);
		}
//		String totalExp=exp.get(0).getTotalExpenses().toString();
//	    Double total=exp.get(0).getTotalExpenses();
//	    BigDecimal exp1=new BigDecimal(total);
//        String v=NumberFormat.getIntegerInstance().format(total);
//        System.out.println("----------------------------------------------  "+v);
		List<Address> address = this.addressRepositoy.findByMasterEmployeeDetails(employe);
		
		List<EmployeesAtClientsDetails> clientDetails=this.employeesAtClientsDetailsRepository.findByMasterEmployeeDetails(employe);
		
		List<EmployeeAtClientResponse> clientDetailList=new ArrayList<EmployeeAtClientResponse>();
		
		
		
		for(EmployeesAtClientsDetails e: clientDetails) {
			EmployeeAtClientResponse clientRes=new EmployeeAtClientResponse();
		     	clientRes.setCGST(NumberFormat.getIntegerInstance().format(e.getCGST()));
				clientRes.setSGST(NumberFormat.getIntegerInstance().format(e.getSGST()));
				clientRes.setIGST(NumberFormat.getIntegerInstance().format(e.getIGST()));
		        clientRes.setTotalTax(NumberFormat.getIntegerInstance().format(e.getTotalTax()));
		        clientRes.setPOValue(NumberFormat.getIntegerInstance().format(e.getPOValue()));
		        clientRes.setClientSalary(NumberFormat.getIntegerInstance().format(e.getClientSalary()));
		        if(e.getTotalEarningAtclient()!=null) {
		             clientRes.setTotalEarningAtClient(NumberFormat.getIntegerInstance().format(e.getTotalEarningAtclient()));
		             allClientsEarning+=e.getTotalEarningAtclient().longValue();    
		        }
		        
		        clientRes.setPOSdate(dtf.format(e.getPOSdate()));
		        if(e.getClientJoiningDate()!=null)
		        	clientRes.setClientJoiningDate(dtf.format(e.getClientJoiningDate()));
		        
//		        if(e.getPOEdate()!=null) {
//		        	clientRes.setPOEdate(dtf.format(e.getPOEdate()));
//		        	  
//		        	if(e.getClientLastWorkingDate()!=null && e.getClientLastWorkingDate().isBefore(e.getPOEdate())) {
//		        		 Long ten=ChronoUnit.DAYS.between(e.getPOSdate(), e.getClientLastWorkingDate());
//							clientRes.setClientTenure(ten.toString()+" Days");
//		        	} if(e.getLancesoftLastWorkingDate()!=null && e.getLancesoftLastWorkingDate().isBefore(e.getPOEdate())) {
//		        		 Long ten=ChronoUnit.DAYS.between(e.getPOSdate(), e.getLancesoftLastWorkingDate());
//							clientRes.setClientTenure(ten.toString()+" Days");
//		        	} if(e.getLancesoftLastWorkingDate()!=null && e.getClientLastWorkingDate()!=null) {
//		        		        if( e.getLancesoftLastWorkingDate().isBefore(e.getClientLastWorkingDate())){
//		        		        	
//		        		               Long ten=ChronoUnit.DAYS.between(e.getPOSdate(), e.getLancesoftLastWorkingDate());
//							           clientRes.setClientTenure(ten.toString()+" Days");
//		        		        }
//		        		        if(e.getClientLastWorkingDate().isBefore(e.getLancesoftLastWorkingDate())) {
//		        		        	 Long ten=ChronoUnit.DAYS.between(e.getPOSdate(), e.getClientLastWorkingDate());
//							           clientRes.setClientTenure(ten.toString()+" Days");
//		        		        }
//		        	} if(e.getLancesoftLastWorkingDate()==null && e.getClientLastWorkingDate()==null) {
//		        	
//		        	 Long ten=ChronoUnit.DAYS.between(e.getPOSdate(), e.getPOEdate());
//						clientRes.setClientTenure(ten.toString()+" Days");
//		        	}
//		        	if(e.getLancesoftLastWorkingDate()!=null  && e.getLancesoftLastWorkingDate().isAfter(e.getPOEdate())) {
//			        	
//			        	 Long ten=ChronoUnit.DAYS.between(e.getPOSdate(), e.getPOEdate());
//							clientRes.setClientTenure(ten.toString()+" Days");
//			        	}if(e.getClientLastWorkingDate()!=null && e.getClientLastWorkingDate().isAfter(e.getPOEdate())) {
//			        		 Long ten=ChronoUnit.DAYS.between(e.getPOSdate(), e.getPOEdate());
//								clientRes.setClientTenure(ten.toString()+" Days");
//			        	}
//		        }
//		        else {
//		        	clientRes.setPOEdate("Present");
//		        	 Long ten=ChronoUnit.DAYS.between(e.getPOSdate(), LocalDate.now());
//		        	 clientRes.setClientTenure(ten.toString()+" Days till today");
//		        }
		        if(e.getPOEdate()!=null)
		        	clientRes.setPOEdate(dtf.format(e.getPOEdate()));
		        clientRes.setClientTenure(e.getClientTenure()+" Days");
		        if(e.getPODate()!=null)
		        	clientRes.setPODate(dtf.format(e.getPODate()));
		        if(e.getOfferReleaseDate()!=null)
		        	clientRes.setOfferReleaseDate(dtf.format(e.getOfferReleaseDate()));
		        if(e.getLancesoftLastWorkingDate()!=null)
		        	clientRes.setLancesoftLastWorkingDate(dtf.format(e.getLancesoftLastWorkingDate()));
		        if(e.getClientLastWorkingDate()!=null)
		        	clientRes.setClientLastWorkingDate(dtf.format(e.getClientLastWorkingDate()));
		       clientRes.setClientEmail(e.getClientEmail());
		       clientRes.setClientLocation(e.getClientLocation());
		       clientRes.setClientManagerName(StringUtils.capitalize(e.getClientManagerName()));
		       clientRes.setClients(e.getClients().getClientsNames());
		       if(e.getSubContractor()!=null)
		       clientRes.setSubContractor(e.getSubContractor().getClientsNames());
		       clientRes.setDesgAtClient(StringUtils.capitalize(e.getDesgAtClient()));
		       clientRes.setPONumber(e.getPONumber());
		       clientRes.setSkillSet(StringUtils.capitalize(e.getSkillSet()));
		       clientRes.setWorkMode(e.getWorkMode());
		       clientRes.setEmployee(StringUtils.capitalize(e.getMasterEmployeeDetails().getFirstName())+" "
		    		   +StringUtils.capitalize(e.getMasterEmployeeDetails().getLastName())+" ("
		    		   +e.getMasterEmployeeDetails().getLancesoft()+")");
		       clientRes.setTowerHead(StringUtils.capitalize(e.getTowerHead().getFirstName())+" "
		    		   +StringUtils.capitalize(e.getTowerHead().getLastName())+" ("
		    		   +e.getTowerHead().getLancesoft()+")");
		       clientRes.setTowerLead(StringUtils.capitalize(e.getTowerLead().getFirstName())+" "
		    		   +StringUtils.capitalize(e.getTowerLead().getLastName())+" ("
		    		   +e.getTowerLead().getLancesoft()+")");
		       clientRes.setRecruiter(StringUtils.capitalize(e.getRecruiter().getFirstName())+" "
		    		   +StringUtils.capitalize(e.getRecruiter().getLastName())+" ("
		    		   +e.getRecruiter().getLancesoft()+")");
		    
		       clientDetailList.add(clientRes);
		       
			
		}
		
		List<AtClientAllowances> attAllowances= this.allowancesOfEmployeeRepo.findByMasterEmployeeDetails(employe);
 
		response.setAtClientAllowances(attAllowances);

		
	Optional<ReleaseEmpDetails> releasedEmp=this.releaseEmpDetailsRepo.findBymasterEmployeeDetails_Id(employe.getEmpId());
	
	if(releasedEmp.isPresent()) {
	List<History> empHistory=this.historyRepository.findByLancesoft(releasedEmp.get().getMasterEmployeeDetailsId().getLancesoft());
	
	Optional<History> exitEmp=empHistory.stream().filter(e->e.getUpdatedStatus()
			.equals(UpdatedStatus.ABSCONDED) 
			||e.getUpdatedStatus().equals(UpdatedStatus.RELEASED)
			||e.getUpdatedStatus().equals(UpdatedStatus.TERMINATED))
			.findFirst();
	
	if(exitEmp.isPresent()) {
		response.setLastStatus(exitEmp.get().getStatus().toString());
		
		String releasDate=dtf.format(releasedEmp.get().getReleaseDate());
		response.setReleasedDate(releasDate);
		response.setExitType(exitEmp.get().getUpdatedStatus().toString());
	}
	}else {
		response.setLastStatus("Working");
		response.setReleasedDate("Working");
		response.setExitType("Working");
	}
	        
	    response.setAllClientsEarning(NumberFormat.getIntegerInstance().format(allClientsEarning));
		response.setDetailsResponse(res);
		response.setInternalExpenses(intRes);
		response.setAddres(address);
		response.setEmployeeAtClientsDetails(clientDetailList);
       
		List<Salary> salaries = this.salaryRepository.findsBymasterEmployeeDetails_Id(empId);

		if (salaries.size() == 1) {//if there is only one directly setting up salary	
			response.setSalary(NumberFormat.getIntegerInstance().format(salaries.get(0).getSalary()));

		} else {

			HashMap<LocalDate, String> salaryDates = new HashMap<LocalDate, String>();//taken key,val bcz want to know from which col we're getting sals
			for (Salary s : salaries) {
				if (s.getUpdatedAt() == null) {
					salaryDates.put(s.getCreatedAt(), "Created");
				} else {
					salaryDates.put(s.getUpdatedAt(), "Updated");
				}
			} 
			LocalDate maxDate = salaryDates.keySet().stream().filter(e -> e.isBefore(LocalDate.now()))
					.max(LocalDate::compareTo).orElse(null);//found latest sal date which is before current date
			String val = salaryDates.get(maxDate);//got val of latest sal key
			
			if (val.equals("Created")) {//using val of latest sal key,finding record with that col query mehtod
				List<Salary> currentSals = this.salaryRepository.findByCreatedAtAndMasterEmployeeDetails(maxDate,
						employe);//return list bcz ther might be multiple dame date
				response.setSalary(NumberFormat.getIntegerInstance().format(currentSals.stream().findFirst().get().getSalary()));//
			} else {
				List<Salary> currentSals = this.salaryRepository.findByUpdatedAtAndMasterEmployeeDetails(maxDate,
						employe);
				response.setSalary(NumberFormat.getIntegerInstance().format(currentSals.stream().findFirst().get().getSalary()));
			}

		}

	} catch (NoSuchElementException e) {
		// TODO: handle exception
	}

	return response;
}

	// UMER
	@Override
	public ClientEmpUpdateReq getClientDetailForUpdate(ClientEmpUpdateReq req, int clientDetailId) {
		Optional<EmployeesAtClientsDetails> detail=this.employeesAtClientsDetailsRepository.findById(clientDetailId);
		req.setClientManagerEmail(detail.get().getClientEmail());
		req.setClientManagerName(detail.get().getClientManagerName());
		req.setClientSalary(detail.get().getClientSalary());
		req.setDesgAtClient(detail.get().getDesgAtClient());
		req.setPoEDate(detail.get().getPOEdate());
		req.setPoSDate(detail.get().getPOSdate());
		req.setClientId(detail.get().getClients().getClientsId());
		req.setCGST(detail.get().getCGST());
		req.setSGST(detail.get().getSGST());
		req.setIGST(detail.get().getIGST());
		req.setTotalTax(detail.get().getTotalTax());
	//	req.setHandledBy(detail.get().getHandledBy());
	//	req.setTowerHead(detail.get().getTowerHead());
		req.setPODate(detail.get().getPODate());
		req.setPONumber(detail.get().getPONumber());
		req.setPOValue(detail.get().getPOValue());
		
		return req;
	}

	// UMER
	@Override
	public void assignSencondSupervisor(int empId, int secManager, boolean flag) {

		MasterEmployeeDetails employee = this.masterEmployeeDetailsRepository.findById(empId).get();

		if (empId == secManager && flag == false) {

			if (employee.getGender().equals("Male"))
				throw new SupervisorAlreadyExistException(
						"You cannot assign the same person to be his secondary manager");
			else
				throw new SupervisorAlreadyExistException(
						"You cannot assign the same person to be her secondary manager");
		}
		if (empId == secManager && flag == true) {
			return;
		}
		
		Optional<SecondaryManager> repotee=this.secondaryManagerRepo.findByEmployee(employee);
	
		if(repotee.isEmpty()) {// setting new record if not exist
		SecondaryManager sup=new SecondaryManager();
		this.masterEmployeeDetailsRepository.findById(empId).map(id->{
			sup.setEmployee(id);
			return id;
		});//set employee
		
		this.masterEmployeeDetailsRepository.findById(secManager).map(id->{
			sup.setSecondaryManager(id);
			return id;
		});//set  second supervisor
		
		this.secondaryManagerRepo.save(sup);//saved
		}

		if(repotee.isPresent() && flag==false) {//throw message
			MasterEmployeeDetails secSup=this.masterEmployeeDetailsRepository.findById(secManager).get();
			if(repotee.get().getEmployee().getGender().equals("Male")) {
				
			throw new SupervisorAlreadyExistException(" "+employee.getFirstName()+" "+employee.getLastName()+" ("+employee.getLancesoft()+") has already been assigned to "
					+repotee.get().getSecondaryManager().getFirstName()+" "+repotee.get().getSecondaryManager().getLastName()+" ("+repotee.get().getSecondaryManager().getLancesoft()+")"
							+ "; would you like to change his supervisor from "
					+repotee.get().getSecondaryManager().getFirstName()+" "+repotee.get().getSecondaryManager().getLastName()+" ("+repotee.get().getSecondaryManager().getLancesoft()+") to "
							+secSup.getFirstName()+" "+secSup.getLastName()+" ("+secSup.getLancesoft()+")?");
			}
			else if(repotee.get().getEmployee().getGender().equals("Female")) {
				throw new SupervisorAlreadyExistException(""+employee.getFirstName()+" "+employee.getLastName()+" ("+employee.getLancesoft()+") has already been assigned to "
						+repotee.get().getSecondaryManager().getFirstName()+" "+repotee.get().getSecondaryManager().getLastName()+" ("+repotee.get().getSecondaryManager().getLancesoft()+")"
								+ "; would you like to change her supervisor from "
						+repotee.get().getSecondaryManager().getFirstName()+" "+repotee.get().getSecondaryManager().getLastName()+" ("+repotee.get().getSecondaryManager().getLancesoft()+") to "
								+secSup.getFirstName()+" "+secSup.getLastName()+" ("+secSup.getLancesoft()+")?");
			}
		}
		
		if(repotee.isPresent() && flag==true) {// updating exist record
			repotee.get().setSecondaryManager(this.masterEmployeeDetailsRepository.findById(secManager).get());
			this.secondaryManagerRepo.save(repotee.get());
		}
		
		
		
	}

	@Override
	public void assignResponsibilitiesToAnEmployee(Integer empId, List<ResponsibilitiesTypes> Responsibilities) {
	
		for(ResponsibilitiesTypes r: Responsibilities) {
			EmployeeResponsibilities res=new EmployeeResponsibilities();
	     res.setMasterEmployeeDetails(this.masterEmployeeDetailsRepository.findById(empId).orElseThrow(()-> new SupervisorAlreadyExistException("Employee does not exist")));
	     res.setResponsibilitiesTypes(r);
	     this.responsibilityRepo.save(res);
		}

	}


}
