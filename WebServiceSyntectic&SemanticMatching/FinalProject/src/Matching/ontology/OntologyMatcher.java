/**
 * 
 */
package Matching.ontology;

import java.util.HashMap;

import org.mindswap.pellet.owlapi.Reasoner;

import org.semanticweb.owl.model.OWLClass;
import org.semanticweb.owl.model.OWLOntology;

import org.semanticweb.owl.model.OWLOntologyManager;

/**
 * @author Chenchen Cheng
 *
 */
public class OntologyMatcher {
    private static Reasoner reasoner = null;
    private static String ontLocation = "File:./src/SUMO.owl";
    private static OWLOntologyManager manager = null;
    private static OWLOntology ontology = null;
    private static MyOntManager ontsum = null;
    
    public static double getScore(String clsName1, String clsName2) {
        HashMap<String, OWLClass> mapName_OWLClass = ontsum.loadClasses(reasoner);
        
        String newClsName1 = clsName1;
        String newClsName2 = clsName2;
        if(clsName1.contains("-")){
        	newClsName1.replace("-", "");
        }
        if(clsName2.contains("-")){
        	newClsName2.replace("-", "");
        }

        OWLClass cls1 = mapName_OWLClass.get(newClsName1.toLowerCase());
        OWLClass cls2 = mapName_OWLClass.get(newClsName2.toLowerCase());

        if (cls1 == null || cls2 == null) {
        }       
        else if (reasoner.isEquivalentClass(cls1, cls2)) {
            return 1.0;
        } else if (reasoner.isSubClassOf(cls2, cls1)) {
            return 0.8;
        } else if (reasoner.isSubClassOf(cls1, cls2)) {
            return 0.6;
        } else if(ontsum.findRelationship(cls1, cls2,reasoner).size() > 0){
        	return 0.5;
        } 
        
        return 0.0;
    }

    public static void initializeOntology() {
        ontsum = new MyOntManager();
        manager = ontsum.initializeOntologyManager();
        ontology = ontsum.initializeOntology(manager, ontLocation);
        reasoner = ontsum.initializeReasoner(ontology, manager);
    }

}
