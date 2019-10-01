
public interface Repository extends JpaRepository<Foo,Long>{
	
	//Finding foo by id
	public Foo findById(Long id);
	
	//Deleting by id
	public void deleteById(Long id);
	
}
