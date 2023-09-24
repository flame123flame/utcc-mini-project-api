package UTCC.project.work.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import UTCC.project.work.model.BusVehicle;

@Repository
public interface BusVehicleRepository extends CrudRepository<BusVehicle, Long> {

	
	 @Query(value = "SELECT * FROM bus_vehicle bv WHERE bv.bus_vehicle_plate_no = ?1", nativeQuery = true)
	  public BusVehicle findByBusVehiclePlateNo(String no);
}
