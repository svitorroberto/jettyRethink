package dao;

import java.util.ArrayList;

import model.Departamento;

import com.rethinkdb.RethinkDB;
import com.rethinkdb.net.Connection;
import com.rethinkdb.net.Cursor;

public class DepartamentoDao {
	public static final RethinkDB r = RethinkDB.r;
	public void inserir(Departamento d) {
		Connection conn = r.connection().hostname("localhost").port(28015).connect();
		r.table("departamento").insert(
				r.array(
				
						r.hashMap("depto_descricao", d.getDepto_descricao()).with("depto_ramal", d.getDepto_ramal())
			 )).run(conn);
		
		
	}

	public void deletar() {
		Connection conn = r.connection().hostname("localhost").port(28015).connect();
		r.table("departamento").filter(row -> row.g("posts").count().lt(3)).delete().run(conn);

	}

	public void atualizar(Departamento d) {
		
		Connection conn = r.connection().hostname("localhost").port(28015).connect();
		r.table("departamento").update(
				r.array(
						
						r.hashMap("depto_descricao", d.getDepto_descricao()),
						r.hashMap("depto_ramal", d.getDepto_ramal())	     
			 )).run(conn);

	}

	public ArrayList<Departamento> listar() {
		Connection conn = r.connection().hostname("localhost").port(28015).connect();
		Cursor cursor = r.table("departamento").run(conn);
		ArrayList<Departamento> funcionarios = new ArrayList<Departamento>();
		for (Object doc : cursor) {
		    funcionarios.add((Departamento)doc);
		}
		return funcionarios;
	}

	public void buscar(Departamento d) {
		Connection conn = r.connection().hostname("localhost").port(28015).connect();
		Cursor cursor = r.table("departamento").filter(row -> row.g("dpto_descricao").eq(d.getDepto_descricao())).run(conn);
		for (Object doc : cursor) {
		    System.out.println(doc);
		}
	}

}
