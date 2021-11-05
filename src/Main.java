
import Frontend.ASTBuilder;
import AST.RootNode.RootNode;
import Frontend.Initiator;
import Frontend.SemanticChecker;
import Util.GlobalScope;
import Util.MxstarErrorListener;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import Parser.MxstarParser;
import Parser.MxstarLexer;
import java.io.FileInputStream;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception{

//        String name = "test.yx";
//        InputStream input = new FileInputStream(name);
        InputStream input = System.in;

        try {
            // CharStreams is ANTLR's built-in string of 01;
            // new a lexer to process the charStream
            MxstarLexer lexer = new MxstarLexer(CharStreams.fromStream(input));
            // remove original error methods
            lexer.removeErrorListeners();
            lexer.addErrorListener(new MxstarErrorListener());
            // new a Parser from the lexer
            MxstarParser parser = new MxstarParser(new CommonTokenStream(lexer));
            // use customized error methods to alter the original one
            parser.removeErrorListeners();
            parser.addErrorListener(new MxstarErrorListener());
            // start parsing according to the program rule
            ParseTree parseTreeRoot = parser.program();

            ASTBuilder astBuilder = new ASTBuilder();
            RootNode rt = (RootNode) astBuilder.visit(parseTreeRoot);
            GlobalScope gScope = new GlobalScope(null);
            Initiator initiator = new Initiator(gScope);
            gScope = initiator.initializeBuiltIn();
            initiator.visit(rt);
            SemanticChecker semanticCheck = new SemanticChecker(gScope);
            semanticCheck.visit(rt);
        } catch (RuntimeException er) {
            System.err.println(er.getMessage());
            throw new RuntimeException();
        }
    }
}