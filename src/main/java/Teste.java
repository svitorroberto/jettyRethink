import com.rethinkdb.RethinkDB;
import com.rethinkdb.net.Connection;

public class Teste {

	public static final RethinkDB r = RethinkDB.r;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection conn = r.connection().hostname("localhost").port(28015).connect();
		r.db("test").tableCreate("cargo").run(conn);
		r.db("test").tableCreate("departamento").run(conn);
		r.db("test").tableCreate("funcionario").run(conn);
		
	}

}
