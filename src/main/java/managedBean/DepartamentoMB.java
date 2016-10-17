package managedBean;

import javax.faces.bean.ManagedBean;

import model.Departamento;
import dao.DepartamentoDao;

@ManagedBean(name="departamentoMB")
public class DepartamentoMB {
	
	public Departamento d;
	
	public DepartamentoMB(){
		d = new Departamento();
	}
	public String cadastraDepartamentoMB(){
		new DepartamentoDao().inserir(d);
		return "cadastrou departamento";
	}
	
	
	public Departamento getD() {
		return d;
	}
	public void setD(Departamento d) {
		this.d = d;
	}
	
	
}
