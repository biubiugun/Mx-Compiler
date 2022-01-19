package IR.Operand;

import IR.TypeSystem.ArrayType;
import IR.TypeSystem.IntegerType;
import IR.TypeSystem.PointerType;

public class StringConst extends Const{
    public String value;
    public String name;
    public int size;

    public StringConst(String _value, String _name){
        super(new PointerType(new IntegerType(8),1));
        value = _value;
        name = _name;
        size = _value.length() + 1;
        for(int i=0;i<_value.length();i++){
            if (_value.charAt(i)=='\\'){
                size--;
                if (i!=_value.length()-1&&_value.charAt(i+1)=='\\'){
                    i = i+1;
                }
            }
        }
    }

    public int stringSize(){
        return size;
    }


    @Override
    public String toString() {
        return "getelementptr inbounds ([" + stringSize()+ " x i8] ,[" + stringSize() + " x i8]* @" + name + ", i64 0, i64 0)";
    }

    public String getReplaceValue(){
        return ((value)
                .replace("\\\\", "\\5C")
                .replace("\\n", "\\0A")
                .replace("\\\"", "\\22")
                .replace("\\t", "\\09") )+ "\\00";
    }

    public String toPrintString(){//constant [5 x i8] c"hello ", align 1
        return "@" + name +  " = constant [" +stringSize() +  " x i8] c\"" + getReplaceValue() +"\", align 1";
    }
}
