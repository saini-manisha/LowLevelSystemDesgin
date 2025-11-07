package CompositePattern;

//Base interface for files and folders

import java.util.ArrayList;
import java.util.List;

interface FileSystemItem{
    void ls(int indent);
    void openAll(int indent);
    int getSize();
    FileSystemItem cd(String name);
    String getName();
    boolean isFolder();
}

// Leaff: File
class File implements FileSystemItem{
    private String name;
    private int size;

    public File(String n,int s){
        name=n;
        size=s;
    }

    public void ls(int indent){
        String indentSpaces=" ".repeat(indent);
        System.out.println(indentSpaces+name);
    }

    public void openAll(int indent){
        String indentSpaces=" ".repeat(indent);
        System.out.println(indentSpaces +name);

    }

    public int getSize(){
        return size;
    }

    public FileSystemItem cd(String name){
        return null;
    }

    public String getName(){
        return name;
    }

    public boolean isFolder(){
        return false;
    }
}

class Folder implements  FileSystemItem{
    private String name;
    private List<FileSystemItem> children;

    public Folder(String n){
        System.out.println("created");
        name=n;
        children=new ArrayList<>();
    }

    public void add(FileSystemItem item){
        children.add(item);
    }

    public void ls(int indent){
        String indentSpaces=" ".repeat(indent);
        for (FileSystemItem child:children){
            if(child.isFolder()){
                System.out.println(indentSpaces+"+ "+ child.getName());

            }
            else{
                System.out.println(indentSpaces+ child.getName());
            }
        }
    }

    public void openAll(int indent){
        String indentSpaces=" ".repeat(indent);
        System.out.println(indentSpaces+"+ "+name);
        for (FileSystemItem child:children){
            child.openAll(indent+4);
        }
    }

    public int getSize(){
        int total=0;
        for(FileSystemItem child:children){
            total+=child.getSize();
        }
        return total;
    }

    public FileSystemItem cd(String target){
        for (FileSystemItem child:children){
            if(child.isFolder() && child.getName().equals(target)){
                return child;
            }
        }

        return null;
    }

    public String getName(){
        return name;
    }

    public boolean isFolder(){
        return true;
    }


}

public class CompositePattern {
    public static void main(String[] args){
        Folder root =new Folder("root");
        root.add(new File("file1.txt",1));
        root.add(new File("file2.txt",1));

        Folder docs=new Folder("docs");
        docs.add(new File("resume.pdf",1));
        docs.add(new File("notes.txt",1));

        root.add(docs);

        Folder images=new Folder("images");
        images.add(new File("photo.jpg",1));
        root.add(images);

        root.ls(0);

        docs.ls(0);
        root.openAll(0);

        FileSystemItem cwd=root.cd("docs");
        if(cwd!=null){
            cwd.ls(0);
        }
        else{
            System.out.println("\n could not cd into docs\n");
        }

        System.out.println(root.getSize());

    }




}
