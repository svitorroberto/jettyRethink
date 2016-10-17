package managedBean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import dao.CargoDao;
import model.Cargo;




@ManagedBean(name="cargoMB")
public class CargoMB {

	public Cargo c;	
	private ArrayList<Cargo> cargos;
	private CargoDao cdao = new CargoDao();
	
	public CargoMB(){
		c = new Cargo();
	}
	@PostConstruct
	public void init(){
		cargos = cdao.listar();
	}
	
	public String CadastraCargoMB(){
		new CargoDao().inserir(c);
		return "Cadastrou";
	}
	public List<Cargo> getCargos(){
		return cargos;
	}
	public void deletaCargos(){
		
	}
	public Cargo getC() {
		return c;
	}

	public void setC(Cargo c) {
		this.c = c;
	}
	
}
