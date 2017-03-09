package rd;

import java.util.*;

import soot.Body;
import soot.BodyTransformer;
import soot.Local;
import soot.SootClass;
import soot.SootMethod;
import soot.Type;
import soot.Unit;
import soot.jimple.JimpleBody;
import soot.jimple.Stmt;
import soot.jimple.toolkits.annotation.logic.Loop;
import soot.jimple.toolkits.annotation.logic.LoopFinder;
import soot.toolkits.graph.BriefUnitGraph;
import soot.toolkits.graph.UnitGraph;
import soot.util.Chain;

public class wrapper extends BodyTransformer{

	@Override
	protected void internalTransform(Body body, String phase, Map options) {
		// TODO Auto-generated method stub
		SootMethod sootMethod = body.getMethod();
		Chain<Local> localvar =body.getLocals();
		UnitGraph g = new BriefUnitGraph(sootMethod.getActiveBody());
		//ReachingDefinitionAnalysis reach = new ReachingDefinitionAnalysis(g);
		int j
		String str=sootMethod.getName();
		System.out.println("Method_name :"+str);
		List a1 = new ArrayList();
		a1=sootMethod.getParameterTypes();
	System.out.print("Parameter->");
		System.out.print("   " +a1);	
		int temp2=0;
		System.out.println();
		Type s=sootMethod.getReturnType();	System.out.println("Return_type:"+s);
		List<SootClass> s1=sootMethod.getExceptions();
		System.out.println("Exception:"+s1);		
		Local var=localvar.getFirst();
		for(j=0;j<localvar.size();j++)
		{
			System.out.println("localvar is :"+var+"   ||  type is :"+var.getType());
			var=localvar.getSuccOf(var);
		}
		
		int temp1=0;		
		Iterator<Unit> var2 = body.getUnits().snapshotIterator();
     while (var2.hasNext()) {     Stmt sud= (Stmt) var2.next();
            if (sud.containsInvokeExpr()) 
            {       	temp1=1;
            }
           if(sud.toString().contains("*")) { 	temp2=1;
            }
        }
		LoopFinder loopvar=new LoopFinder();
		loopvar.transform(body);
		Collection<Loop> s2=loopvar.loops();
		if(s2.isEmpty())
		{
			System.out.println("trere is no any loop");
		}
		else{
			System.out.println("loop is presrent");
		}
		
		boolean var1=sootMethod.isStatic();
		if(var1==true)
		{
			System.out.println("method is Static");
		}
		else{
			System.out.println("Method is virtual");
		}
	    
		
		
        if(temp1==1) {     	System.out.println ("Function is invoked");
        }
        else { 	System.out.println ("Function is not invoked");
        } 
        if(temp2==1){
        	System.out.println ("* operator=YES");
        }
        else
        { 	System.out.println (" * operator =NO");
        }	
		System.out.println();
		System.out.println();
		System.out.println();
	}
}
