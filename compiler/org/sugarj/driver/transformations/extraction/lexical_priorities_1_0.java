package org.sugarj.driver.transformations.extraction;

import org.strategoxt.stratego_lib.*;
import org.strategoxt.lang.*;
import org.spoofax.interpreter.terms.*;
import static org.strategoxt.lang.Term.*;
import org.spoofax.interpreter.library.AbstractPrimitive;
import java.util.ArrayList;
import java.lang.ref.WeakReference;

@SuppressWarnings("all") public class lexical_priorities_1_0 extends Strategy 
{ 
  public static lexical_priorities_1_0 instance = new lexical_priorities_1_0();

  @Override public IStrategoTerm invoke(Context context, IStrategoTerm term, Strategy v_28)
  { 
    ITermFactory termFactory = context.getFactory();
    context.push("lexical_priorities_1_0");
    Fail256:
    { 
      IStrategoTerm b_146 = null;
      IStrategoTerm a_146 = null;
      if(term.getTermType() != IStrategoTerm.APPL || outt._conslexical_priorities_1 != ((IStrategoAppl)term).getConstructor())
        break Fail256;
      a_146 = term.getSubterm(0);
      IStrategoList annos208 = term.getAnnotations();
      b_146 = annos208;
      term = v_28.invoke(context, a_146);
      if(term == null)
        break Fail256;
      term = termFactory.annotateTerm(termFactory.makeAppl(outt._conslexical_priorities_1, new IStrategoTerm[]{term}), checkListAnnos(termFactory, b_146));
      context.popOnSuccess();
      if(true)
        return term;
    }
    context.popOnFailure();
    return null;
  }
}