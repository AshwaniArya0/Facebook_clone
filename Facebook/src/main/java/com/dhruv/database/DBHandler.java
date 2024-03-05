package com.dhruv.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.dhruv.beans.Friend;
import com.dhruv.beans.User;
import com.dhruv.beans.Wpost;

public class DBHandler {
	
	
/*First Step
 * Database connectivity
 *
 */
	
	Connection cn;
	public Connection getConnection() {
		Connection cn=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			cn=DriverManager.getConnection("jdbc:mysql://localhost/facebook?user=root&password=root");
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return cn;
	}

	/* 
	 * For saving data in user table 
	 * 
	 */
	
	
	public String Save(User user) {
		String message="";
		try {
			String query="insert into user values(?,?,?)";
			Connection cn=getConnection();
			PreparedStatement statement=cn.prepareStatement(query);
			statement.setString(1, user.getEmail());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getName());
			statement.executeUpdate();
			cn.close();
			message="success";
			
		}
		catch (Exception e) {
			// TODO: handle exception
			message=e.getMessage();
		}
		return message;
	}
	
	
	

	
	

	/* 
	 * For saving data in Friend table 
	 * 
	 */
	
	public String Save(Friend friend) {
		String message="";
		try {
			String query="insert into friends(sender,rec,stat) values(?,?,?)";
			Connection cn=getConnection();
			PreparedStatement statement=cn.prepareStatement(query);
			statement.setString(1, friend.getSender());
			statement.setString(2, friend.getRec());
			statement.setInt(3, friend.getStat());
			statement.executeUpdate();
			cn.close();
			message="success";
			
		}
		catch (Exception e) {
			// TODO: handle exception
			message=e.getMessage();
		}
		return message;
	}
	
	/* 
	 * For saving data in wpost table 
	 * 
	 */
	
	public String Save(Wpost wpost) {
		String message="";
		try {
			String query="insert into wpost(sender,msg,dop) values(?,?,?)";
			Connection cn=getConnection();
			PreparedStatement statement=cn.prepareStatement(query);
			statement.setString(1, wpost.getSender());
			statement.setString(2, wpost.getMsg());
			statement.setString(3, wpost.getDop());
			statement.executeUpdate();
			cn.close();
			message="success";
			
		}
		catch (Exception e) {
			// TODO: handle exception
			message=e.getMessage();
		}
		return message;
	}
	
	
	public ArrayList<Wpost> getposts(String email){

	ArrayList<Wpost> posts = new ArrayList<>();
	ArrayList<Friend> friends = getFriends(email);
	ArrayList<String> sfriends = new ArrayList<>();
	
	for(Friend friend : friends)
	{
		if(friend.getRec().equals(email))
			sfriends.add(friend.getSender());
		else
			sfriends.add(friend.getRec());
	}
	sfriends.add(email);
	try {
		
		String query = "select * from wpost order by wid desc";
		Connection cn = getConnection();
		PreparedStatement statement = cn.prepareStatement(query);
		ResultSet rs = statement.executeQuery();
	    while(rs.next())
	    {    int wid = rs.getInt(1);
	         String sender = rs.getString(2);
	         String msg = rs.getString(3);
	         String dop = rs.getString(4);
	    	Wpost wpost = new Wpost(wid,sender,msg,dop);
	    	if(sfriends.contains(sender))
	    	posts.add(wpost);
	    }
		cn.close();
	}
	catch(Exception e) {
		System.out.print(e.getMessage());
	}
	return posts;

	}
	
	
	public ArrayList<User> getProfile(String email) {
	    ArrayList<User> users = new ArrayList<>();
	    ArrayList<Friend> friends = getFriends(email);
	    ArrayList<String> susers = new ArrayList<>();
	    
	    // Add the email addresses of friends to susers
	    for (Friend friend : friends) {
	        susers.add(friend.getSender());
	    }
	    // Add the user's email to susers
	    susers.add(email);

	    try {
	        String query = "SELECT * FROM user WHERE email=?";
	        Connection cn = getConnection();
	        PreparedStatement statement = cn.prepareStatement(query);
	        statement.setString(1, email);
	        ResultSet rs = statement.executeQuery();
	        
	        while (rs.next()) {
	            String mail = rs.getString(1);
	            String password = rs.getString(2);
	            String name = rs.getString(3);
	            User user = new User(mail, password, name);
	            
	            // Check if the email of the user is in susers
	            if (susers.contains(mail)) {
	                users.add(user); // Add the User object to the users list
	            }
	        }
	        
	        // Close resources
	        rs.close();
	        statement.close();
	        cn.close();
	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	    }
	    
	    return users;
	}

	
	/* 
	 * For Checking user is valid or not from the user table 
	 * 
	 */
	
	public String checkUser(User user) {
		String message="";
		try {
			String query="select * from user where email=? and password=?";
			Connection cn=getConnection();
			PreparedStatement statement=cn.prepareStatement(query);
			statement.setString(1, user.getEmail());
			statement.setString(2, user.getPassword());
			
			ResultSet rs=statement.executeQuery();
			if (rs.next()) {
				message="success";
			}
			else
				message="invalid user";
			cn.close();
			
			
		}
		catch (Exception e) {
			// TODO: handle exception
			message=e.getMessage();
		}
		return message;
	}
	
	/* 
	 * For Getting user name from email 
	 * 
	 */
	
	public String getUserName(String email) {
		String name="";
		try {
			String query="select * from user where email=? ";
			Connection cn=getConnection();
			PreparedStatement statement=cn.prepareStatement(query);
			statement.setString(1, email);
			
			ResultSet rs=statement.executeQuery();
			if (rs.next()) {
				name=rs.getString(3);
			}
			cn.close();			
		}
		catch (Exception e) {
			// TODO: handle exception
			name=e.getMessage();
		}
		return name;
	}
	
	/* 
	 * For Getting pending request 
	 * 
	 */
	
	public ArrayList<Friend> getPendingRequest(String rec){
		ArrayList<Friend> prequest=new ArrayList<Friend>();
		try {
			String query="select * from friends where rec=? and stat=0 order by fid desc ";
			Connection cn=getConnection();
			PreparedStatement statement=cn.prepareStatement(query);
			statement.setString(1, rec);			
			ResultSet rs=statement.executeQuery();
			while (rs.next()) {
				int fid=rs.getInt(1);
				String sender=rs.getString(2);
				String trec=rs.getString(3);
				int stat=rs.getInt(4);
				Friend friend=new Friend(fid, sender, trec, stat);
				prequest.add(friend);
			}
			cn.close();	
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return prequest;	
	}
	public String changeStatus(int fid ,int stat) {
		String message="";
		try {
			String query="update friends set stat=? where fid=?";
			Connection cn=getConnection();
			PreparedStatement statement=cn.prepareStatement(query);
			statement.setInt(1, stat);
			statement.setInt(2, fid);
			statement.executeUpdate();
			message="Update";
			cn.close();
			
			
		}
		catch (Exception e) {
			// TODO: handle exception
			message=e.getMessage();
		}
		return message;
	}
	
	public ArrayList<Friend> getFriends(String rec){
		ArrayList<Friend> friends=new ArrayList<Friend>();
		try {
			String query="select * from friends where (rec=? or sender=?) and stat=1 order by fid desc ";
			Connection cn=getConnection();
			PreparedStatement statement=cn.prepareStatement(query);
			statement.setString(1, rec);			
			statement.setString(2, rec);			
			ResultSet rs=statement.executeQuery();
			while (rs.next()) {
				int fid=rs.getInt(1);
				String sender=rs.getString(2);
				String trec=rs.getString(3);
				int stat=rs.getInt(4);
				Friend friend=new Friend(fid, sender, trec, stat);
				friends.add(friend);
			}
			cn.close();	
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return friends;	
	}

	
	
}

