package com.batteryController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.spec.PSource;

import com.batterybean.BeanClass;

public class BatteryDao {
	String databaseurl = "jdbc:mysql://localhost:3306/battery";
	String databaseuser = "root";
	String databasepass = "root";

	private static String selectall = "SELECT * FROM users";
	private static String selectqueery = "select * from users where userid=? and userpass=?";
	private static String selectqueery1 = "select * from users where userid=? and username=?";
	private static String insertqueery = "insert into users(username,usernumber,usermail,userpass,usergen) values(?,?,?,?,?)";
	private static String updatequeery = "UPDATE users SET  username=?,usernumber=?,usermail=?,userpass=?,usergen=? WHERE  userid= ?";
	private static String selectadmin = "select * from admin where adminid=? and adminpass=?";

	public Connection getConnection() throws SQLException, ClassNotFoundException {

		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(databaseurl, databaseuser, databasepass);
		System.out.println("con done");
		return con;

	}

	public boolean getadmin(BeanClass bean) {
		Connection con = null;
		boolean res = false;
		try {
			con = getConnection();
			PreparedStatement ps = con.prepareStatement(selectadmin);
			ps.setInt(1, bean.getAdminid());
			ps.setString(2, bean.getAdminpass());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				res = true;
				System.out.println("admin found");
			}
			else {
				System.out.println("admin not found");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public boolean deleteuser(int userid) {
		Connection con = null;
		boolean res = false;
		try {
			con = getConnection();
			Statement s = con.createStatement();
			int count = s.executeUpdate("DELETE FROM  users  WHERE  userid=" + userid + "");
			if (count > 0) {
				res = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public List<BeanClass> getalluser() {
		Connection con = null;
		List<BeanClass> beans = new ArrayList<BeanClass>();
		try {
			con = getConnection();
			PreparedStatement ps = con.prepareStatement(selectall);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				BeanClass bean = new BeanClass();
				bean.setId(rs.getInt(1));
				bean.setName(rs.getString(2));
				bean.setNumber(rs.getInt(3));
				bean.setEmail(rs.getString(4));
				bean.setPass(rs.getString(5));
				bean.setGender(rs.getString(6));

				beans.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return beans;
	}

	public BeanClass getUser(int userid, String pass) {
		Connection con = null;
		BeanClass bean = new BeanClass();
		try {
			con = getConnection();
			PreparedStatement ps = con.prepareStatement(selectqueery1);
			System.out.println(userid + " " + pass);
			ps.setInt(1, userid);
			ps.setString(2, pass);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				bean.setId(rs.getInt("userid"));
				bean.setName(rs.getString("username"));
				bean.setNumber(rs.getInt("usernumber"));
				bean.setEmail(rs.getString("usermail"));
				bean.setPass(rs.getString("userpass"));
				bean.setGender(rs.getString("usergen"));

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return bean;
	}

	public boolean isValoid(int userid, String pass) {
		Connection con = null;
		boolean isvalid = false;
		try {
			con = getConnection();
			PreparedStatement ps = con.prepareStatement(selectqueery);
			System.out.println(userid + " " + pass);
			ps.setInt(1, userid);
			ps.setString(2, pass);

			ResultSet rs = ps.executeQuery();
			isvalid = rs.next();
			System.out.println(rs.getString(1));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return isvalid;
	}

	public boolean userRegister(BeanClass bean) {
		Connection con = null;
		boolean res = false;
		try {
			con = getConnection();
			PreparedStatement ps = con.prepareStatement(insertqueery);
			ps.setString(1, bean.getName());
			ps.setInt(2, bean.getNumber());
			ps.setString(3, bean.getEmail());
			ps.setString(4, bean.getPass());
			ps.setString(5, bean.getGender());
			int out = ps.executeUpdate();
			System.out.println(out);
			if (out > 0) {
				res = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public boolean userupdate(BeanClass bean) {
		Connection con = null;
		boolean res = false;
		try {
			con = getConnection();
			System.out.println(bean.getName() + "" + bean.getId() + "" + bean.getNumber() + "" + bean.getEmail() + ""
					+ bean.getPass() + "" + bean.getGender());
			PreparedStatement ps = con.prepareStatement(updatequeery);

			ps.setString(1, bean.getName());
			ps.setInt(2, bean.getNumber());
			ps.setString(3, bean.getEmail());
			ps.setString(4, bean.getPass());
			ps.setString(5, bean.getGender());
			ps.setInt(6, bean.getId());
			int out = ps.executeUpdate();
			System.out.println(out);
			if (out > 0) {
				res = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;

	}
}
