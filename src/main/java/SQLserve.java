
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.mysql.cj.jdbc.MysqlDataSource;

/**
 * Servlet implementation class SQLserve
 */
public class SQLserve extends HttpServlet {
	private Connection connection;
	private Statement statement;

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SQLserve() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("im here");
		// read a properties file
		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setURL("jdbc:mysql://localhost:3306/project4?useTimezone=true&serverTimezone=UTC");
		dataSource.setUser("client");
		dataSource.setPassword("client");
		try {
			connection = dataSource.getConnection();
			statement = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		System.out.println("also im here");
		// begin the doc
		out.println("<center>");

		String clientQuery = request.getParameter("queryArea");

		try {
			if (statement.execute(clientQuery) == false) {
				System.out.println("Yur");
				//first SQL
				Statement statementint = connection.createStatement();
				statementint.executeUpdate(clientQuery);
				
				//get the sups over 100
				Statement statement100 = connection.createStatement();
				ResultSet list100 = statement100.executeQuery("Select snum From shipments where quantity >= 100");
			
				if(list100 == null)
				{
					out.println("<head>");
					out.println("Buisness logic not detected");
					out.println("</head>");
				}
				else
				{
					out.println("<head>");
					out.println("Buisness logic detected");
					out.println("</head>");
				}
				
				//update the sups
				Statement updatesups = connection.createStatement();
				int i = 1;
				while (list100.next()) {
					statement100.executeUpdate("update suppliers\r\n"
							+ "set status = status + 5\r\n"
							+ "where snum = '" + list100.getString(i) + "'");
					i++;
				}
				
						
				// display the sups
				ResultSet resultSet = statement.executeQuery("select * from suppliers");
				ResultSetMetaData rsmd = resultSet.getMetaData();
				
				out.println("<body>");
				out.println("<table>");
				out.println("<thead>");
				out.println("<tr>");

				int columnCount = rsmd.getColumnCount();
				for (i = 1; i <= columnCount; i++) {
					out.println("<th>" + rsmd.getColumnName(i) + "</th>");
				}

				out.println("</tr>");
				out.println("</thead>");
				out.println("<tbody>");

				while (resultSet.next()) {
					out.println("<tr>");
					for (i = 1; i <= columnCount; i++) {
						out.println("<td>");
						out.print(resultSet.getString(i));
						out.println("</td>");
					}
					out.print("</tr>");
				} 

				out.println("</tbody>");
				out.println("</table>");
				resultSet.close();
			} 
			else {
				ResultSet resultSet = statement.executeQuery(clientQuery);
				ResultSetMetaData rsmd = resultSet.getMetaData();

				out.println("<body>");
				out.println("<div class='container'>");
				out.println("<table>");
				out.println("<thead>");
				out.println("<tr>");

				int columnCount = rsmd.getColumnCount();
				for (int i = 1; i <= columnCount; i++) {
					out.println("<th>" + rsmd.getColumnName(i) + "</th>");
				}

				out.println("</tr>");
				out.println("</thead>");
				out.println("<tbody>");

				while (resultSet.next()) {
					out.println("<tr>");
					for (int i = 1; i <= columnCount; i++) {
						out.println("<td>");
						out.print(resultSet.getString(i));
						out.println("</td>");
					}
					out.print("</tr>");
				} 

				out.println("</tbody>");
				out.println("</table>");
				out.println("</div>");
				resultSet.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			out.println("<head>");
			out.println("Error: <br/>" + e.getMessage());
			out.println("</head>");
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
}
