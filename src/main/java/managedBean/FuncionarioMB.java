package managedBean;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.bean.ManagedBean;

import model.Funcionario;
import dao.FuncionarioDao;

@ManagedBean(name="funcionarioMB")
public class FuncionarioMB {

	public Funcionario f;
	
	public FuncionarioMB(){
		f = new Funcionario();
		
	}
	public String cadastraFuncionarioMB(){
//		arrumaData();
		new FuncionarioDao().inserir(f);
		return "cadastrou funcionario";
	}
	
	public void arrumaData(){
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date d = new Date();
		f.setFunc_dt_contratacao(format.format(f.getFunc_dt_contratacao()));
		f.setFunc_dt_contratacao(format.format(f.getFunc_dt_nascimento()));
	}
	
	
	
	
	public Funcionario getF() {
		return f;
	}
	public void setF(Funcionario f) {
		this.f = f;
	}
	
	
}
