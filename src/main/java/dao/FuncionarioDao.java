package dao;

import java.awt.List;
import java.util.ArrayList;

import model.Funcionario;

import com.rethinkdb.RethinkDB;
import com.rethinkdb.net.Connection;
import com.rethinkdb.net.Cursor;

public class FuncionarioDao {
	
	
	public static final RethinkDB r = RethinkDB.r;
	public void inserir(Funcionario f) {
		Connection conn = r.connection().hostname("localhost").port(28015).connect();
		r.table("funcionario").insert(
				r.array(
						
						r.hashMap("func_nome", f.getFunc_nome()
								).with("func_cpf", f.getFunc_cpf()
								).with("func_dt_nascimento", f.getFunc_dt_nascimento()
								).with("func_dpto", f.getFunc_dpto()
								).with("func_dt_contratacao", f.getFunc_dt_contratacao()
								).with("func_salario", f.getFunc_salario()
								).with("func_cargo", f.getFunc_cargo())			     
			 )).run(conn);
		

	}

	public void deletar() {
		Connection conn = r.connection().hostname("localhost").port(28015).connect();
		r.table("funcionario").filter(row -> row.g("posts").count().lt(3)).delete().run(conn);
	}

	public void atualizar(Funcionario f) {
		Connection conn = r.connection().hostname("localhost").port(28015).connect();
		r.table("departamento").update(
				r.array(
						
						r.hashMap("func_nome", f.getFunc_nome()),
						r.hashMap("func_cpf", f.getFunc_cpf()),
						r.hashMap("func_dt_nascimento", f.getFunc_dt_nascimento()),
						r.hashMap("func_dpto", f.getFunc_dpto()),
						r.hashMap("func_dt_contratacao", f.getFunc_dt_contratacao()),
						r.hashMap("func_salario", f.getFunc_salario()),
						r.hashMap("func_cargo", f.getFunc_cargo())			     
			 )).run(conn);
	}

	public ArrayList<Funcionario> listar() {
		Connection conn = r.connection().hostname("localhost").port(28015).connect();
		Cursor cursor = r.table("funcionario").run(conn);
		ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();
		for (Object doc : cursor) {
		    funcionarios.add((Funcionario)doc);
		}
		return funcionarios;
	}

	public void buscar(Funcionario f) {
		Connection conn = r.connection().hostname("localhost").port(28015).connect();
		Cursor cursor = r.table("funcionario").filter(row -> row.g("func_nome").eq(f.getFunc_nome())).run(conn);
		for (Object doc : cursor) {
		    System.out.println(doc);
		}
	}

}
