package cn.asens.asm;

/**
 * Created by lenovo on 2016/5/19.
 */
import java.util.List;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

public class ReadMethodArgNameMethodVisitor extends MethodVisitor {

    public List<String> argumentNames;

    public int argLen;

    public ReadMethodArgNameMethodVisitor(int api) {
        super(api);
    }

    @Override
    public void visitLocalVariable(String name, String desc, String signature,
                                   Label start, Label end, int index) {
        if("this".equals(name)) {
            return;
        }
        if(argLen-- > 0) {
            argumentNames.add(name);
        }
    }

}
