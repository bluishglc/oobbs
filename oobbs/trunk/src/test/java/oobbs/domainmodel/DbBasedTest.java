package oobbs.domainmodel;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.dbunit.DataSourceDatabaseTester;
import org.dbunit.DefaultOperationListener;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.xml.XmlDataSet;
import org.dbunit.ext.mysql.MySqlDataTypeFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import testutil.ApplicationContextSupport;

/**
 * This base class does not extends any DBTestCase of dbunit,or use any Tester,Their's implement is not good and flexible.
 * Here,we prepare and set connection manully!
 * 
 * @author Laurence Geng
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/applicationContext-infrastructure.xml",
								   "classpath:/applicationContext-domainModel.xml",
								   "classpath:/applicationContext-test.xml"})
public abstract class DbBasedTest{
	
    /** The data source. */
    @Autowired
    protected DataSource dataSource;

    /** The dbunitTestUtil can fill test data from xml into test db before testing. */
    protected DataSourceDatabaseTester dbunitTestUtil;
    
    /**
     * Inits dbunitTestUtil. 
	 * The connectionRetrieved method is called back when setUp() executes.
	 * At this time,we should set connection-specific setting: set foreign key check disabled
	 * so as dbunit can invert test data, and set data type factory be MySqlDataTypeFactory so as
	 * dbunit can convert correct type when invert data to mysql.
	 *
     * @throws Exception the exception
     */
    protected void initDbunitTestUtil() throws Exception{
    	dbunitTestUtil = new DataSourceDatabaseTester(dataSource);
		dbunitTestUtil.setDataSet(new XmlDataSet(new ClassPathResource("dbunit-test-data.xml").getInputStream()));
		dbunitTestUtil.setOperationListener( new DefaultOperationListener(){
            public void connectionRetrieved(IDatabaseConnection connection) {
                try {
                	//Disable foreign key check!
					connection.getConnection().prepareStatement("set @@session.foreign_key_checks = 0").execute();
					// When a new connection has been created then invoke the setUp method
					// so that user defined DatabaseConfig parameters can be set.
					connection.getConfig().setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new MySqlDataTypeFactory());
				} catch (SQLException e) {
					e.printStackTrace();
				}
            }});
    }
	
	/**
	 * Before test method.
	 * 
	 * @throws Exception the exception
	 */
	@Before
    public void beforeTestMethod() throws Exception { 
		initDbunitTestUtil();
		dbunitTestUtil.onSetup();
    }
    
    /**
     * After test method.
     * 
     * @throws Exception the exception
     */
    @After
    public void afterTestMethod() throws Exception {
    	dbunitTestUtil.onTearDown();
    }
    
}
