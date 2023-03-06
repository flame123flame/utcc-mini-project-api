package UTCC.project.work.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import UTCC.project.work.model.BusVehicle;

@Repository
public interface BusVehicleRepository extends CrudRepository<BusVehicle, Long> {

}
