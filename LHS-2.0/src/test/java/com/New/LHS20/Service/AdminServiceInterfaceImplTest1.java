 

 package com.New.LHS20.Service;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import com.New.LHS20.Dao.LocationRepository;
import com.New.LHS20.Dao.PoliciesRepository;
import com.New.LHS20.Entity.Location;
import com.New.LHS20.Entity.Policies;
import com.New.LHS20.Service.AdminServiceInterfaceImpl;


@RunWith(SpringRunner.class)
@SpringBootTest
class AdminServiceInterfaceImplTest1 {


	 

 
@Autowired
private AdminServiceInterfaceImpl adminServiceInterfaceImpl;
//@MockBean
//private AdminRepository adminRepository;
@MockBean
private LocationRepository locationRepository;
@MockBean
private PoliciesRepository policiesRepository;


@Test
public void getLocations() {
List<Location> location=new ArrayList();location.add(new Location(1,"Hyderabad"));
when(locationRepository.findAll()).thenReturn( location);
assertEquals(location,locationRepository.findAll());
}

@Test
public void AddLocations() {
List<Location> location= new ArrayList();
location.add(new Location(1,"Hyderabad"));
when(locationRepository.saveAll(location)).thenReturn( location);
assertEquals(location,locationRepository.saveAll(location));
}

@Test
public void Addpolicy() {
List<Policies> policy= new ArrayList();
policy.add(new Policies(1,"policy are here",LocalDate.now(), LocalTime.now()));
when(policiesRepository.saveAll(policy)).thenReturn( policy);
assertEquals(policy,policiesRepository.saveAll(policy));
}

@Test
public void getpolicy() {
List<Policies> policy= new ArrayList();
policy.add(new Policies(1,"policy are here",LocalDate.now(), LocalTime.now()));
when(policiesRepository.findAll()).thenReturn( policy);
assertEquals(policy,policiesRepository.findAll());
}


}



