package db03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class TestTUser {
	// 연결문자열
	private static String driver   = "oracle.jdbc.OracleDriver";
	private static String url      = "jdbc:oracle:thin:@localhost:1521:xe";
	private static String dbuid    = "sky";
	private static String dbpwd    = "1234";
	
	static Scanner in = new Scanner(System.in);

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		
		// CRUD 예제, Create, Read, Update, Delete
		do {
			// 화면 출력
			System.out.println("=============================");
			System.out.println("          회원정보           ");
			System.out.println("=============================");
			System.out.println("1. 회원 목록");
			System.out.println("2. 회원 조회");
			System.out.println("3. 회원 추가");
			System.out.println("4. 회원 수정");
			System.out.println("5. 회원 삭제");
			System.out.println("Q. 종료");
			
			System.out.println("선택:");
			String choice = in.nextLine();
			
			TUserDTO tuser = null;
			int aftcnt;
			String uid = "";
			
			switch( choice ) {
			case "1": ArrayList<TUserDTO> userList = getTUserList(); 
	                  displayList( userList );
				      break;
			case "2": System.out.println("조회할 아이디를 입력하세요");
				      uid = in.nextLine();
				      tuser = getTUser(uid);
				      // System.out.println( tuser.toString() );
			          display(tuser); 
			          break;
			case "3": tuser = inputData(); 
			          aftcnt = addTUser( tuser );
			          System.out.println(aftcnt + "건 저장되었습니다"); 
			          break;
			case "4": tuser = inputData2();
			          aftcnt = whiteUser( tuser );
				      break; // 4. 회원 수정
			case "5": System.out.println("삭제할 아이디를 입력하세요");
			          uid = in.nextLine();
			          tuser = deleteTUser(uid);
				      break; // 5. 회원 삭제
			case "Q": System.out.println("프로그램을 종료합니다");
			          System.exit(0);
			          break;
			}
			
		} while( true ); // 무한반복 : 무한루프
		
	}



	



















	// 1. DB 에서 전체 목록 조회
	private static ArrayList<TUserDTO> getTUserList() throws ClassNotFoundException, SQLException {
		Class.forName( driver );
		Connection          conn     = DriverManager.getConnection(url, dbuid, dbpwd);
		String              sql      = " SELECT * FROM TUSER ";
		sql                         += " ORDER BY ID ASC ";
		PreparedStatement   pstmt    = conn.prepareStatement(sql);
		ResultSet           rs       = pstmt.executeQuery();
		
		ArrayList<TUserDTO> userList = new ArrayList<>();
		
		while( rs.next() ) {
			String          id       = rs.getString("id");
			String          name     = rs.getString("name");
			String          email    = rs.getString("email");
			TUserDTO        tuser    = new TUserDTO(id, name, email);
			userList.add( tuser );
		}
		
		rs.close();
		pstmt.close();
		conn.close();
		
		return userList;
	}

	// 2. 입력받은 아이디로 한줄을 DB에서 조회한다.
	private static TUserDTO getTUser(String uid) throws ClassNotFoundException, SQLException {
		Class.forName( driver );
		Connection conn = DriverManager.getConnection(url, dbuid, dbpwd);
		
		String sql = "";
		sql += "SELECT * FROM TUSER WHERE ID = ?"; // 물음표는 작은따옴표로 둘러싸지 않는다
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, uid.toUpperCase() ); // sky 앞뒤에 자동으로 작은 따옴표를 붙여주기 때문에 "'sky'" 이렇게 적으면 오류가 난다.
		
		TUserDTO tuser = null;
		
		ResultSet rs = pstmt.executeQuery();
		if ( rs.next() ) {
			String userid   = rs.getString("id");
			String username = rs.getString("name");
			String email    = rs.getString("email");
			
			tuser  = new TUserDTO(userid, username, email);
			
		} else {
			
		}
		
		pstmt.close();
		conn.close();
		
		return tuser;
	}
	/*
	private static void showData() throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(url, dbuid, dbpwd);
		
		String sql = "";
		sql += "SELECT * FROM TUSER WHERE ID = '?'";
		PreparedStatement pstmt = conn.prepareStatement( sql ); // 여기까지는 바로 열고 닫은거란다.
		
		pstmt.close();
		conn.close();
		
	}
	*/

	// 3. DB에 insert 한다.
	private static int addTUser(TUserDTO tuser) throws SQLException, ClassNotFoundException {
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(url, dbuid, dbpwd);
		
		String sql = "";
		sql += "INSERT INTO TUSER VALUES(?, ?, ?)";
		PreparedStatement pstmt = conn.prepareStatement( sql );
		pstmt.setString(1, tuser.getId());
		pstmt.setString(2, tuser.getName());
		pstmt.setString(3, tuser.getEmail());
		
		int aftcnt = pstmt.executeUpdate();
		
		pstmt.close();
		conn.close(); // Connection 했으니까 닫아라
		
		return  aftcnt;
		
	}
	
	// 삭제
	private static TUserDTO deleteTUser(String uid) throws ClassNotFoundException, SQLException {
		Class.forName( driver );
		Connection conn = DriverManager.getConnection(url, dbuid, dbpwd);
		
		String sql = "DELETE FROM TUSER WHERE ID = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, uid.toUpperCase() );
		
		TUserDTO tuser = null;
		
		ResultSet rs = pstmt.executeQuery();
		if ( rs.next() ) {
			String userid   = rs.getString("id");
			String username = rs.getString("name");
			String email    = rs.getString("email");
			
			tuser  = new TUserDTO(userid, username, email);
			
		} else {
			
		}
		
		pstmt.close();
		conn.close();
		
		return tuser;
	}
	// 데이터를 수정한다.
	private static int whiteUser(TUserDTO tuser) throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(url, dbuid, dbpwd);
		
		String sql = "";
		sql += "UPDATE TUSER SET EMAIL = ?, NAME  = ? WHERE ID = ?";
		PreparedStatement pstmt = conn.prepareStatement( sql );
		pstmt.setString(1, tuser.getEmail());
		pstmt.setString(2, tuser.getName());
		pstmt.setString(3, tuser.getId());
		
		int aftcnt = pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
		
		return aftcnt;
	}

	// 데이터를 키보드로 입력받는다
	private static TUserDTO inputData() {
		System.out.println("아이디:");
		String id = in.nextLine();
		System.out.println("이름:");
		String name = in.nextLine();
		System.out.println("이메일:");	
		String email = in.nextLine();
		
		TUserDTO tuser = new TUserDTO(id, name, email);
		return   tuser;
	}
	
	// 수정할 데이터를 키보드로 입력받는다
	private static TUserDTO inputData2() {
		System.out.println("수정 대상인 아이디를 입력하세요:");
		String id = in.nextLine();
		System.out.println("이름:");
		String name = in.nextLine();
		System.out.println("이메일:");	
		String email = in.nextLine();
		
		TUserDTO tuser = new TUserDTO(id, name, email);
		return   tuser;
	}
	
	// TUser 한줄을 출력한다.
	private static void display(TUserDTO tuser) {
		if ( tuser == null ) {
			System.out.println("조회한 자료가 없습니다. 아이디를 확인하세요");
		} else {
			String msg = String.format( "%s %s %s", tuser.getId(), tuser.getName(), tuser.getEmail() );
			System.out.println(msg);
		}
		
	}
	
	// 전체 목록을 출력한다.
	private static void displayList(ArrayList<TUserDTO> userList) {
		if( userList.size() == 0 ) {
			System.out.println("조회한 자료가 없습니다.");
			return;
		}
		String fmt = "";
		String msg = "";
		for (TUserDTO tuser : userList) {
			String id    = tuser.getId();
			String name  = tuser.getName();
			String email = tuser.getEmail();
			msg = """
			%s %s %s      
			""".formatted(id, name, email); // java template 문자열
			System.out.print( msg );
		}
		System.out.println("Press enter key ...");
		in.nextLine();

	}

}



















