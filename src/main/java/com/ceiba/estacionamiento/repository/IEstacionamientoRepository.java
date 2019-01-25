package com.ceiba.estacionamiento.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.ceiba.estacionamiento.model.Vehiculo;

@Repository
public interface IEstacionamientoRepository extends CrudRepository<Vehiculo, Long> {
	
	@Query("SELECT est FROM Vehiculo est WHERE est.fechaSalida is null")
    public List<Vehiculo> findListVehicles();
	
	@Query("SELECT est FROM Vehiculo est WHERE est.placa = :placa")
    public Vehiculo findVehicleByPlaca(@Param("placa") String placa);
}
