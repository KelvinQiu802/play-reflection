import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ClassPrinter {

    private Boolean foo;
    private String bar;

    public ClassPrinter(Boolean foo, String bar) {
        this.foo = foo;
        this.bar = bar;
    }

    public ClassPrinter() {
    }

    public static void main(String[] args) {
        print(ClassPrinter.class);
    }

    public static void print(Class cl) {
        Class superClass = cl.getSuperclass();

        String modifier = Modifier.toString(cl.getModifiers());
        if (!modifier.isEmpty()) System.out.printf("%s ", modifier);
        System.out.printf("class %s", cl.getTypeName());

        if (superClass != null && superClass != Object.class) {
            System.out.printf(" extends %s", superClass.getTypeName());
        }

        System.out.print("\n{\n");

        printFields(cl);
        System.out.println();
        printConstructors(cl);
        System.out.println();
        printMethods(cl);

        System.out.println("}");
    }

    public static void printConstructors(Class cl) {
        Constructor[] constructors = cl.getConstructors();
        for (Constructor c : constructors) {
            String name = c.getName();
            String modifier = Modifier.toString(c.getModifiers());

            System.out.print("\t");
            if (!modifier.isEmpty()) System.out.printf("%s ", modifier);
            System.out.printf("%s (", name);
            // parameter type
            Class[] paramTypes = c.getParameterTypes();
            for (int i = 0; i < paramTypes.length; ++i) {
                if (i > 0) System.out.print(", ");
                System.out.print(paramTypes[i].getTypeName());
            }
            System.out.println(");");
        }
    }

    public static void printFields(Class cl) {
        Field[] fields = cl.getDeclaredFields();
        for (Field f : fields) {
            String modifier = Modifier.toString(f.getModifiers());
            String name = f.getName();
            String type = f.getType().getTypeName();

            System.out.print("\t");
            if (!modifier.isEmpty()) System.out.printf("%s ", modifier);
            System.out.printf("%s %s;\n", type, name);
        }
    }

    public static void printMethods(Class cl) {
        Method[] methods = cl.getDeclaredMethods();
        for (Method m : methods) {
            String modifier = Modifier.toString(m.getModifiers());
            String name = m.getName();
            String returnType = m.getReturnType().getTypeName();

            System.out.print("\t");
            if (!modifier.isEmpty()) System.out.printf("%s ", modifier);
            System.out.printf("%s %s(", returnType, name);
            // params type
            Class[] paramTypes = m.getParameterTypes();
            for (int i = 0; i < paramTypes.length; ++i) {
                if (i > 0) System.out.print(", ");
                System.out.print(paramTypes[i].getTypeName());
            }
            System.out.println(");");
        }
    }

}