package unicauca.taller05.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import unicauca.taller05.models.EspacioFisico;

@Repository
public interface EspacioFisicoRepository extends JpaRepository<EspacioFisico, Integer> {
    @Modifying
    @Query("update EspacioFisico ef set c.nombre = :nombre where c.apellido = :apellido")
    public int actualizarCliente(
            @Param("nombre") String nombreCliente,
            @Param("apellido") String apellido);
}
