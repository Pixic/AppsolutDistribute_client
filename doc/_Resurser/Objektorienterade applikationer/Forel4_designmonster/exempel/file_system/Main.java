import filesystem.*;

public class Main
{
    public static void main(String[] args)
    {
        TextFile f1 = new TextFile("f1");
        TextFile f2 = new TextFile("f2");
        TextFile f3 = new TextFile("f3");
        TextFile f4 = new TextFile("f4");
        Directory root = new Directory("root");
        Directory subdir = new Directory("subdir");
        root.add(f1);
        root.add(subdir);
        subdir.add(f2);
        subdir.add(f3);
        subdir.add(f4);
        root.print(true); // recursive print
        
        f1.write("A little piece of text");
        f2.write("Some more text");
        f3.write("Some really not very long text");
        f4.write("A tiny text");
        f1.append("\nAn extra line of text");
        
        System.out.println(root.getSize());
        System.out.println(subdir.getSize());
        System.out.println(f1.read());
    }
}
