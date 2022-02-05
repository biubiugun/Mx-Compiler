
import Backend.ASMBuilder;
import Backend.RegAllocateStack;
import Frontend.ASTBuilder;
import AST.RootNode.RootNode;
import Frontend.Initiator;
import Frontend.SemanticChecker;
import IR.IRBuilder;
import IR.IRModule;
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

//        String name = "src/selftest/test.mx";
//        InputStream input = new FileInputStream(name);
        InputStream input = System.in;
        PrintStream os = System.out;
        boolean semantic_test_only = false;
        boolean ir_test = false;
        for (int i = 0;i < args.length; ++i){
            if(args[i].charAt(0) == '-'){
                switch (args[i]) {
                    case "-fsyntax-only" -> semantic_test_only = true;
                    case "-emit-llvm" -> ir_test = true;
                    case "-o" -> os = new PrintStream(new FileOutputStream(args[i + 1]));
                    default -> {}
                }
            }
        }
//        os = new PrintStream(new FileOutputStream("src/selftest/self_llvm_test.ll"));
//        ir_test = true;

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

            if(!semantic_test_only){
                //llvm ir
                IRModule ir_module = new IRModule();
                IRBuilder ir_builder = new IRBuilder(ir_module,gScope);
                ir_builder.visit(rt);
                ir_builder.process_initialize();
                if(ir_test){
                    os.println(ir_module);
                }
                //asm
                ASMBuilder asm_builder = new ASMBuilder();
                asm_builder.visit(ir_module);
//                os.println(asm_builder.ASM_build_module.print_ASM_string());
//                reg alloc
                RegAllocateStack alloc_stack = new RegAllocateStack(asm_builder.ASM_build_module);
                alloc_stack.run();
                os.println(alloc_stack.base_module.print_ASM_string());
            }

        } catch (RuntimeException er) {
            System.err.println(er);
            throw new RuntimeException();
        }
    }
}