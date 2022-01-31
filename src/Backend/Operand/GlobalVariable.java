package Backend.Operand;

public class GlobalVariable extends BaseReg{
    public String content;

    public GlobalVariable(String _name, String _content){
        super(_name);
        content = _content;
    }

    String replaced_string(String _str){
        return _str.replace("\\", "\\\\")
                .replace("\n", "\\n")
                .replace("\0", "")
                .replace("\t", "\\t")
                .replace("\"", "\\\"");
    }

    public String print_ASM_string(){
        StringBuilder str = new StringBuilder();
        if(content == null){
            str.append('\t').append(".type").append('\t').append(GetName()).append(",@object\n");
            str.append('\t').append(".section").append('\t').append(".bss\n");
            str.append('\t').append(".globl").append('\t').append(GetName()).append('\n');
            str.append(GetName()).append(":\n");
            str.append('\t').append(".word").append('\t').append(0).append('\n');
            str.append('\t').append(".size").append('\t').append(GetName()).append(", 4");
        }else{
            str.append('\t').append(".type").append('\t').append(GetName()).append(",@object\n");
            str.append('\t').append(".section").append('\t').append(".rodata\n");
            str.append(GetName()).append(":\n");
            str.append('\t').append(".asciz").append('\t').append('\"').append(replaced_string(content)).append('\"').append('\n');
            str.append('\t').append(".size").append('\t').append(GetName()).append(", ").append(content.length());
        }
        return str.toString();
    }


}
