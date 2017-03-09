package rd;

import soot.Pack;
import soot.PackManager;
import soot.Scene;
import soot.SootClass;
import soot.SootField;
import soot.SootResolver;
import soot.Transform;
import soot.options.Options;
import soot.util.Chain;

public class driver
{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if(args.length==0)
		{
			System.err.println("Usage: java Driver [options] classname");
			System.exit(0);
		}
		SootClass abc=Scene.v().loadClassAndSupport("rd.test");
		Options.v().setPhaseOption("jb", "use-original-names:true");
		Pack jtp=PackManager.v().getPack("jtp");
		jtp.add(new Transform("jtp.instrumenter", new wrapper()));
		SootResolver.v().resolveClass("java.lang.CloneNotSupportedException", SootClass.SIGNATURES);
		soot.Main.main(args);
		Options.v().set_output_format(Options.output_format_jimple);
		Chain<SootField> l=abc.getFields();
		System.out.println (l);
		
	}
}