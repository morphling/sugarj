package org.sugarj;

import static org.sugarj.common.ATermCommands.getApplicationSubterm;
import static org.sugarj.common.ATermCommands.isApplication;
import static org.sugarj.common.Log.log;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.spoofax.interpreter.terms.IStrategoTerm;
import org.strategoxt.lang.Context;
import org.strategoxt.stratego_gpp.parse_pptable_file_0_0;
import org.sugarj.common.ATermCommands;
import org.sugarj.common.Environment;
import org.sugarj.common.FileCommands;
import org.sugarj.common.IErrorLogger;
import org.sugarj.common.Log;
import org.sugarj.common.path.Path;
import org.sugarj.common.path.RelativePath;
import org.sugarj.languagelib.SourceFileContent;
import org.sugarj.prolog.PrologSourceFileContent;
import org.sugarj.prolog.PrologSourceFileContent.PrologModuleImport;

public class PrologLib extends LanguageLib implements Serializable {
  private static final long serialVersionUID = 6271882490466636509L;

  private Set<RelativePath> generatedFiles = new HashSet<RelativePath>();

  private Path prologOutFile;

  private PrologSourceFileContent prologSource;

  private String decName;
  private String relNamespaceName;

  private IStrategoTerm pptable = null;
  private File prettyPrint = null;

  private File getPrettyPrint() {
    if (prettyPrint == null)
      prettyPrint = getFactoryForLanguage().ensureFile("org/sugarj/languages/Prolog.pp");

    return prettyPrint;
  }

  @Override
  public SourceFileContent getGeneratedSource() {
    return prologSource;
  }

  @Override
  public Path getOutFile() {
    return prologOutFile;
  }

  @Override
  public Set<RelativePath> getGeneratedFiles() {
    return generatedFiles;
  }

  @Override
  public void processLanguageSpecific(IStrategoTerm toplevelDecl, Environment environment) throws IOException {
    // Nothing to do here for prolog
    IStrategoTerm dec = toplevelDecl;

    // TODO: Implement reexport handling in a more sensible way

    if (isApplication(dec, "ModuleReexport"))
      prologSource.addReexport(prettyPrint(dec));
    else
      prologSource.addBodyDecl(prettyPrint(dec));
  }

  private IStrategoTerm initializePrettyPrinter(Context ctx) {
    if (pptable == null) {
      IStrategoTerm pptable_file = ATermCommands.makeString(getPrettyPrint().getAbsolutePath());
      pptable = parse_pptable_file_0_0.instance.invoke(org.strategoxt.stratego_gpp.stratego_gpp.init(), pptable_file);
    }

    return pptable;
  }

  public String prettyPrint(IStrategoTerm term) {
    IStrategoTerm ppTable = initializePrettyPrinter(interp.getCompiledContext());
    return ATermCommands.prettyPrint(ppTable, term, interp);
  }

  @Override
  public void init(RelativePath sourceFile, Environment environment) {
    prologOutFile = environment.createOutPath(FileCommands.dropExtension(sourceFile.getRelativePath()) + "." + PrologLibFactory.getInstance().getGeneratedFileExtension());
    prologSource = new PrologSourceFileContent(this);
  }

  @Override
  public String getRelativeNamespace() {
    // XXX: Is there a namespace separator in prolog? Or even any notion of
    // compound namespaces?
    // XXX: From swi prolog doc: Modules are organised in a single and flat
    // namespace and therefore module names must be chosen with some care to
    // avoid conflicts.
    // XXX: SugarProlog will implement different namespace handling.
    return relNamespaceName;
  }

  @Override
  public void processNamespaceDec(IStrategoTerm toplevelDecl, Environment environment, IErrorLogger errorLog, RelativePath sourceFile, RelativePath sourceFileFromResult) throws IOException {

    String moduleName = null;
    if (isApplication(toplevelDecl, "ModuleDec")) {
      moduleName = prettyPrint(getApplicationSubterm(toplevelDecl, "ModuleDec", 0));
      prologSource.setModuleDecl(prettyPrint(toplevelDecl));
    } else if (isApplication(toplevelDecl, "SugarModuleDec")) {
      moduleName = prettyPrint(getApplicationSubterm(toplevelDecl, "SugarModuleDec", 0));
      prologSource.setModuleDecl(":-module(" + moduleName + ", []).");
    }

    relNamespaceName = FileCommands.dropFilename(sourceFile.getRelativePath());
    decName = getRelativeModulePath(moduleName);
    log.log("The SDF / Stratego package name is '" + relNamespaceName + "'.", Log.DETAIL);

    if (prologOutFile == null)
      prologOutFile = environment.createOutPath(getRelativeNamespaceSep() + FileCommands.fileName(sourceFileFromResult) + "." + PrologLibFactory.getInstance().getGeneratedFileExtension());
  }

  @Override
  public LanguageLibFactory getFactoryForLanguage() {
    return PrologLibFactory.getInstance();
  }

  @Override
  public List<Path> compile(List<Path> sourceFiles, Path bin, List<Path> path) throws IOException {
    // no compilation neccessary for Prolog
    return Collections.emptyList();
  }

  @Override
  public String getModulePathOfImport(IStrategoTerm toplevelDecl) {
    String modulePath = prettyPrint(toplevelDecl.getSubterm(0).getSubterm(0));

    return modulePath;
  }

  private String getRelativeModulePath(String moduleName) {
    return moduleName.replace("/", Environment.sep);
  }

  @Override
  public void addModuleImport(IStrategoTerm toplevelDecl, boolean checked) throws IOException {

    String importedModuleName = prettyPrint(toplevelDecl.getSubterm(0).getSubterm(0));
    PrologModuleImport imp = prologSource.getImport(importedModuleName, toplevelDecl);

    if (checked)
      prologSource.addCheckedImport(imp);
    else
      prologSource.addImport(imp);
  }

  @Override
  public String getExtensionName(IStrategoTerm decl) throws IOException {
    return decName;
  }

  @Override
  public boolean isModuleExternallyResolvable(String relModulePath) {
    // TODO: look for pre-installed SWI libraries?
    return false;
  }
}
