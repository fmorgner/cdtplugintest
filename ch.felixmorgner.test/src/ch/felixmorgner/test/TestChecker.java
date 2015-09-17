package ch.felixmorgner.test;

import org.eclipse.cdt.codan.core.cxx.model.AbstractAstFunctionChecker;
import org.eclipse.cdt.codan.core.model.IChecker;
import org.eclipse.cdt.core.dom.ast.ASTVisitor;
import org.eclipse.cdt.core.dom.ast.IASTDeclaration;
import org.eclipse.cdt.core.dom.ast.IASTFunctionDefinition;
import org.eclipse.cdt.core.dom.ast.IASTSimpleDeclaration;

public class TestChecker extends AbstractAstFunctionChecker implements IChecker {

	class DeclarationVisitor extends ASTVisitor {
		public DeclarationVisitor(IASTFunctionDefinition f) {
			shouldVisitDeclarations = true;
		}
		
		@Override
		public int visit(IASTDeclaration declaration) {
			if(declaration instanceof IASTSimpleDeclaration) {
				if(!((IASTSimpleDeclaration) declaration).getDeclSpecifier().isConst()) {
					reportProblem("ch.felixmorgner.test", declaration, "242");
				}
			}
			return PROCESS_CONTINUE;
		}
	}
	
	@Override
	protected void processFunction(IASTFunctionDefinition func) {
		DeclarationVisitor visitor = new DeclarationVisitor(func);
		func.accept(visitor);
	}

}
