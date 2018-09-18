package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import db.PoolConnection;
import modelo.Reclamo;

public class ReclamoMapper {
	
	private static ReclamoMapper instancia;
	private static Connection con;
	
	public static ReclamoMapper getInstancia(){
		if(instancia == null){
			instancia = new ReclamoMapper();
		}
		con = PoolConnection.getInstance().getConnection();
		return instancia;
	}

	public Reclamo getReclamoById(int idReclamo){
		Reclamo recuperado = null;
		ResultSet resultado = null;
		try {
			PreparedStatement s = con.prepareStatement("SELECT * FROM RECLAMOS WHERE id_reclamo = ");
			resultado = s.executeQuery();
			if(resultado.next()){
				recuperado = new Reclamo();
				recuperado.setNumeroReclamo(resultado.getInt(1));
				recuperado.setDescripcion(resultado.getString(2));
				recuperado.setEstado(resultado.getString(3));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return recuperado;
	}
	
	public List<Reclamo> getAll(){
		List <Reclamo> reclamos = new ArrayList<Reclamo>();
		Reclamo reclamo = null;
		ResultSet resultado = null;
		try {
			PreparedStatement s = con.prepareStatement("SELECT * FROM RECLAMOS");
			resultado = s.executeQuery();
			while(resultado.next()){
				reclamo = new Reclamo();
				reclamo.setNumeroReclamo(resultado.getInt(1));
				reclamo.setDescripcion(resultado.getString(2));
				reclamo.setEstado(resultado.getString(3));
				reclamos.add(reclamo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reclamos;
	}
	
	public void save(Reclamo rec){
		try {
			PreparedStatement s = con.prepareStatement("INSERT INTO reclamos (descripcion,estado) values (?,?)");
			s.setString(1, rec.getDescripcion());
			s.setString(2, rec.getEstado());
			s.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update(Reclamo rec){
		try {
			PreparedStatement s = con.prepareStatement("UPDATE reclamos SET descripcion = ?, estado = ? where id_reclamo = ?");
			s.setString(1, rec.getDescripcion());
			s.setString(2, rec.getEstado());
			s.setInt(3, rec.getNumeroReclamo());
			s.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
