package scenarios;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import Bean.Review;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.List;

import controllers.Handlers;

public class Steps {
 
    @Given("la revue $revue n'existe pas")
    public void reviewDoesntExist(String revue) {
    	Handlers myHandler = new Handlers();
    	assertEquals(myHandler.getReview(revue), null);
    }
     
    @Given("la revue $revue existe")
    public void reviewExists(String revue) {
    	Handlers myHandler = new Handlers();
    	assertNotEquals(myHandler.getReview(revue), null);
    }
    
    @When("j'ajoute la revue $revue avec la date de diffusion $date au système ")
    public void iAddTheReview(String revue, long date) {
    	Handlers myHandler = new Handlers();
    	myHandler.addReview(revue, date);
    }
     
    @Then("la revue $revue est accessible et sa date de création est égale à la date de dernière édition")
    public void reviewIsAvailable(String revue) {
    	Handlers myHandler = new Handlers();
    	Review maRevue = myHandler.getReview(revue);
    	assertEquals(maRevue.getCreationDate(), maRevue.getEditionDate());
    }
    
    @Then("il n'existe que $nbRevue revue nommée $revue en bdd et sa $dateCreation est antérieure à $dateActuelle")
    public void reviewIsntAvailable(int nbRevue, String revue, long dateCreation, long dateActuelle) {
        Handlers myHandler = new Handlers();
        List<String> listReviews = myHandler.getAllReviews();
        int occurrences = Collections.frequency(listReviews, revue);
        assertEquals(occurrences, nbRevue);
        Review maRevue = myHandler.getReview(revue);
    	assertNotEquals(maRevue.getCreationDate(), maRevue.getEditionDate());
    }
}