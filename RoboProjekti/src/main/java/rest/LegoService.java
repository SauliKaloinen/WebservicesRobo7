package rest;

import java.io.PrintWriter;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import data.Parameters;

@Path("/legoservice")
public class LegoService {
//Reading all the rows from table prey.
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Parameters> readAllParameters() {
	//Create an EntityManagerFactory with the settings from persistence.xml file
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("legoservice");
		//And then EntityManager, which can manage the entities.
		EntityManager em=emf.createEntityManager();
		
		//Read all the rows from table prey. Here the Prey must start with capital, 
		//because class's name starts. This returns a List of Prey objects.
		List<Parameters> list=em.createQuery("select a from Parameters a").getResultList();
		return list;
	}
	
//Adding one prey object into the table prey	
	@POST
	@Path("/legoform")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Parameters postParameters(Parameters parameters) {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("legoservice");
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(parameters);//The actual insertion line
		em.getTransaction().commit();
		return parameters;
	}

//This method uses FormParams, but does the same as previous	
	@POST
	@Path("/legoform")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Parameters postParametersByParams(@FormParam("motor1speed") int motor1Speed, @FormParam("motor2speed") int motor2Speed) {
		Parameters Parameters=new Parameters();
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("legoservice");
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(Parameters);
		em.getTransaction().commit();
		return Parameters;
	}
	
	@GET
	@Path("/par")
	@Produces(MediaType.TEXT_PLAIN)
	public String readParameters() {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("legoservice");
		Parameters Parameters=new Parameters();
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		List<Parameters> list=em.createQuery("select k from Parameters k order by k.id desc").getResultList();
		em.getTransaction().commit();
		em.close();
		Parameters abc=list.get(0);
		return ""+abc.getMotor1Speed()+" "+abc.getMotor2Speed()+" "+abc.getId();
	}
	
	
	//Uusi metodi
	//Yhteys tietokantaan
	//Lue haluttu data
	//palauta se (return) parameters.getSpeed()
}