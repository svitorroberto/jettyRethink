package dao;

import java.util.ArrayList;

import model.Cargo;
import model.Funcionario;

import com.rethinkdb.RethinkDB;
import com.rethinkdb.net.Connection;
import com.rethinkdb.net.Cursor;

public class CargoDao {
	public static final RethinkDB r = RethinkDB.r;
	private ArrayList<Cargo> cargos = new ArrayList<Cargo>();
	
	public ArrayList<Cargo> getCargos() {
		return cargos;
	}

	public void setCargos(ArrayList<Cargo> cargos) {
		this.cargos = cargos;
	}

	public void inserir(Cargo c) {
			Connection conn = r.connection().hostname("localhost").port(28015).connect();
			r.table("cargo").insert(
					r.array(
					
							r.hashMap("cargo_descricao", c.getCargo_descricao()).with("cargo_nivel", c.getCargo_nivel())
				 )).run(conn);
	}

	public void deletar() {
		Connection conn = r.connection().hostname("localhost").port(28015).connect();
		r.table("cargo").filter(row -> row.g("cargo_descricao")).delete().run(conn);
	}

	public void atualizar(Cargo c) {
		Connection conn = r.connection().hostname("localhost").port(28015).connect();
		r.table("departamento").update(
				r.array(
						
						r.hashMap("cargo_descricao", c.getCargo_descricao()),
						r.hashMap("cargo_nivel", c.getCargo_nivel())		     
			 )).run(conn);
	}

	public ArrayList<Cargo> listar() {
		Connection conn = r.connection().hostname("localhost").port(28015).connect();
		Cursor cursor = r.table("cargo").run(conn);

		for (Object doc : cursor) {
		    cargos.add((Cargo)doc);
		}
		return cargos;
	}

	public void buscar(Cargo c) {
		Connection conn = r.connection().hostname("localhost").port(28015).connect();
		Cursor cursor = r.table("cargo").filter(row -> row.g("cargo_descricao").eq(c.getCargo_descricao())).run(conn);
		for (Object doc : cursor) {
		    System.out.println(doc);
		}
	}
}
