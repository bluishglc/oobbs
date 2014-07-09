package oobbs.domainmodel;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ReplacementDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.XmlDataSet;
import org.dbunit.ext.mysql.MySqlDataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.hibernate.impl.SessionFactoryImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import testutil.ApplicationContextSupport;


/**
 * For each test class,we specify that the test class just work on one data set and for all test methods,
 * there are same initial database status.If you have a test method which works on another data set or need
 * a different database initial stattus,please move the method the an independent test class.  
 * 
 * @author Laurence Geng
 */
public abstract class DatabaseBasedTest  extends ApplicationContextSupport{

	protected IDataSet dataset;
	
    @Autowired
    protected DataSource dataSource;
    
    protected IDatabaseConnection connection;
	
    protected List<DatabaseOperation> beforeTestDbOperations;

    protected List<DatabaseOperation> afterTestDbOperations;


	/**
	 * Be
	 * @throws Exception 
	 * 
	 * @throws Exception
	 */
	//@BeforeClass
	public void prepareSetting() throws Exception {
		
		dataset=getDataSet();
		if(dataset==null){
			throw new NullPointerException("The data set is null!");
		}
		connection=getConnection();
		if(connection==null){
			throw new NullPointerException("The database connection is null!");
		}
		beforeTestDbOperations=getBeforeTestDbOperations();
		if(beforeTestDbOperations==null){
			throw new NullPointerException("The data set is null!");
		}
		afterTestDbOperations=getAfterTestDbOperations();
		if(afterTestDbOperations==null){
			throw new NullPointerException("The data set is null!");
		}
	}
	


	@Before
	public void beforeTestMethod() {
		try {
			prepareSetting();
			for (DatabaseOperation op : beforeTestDbOperations) {
				op.execute(getConnection(), dataset);
			}
		} catch (Exception ex) {
			throw new RuntimeException("Couldn't execute dababase operations before test method", ex);
		}
	}

	/**
	 * Actrully,this method is not required,because before each test mothod
	 * invoked,it will clean the dataset and reinsert them.see
	 * beforeTestMesthod().however,if no this method,after the last test
	 * finished,the test data will remain the database.
	 * 
	 * @throws Exception
	 */
	@After
	public void afterTestMethod() {
		try {
			for (DatabaseOperation op : afterTestDbOperations) {
				op.execute(getConnection(), dataset);
			}
		} catch (Exception ex) {
			throw new RuntimeException("Couldn't execute dababase operations after test method", ex);
		}
	}
	
	
	/**
	 * The subclass can override this function so as to add or remove
	 * corresponding database operations.
	 * @throws Exception 
	 * @throws  
	 * @throws IOException 
	 * @throws DataSetException 
	 */
	protected IDataSet getDataSet() throws Exception {
		IDataSet dataSet = new XmlDataSet(new ClassPathResource(
				"dbunit-test-data.xml").getInputStream());
		return dataSet;
	}

	/**
	 * Subclasses can/have to override the following methods
	 * @throws Exception 
	 */
	protected IDatabaseConnection getConnection() throws Exception {

		try {
			// Get a JDBC connection from Hibernate
			Connection con = dataSource.getConnection();
			// Disable foreign key constraint checking
			// This really depends on the DBMS product... here for HSQL DB
			con.prepareStatement("set @@foreign_key_checks = 0").execute();
			IDatabaseConnection dbUnitCon = new DatabaseConnection(con);
			// TODO: Remove this once DBUnit works with the latest HSQL DB
			DatabaseConfig config = dbUnitCon.getConfig();
			config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new MySqlDataTypeFactory());
			return dbUnitCon;
		} catch (SQLException e) {
			throw new RuntimeException("Couldn't get daba set", e);
		}
	}
	
	protected List<DatabaseOperation> getAfterTestDbOperations() {
		List<DatabaseOperation> operations=new ArrayList<DatabaseOperation>();
		operations.add(DatabaseOperation.NONE);
		return operations;
	}

	protected List<DatabaseOperation> getBeforeTestDbOperations() {
		List<DatabaseOperation> operations=new ArrayList<DatabaseOperation>();
		operations.add(DatabaseOperation.CLEAN_INSERT);
		return operations;
	}
	
}
