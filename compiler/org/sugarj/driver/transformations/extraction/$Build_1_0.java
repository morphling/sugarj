package org.sugarj.driver.transformations.extraction;

import org.strategoxt.stratego_lib.*;
import org.strategoxt.lang.*;
import org.spoofax.interpreter.terms.*;
import static org.strategoxt.lang.Term.*;
import org.spoofax.interpreter.library.AbstractPrimitive;
import java.util.ArrayList;
import java.lang.ref.WeakReference;

@SuppressWarnings("all") public class $Build_1_0 extends Strategy 
{ 
  public static $Build_1_0 instance = new $Build_1_0();

  @Override public IStrategoTerm invoke(Context context, IStrategoTerm term, Strategy v_22)
  { 
    ITermFactory termFactory = context.getFactory();
    context.push("Build_1_0");
    Fail130:
    { 
      IStrategoTerm f_122 = null;
      IStrategoTerm e_122 = null;
      if(term.getTermType() != IStrategoTerm.APPL || outt._consBuild_1 != ((IStrategoAppl)term).getConstructor())
        break Fail130;
      e_122 = term.getSubterm(0);
      IStrategoList annos102 = term.getAnnotations();
      f_122 = annos102;
      term = v_22.invoke(context, e_122);
      if(term == null)
        break Fail130;
      term = termFactory.annotateTerm(termFactory.makeAppl(outt._consBuild_1, new IStrategoTerm[]{term}), checkListAnnos(termFactory, f_122));
      context.popOnSuccess();
      if(true)
        return term;
    }
    context.popOnFailure();
    return null;
  }
}