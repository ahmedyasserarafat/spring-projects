package com.codenotfound.jms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.codenotfound.jms.consumer.Receiver;
import com.codenotfound.jms.producer.Sender;

/*import oracle.AQ.AQDequeueOption;
import oracle.AQ.AQDriverManager;
import oracle.AQ.AQEnqueueOption;
import oracle.AQ.AQException;
import oracle.AQ.AQMessage;
import oracle.AQ.AQObjectPayload;
import oracle.AQ.AQOracleQueue;
import oracle.AQ.AQOracleSession;
import oracle.AQ.AQQueue;
import oracle.AQ.AQSession;*/
import oracle.AQ.AQDequeueOption;
import oracle.AQ.AQDriverManager;
import oracle.AQ.AQEnqueueOption;
import oracle.AQ.AQException;
import oracle.AQ.AQMessage;
import oracle.AQ.AQObjectPayload;
import oracle.AQ.AQOracleQueue;
import oracle.AQ.AQOracleSession;
import oracle.AQ.AQQueue;
import oracle.AQ.AQSession;  

@SpringBootApplication
public class SpringJmsApplication implements CommandLineRunner{

	@Autowired
	private Sender sender;

	@Autowired
	private Receiver receiver;
	
	@Autowired
	private static EventMsgType event_msg_typ;


	public static void main(String[] args) {
		SpringApplication.run(SpringJmsApplication.class, args);


	}


	@Override
	public void run(String... args) throws Exception {


		//sender.send("helloworld.q", "Assalamu ALikum varahmatullahi barkathu");
		
		/* String userName = "corp";
		    String queue = "aq_admin.event_queue";
		    String qTable = "aq_admin.event_queue_tab";
		    consumeMessage(userName, queue);*/
		
		AQSession  aq_sess = null;
	      try 
	      {
	         aq_sess = createSession(args);

	   /* now run the test: */
	        AQObjectPayloadTest(aq_sess);    
	         
	      }
	      catch (Exception ex)
	      {
	         System.out.println("Exception-1: " + ex); 
	         ex.printStackTrace();      
	      }  



	}
	
	public static AQSession createSession(String args[]) 
	   {
	      Connection db_conn;
	      AQSession  aq_sess = null;

	      try 
	      {
	    
	         Class.forName("oracle.jdbc.driver.OracleDriver");
	    /* your actual hostname, port number, and SID will 
	    vary from what follows. Here we use 'dlsun736,' '5521,'
	    and 'test,' respectively: */

	         db_conn =
	                  DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "corp", "corp");

	         System.out.println("JDBC Connection opened "); 
	         db_conn.setAutoCommit(false);
	                 
	    /* Load the Oracle8i AQ driver: */
	         Class.forName("oracle.AQ.AQOracleDriver");

	    /* Creating an AQ Session: */
	         aq_sess = AQDriverManager.createAQSession(db_conn);
	         System.out.println("Successfully created AQSession ");  
	      }
	      catch (Exception ex)
	      {
	         System.out.println("Exception: " + ex); 
	         ex.printStackTrace();      
	      }  
	      return aq_sess;
	   }
	
	public static void AQObjectPayloadTest(AQSession aq_sess)
		       throws AQException, SQLException, ClassNotFoundException
		  {
		    Connection           db_conn   = null;
		    AQQueue              queue     = null;
		    AQMessage            message   = null;
		    AQObjectPayload      payload   = null;
		    AQEnqueueOption      eq_option = null;
		    AQDequeueOption      dq_option = null;
		   

		    db_conn = ((AQOracleSession)aq_sess).getDBConnection();


		    queue = aq_sess.getQueue("SA_ADMIN", "EMP_QUEUE");


		    /* Enable enqueue/dequeue on this queue */
		    queue.start();

		    /* Enqueue a message in test_queue2 */
	/*	    message = queue.createMessage();

		    Employee  pers = new Employee();
		    pers.setEmpname("John");
		    pers.setEmpno(1);
		   

		    payload = message.getObjectPayload();
		    payload.setPayloadData(pers);
		    eq_option = new AQEnqueueOption();

		     Enqueue a message into test_queue2 
		    queue.enqueue(eq_option, message); 

		    db_conn.commit();   
*/
	

		   
		       

		    /* Dequeue a message from test_queue2 */
		    dq_option = new AQDequeueOption();
		    
		    message = ((AQOracleQueue)queue).dequeue(dq_option,Employee.getFactory());

		    payload = message.getObjectPayload();
		    Employee messageData = (Employee) payload.getPayloadData();  
		        
		    System.out.println("Object data retrieved:  [PERSON]");
		    System.out.println("Name:   " + messageData);
		    

		    db_conn.commit(); 
		  }
	
	/*public static QueueConnection getConnection() {
		
		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

		} catch (ClassNotFoundException e) {

			System.out.println("Where is your Oracle JDBC Driver?");
			e.printStackTrace();
			return;

		}

		System.out.println("Oracle JDBC Driver Registered!");

		Connection connection = null;

		try {

			connection = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", "corp", "corp");

			/* QueueConnection qconn = AQjmsQueueConnectionFactory.createQueueConnection(connection);
          QueueSession qsess = qconn.createQueueSession(true, 0);
          Queue q = qsess.createQueue("Q");
          QueueSender qsend = qsess.createSender(q);
          TextMessage msg;
          msg = qsess.createTextMessage("TEST JAVA");
          qsend = qsess.createSender(q);
          qsend.send(msg);
          qsess.commit();

		} catch (SQLException e) {

			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;

		}*/

	    /*String hostname = "localhost";
	    String oracle_sid = "xe";
	    int portno = 1521;
	    String userName = "corp";
	    String password = "corp";
	    String driver = "thin";
	    QueueConnectionFactory QFac = null;
	    QueueConnection QCon = null;
	    try {
	        // get connection factory , not going through JNDI here
	        QFac = AQjmsFactory.getQueueConnectionFactory(hostname, oracle_sid, portno, driver);
	        // create connection
	        QCon = QFac.createQueueConnection(userName, password);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return QCon;
	}*/
	
	/*public static void consumeMessage(String user, String queueName) {
	    Queue queue;
	    AQMessage            message   = null;
	    AQObjectPayload      payload   = null;
	    try {
	        QueueConnection QCon = getConnection();
	        Session session = QCon.createQueueSession(true, Session.SESSION_TRANSACTED);
	        QCon.start();
	        queue = ((AQjmsSession) session).getQueue(user, queueName);
	        MessageConsumer consumer = session.createConsumer(queue);
	        TextMessage msg = (TextMessage) consumer.receive();
	        System.out.println("MESSAGE RECEIVED " + msg.getText());
	        
	        ObjectMessage msg=(ObjectMessage) consumer.receive();
	        System.out.println("MESSAGE RECEIVED " + msg.getObject());

	        consumer.close();
	        session.close();
	        QCon.close();
	    } catch (JMSException e) {
	        e.printStackTrace();
	    }
	}*/

}
