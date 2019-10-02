package #####;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/* import ${Repository} Entity here */

@Repository
public interface ${Repository}Repository extends JpaRepository<${Repository},Long>{
	
	//Finding foo by id
	public ${Repository} findById(Long id);
	
	//Deleting by id
	public void deleteById(Long id);
	
}
